#!/usr/bin/env groovy

node {

    try {

        stage('checkout') {
            deleteDir()
            checkout scm
            changeLogMessage = util.changeLogs()
        }

        stage('Configure environment') {
            build_config = util.loadJenkinsConfiguration("jenkins.yaml")
            util.useJDKVersion(build_config.javaVersion)
            util.useMavenVersion(build_config.mavenVersion)
            pom = readMavenPom file: 'pom.xml'

            // For you/your team to do: Choose a slack channel. For example, Skylab has a slack channel just for builds. If you just want the messages
            // to go to the author of the latest git commit, leave this as is (and delete the if block).
            // Remember that you need '@' (for direct messages) or '#' (for channels) on the front of the slackMessageDestination value.
            slackMessageDestination = "@${util.committerSlackName()}"
            // More complex example:
            if(util.isPullRequest() || env.BRANCH_NAME == 'develop' || env.BRANCH_NAME == 'master') {
                // Change out for the appropriate team channel
                slackTeamMessageDestination = "#integration-build"
            }
            gitCommit = util.commitSha()
        }

        stage('build') {
            // Let people know a build has begun
            if(env.BRANCH_NAME == 'develop' || env.BRANCH_NAME == 'master') {
                // Ensure that the application name is appropriate may need to include -application after artifactid
                if(slackMessageDestination != "@Jenkins") {
                    util.sendSlackMessage(slackMessageDestination, ":jenkins: ${pom.artifactId} ${pom.version} build started: <${env.BUILD_URL}|${env.JOB_NAME}#${env.BUILD_NUMBER}> \n ${changeLogMessage}")
                }
                // Ensure that the application name is appropriate may need to include -application after artifactid
                util.sendSlackMessage(slackTeamMessageDestination, ":jenkins: ${pom.artifactId} ${pom.version} build started: <${env.BUILD_URL}|${env.JOB_NAME}#${env.BUILD_NUMBER}> \n ${changeLogMessage}")
                // Add test related commands ass appropriate eg -Dbasepom.test.timeout=0 -Dbasepom.failsafe.timeout=0
                sh 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent deploy'
            } else {
                // Ensure that the application name is appropriate may need to include -application after artifactid
                if(slackMessageDestination != "@Jenkins") {
                    util.sendSlackMessage(slackMessageDestination, ":jenkins: ${pom.artifactId} ${pom.version} build started: <${env.BUILD_URL}|${env.JOB_NAME}#${env.BUILD_NUMBER}> \n ${changeLogMessage}")
                }
                // Add test related commands ass appropriate eg -Dbasepom.test.timeout=0 -Dbasepom.failsafe.timeout=0
                sh 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install'
            }
        }

        try {
            stage('sonar') {
                sh 'mvn sonar:sonar -Dsonar.host.url=https://sonar.usermind.com'
            }
        } catch (error) {
            util.sendSlackMessage(slackMessageDestination, ":jenkins_rage: The sonar build failed!", "danger")
        }

        //If this is a pull request - then stop here. Failsafe to keep from going though the docker and kubernetes steps on PRs.
        if( util.isPullRequest() ) {
            if(slackMessageDestination != "@Jenkins") {
                util.sendSlackMessage(slackMessageDestination, ":jenkins: ${pom.artifactId}-application ${pom.version} build FAILED: ${env.BUILD_URL}consoleFull")
            }
            currentBuild.result = 'SUCCESS'
            return
        }

        stage('Publish docker image') {
            //Push the image to docker hub only if config says to.
            if (build_config.pushDockerImage) {
                docker.withRegistry('https://registry.hub.docker.com', 'dockerhub-userminddeployer') {
                    // Ensure that the application name is appropriate may need to include -application after artifactid
                    dockerImage = docker.image("usermindinc/${pom.artifactId}:${pom.version}")
                    echo "Trying to push usermindinc/${pom.artifactId}:${pom.version}"
                    dockerImage.push("${pom.version}")
                    // Use this if you are not doing so in your pom.
                    // dockerImage.push("latest")
                }
            } else {
                echo "Not pushing docker image per configuration."
            }
        }

        stage('Create configmap for Kubernetes') {
            // Update the Configmaps on kubernetes.
            if (env.BRANCH_NAME == 'master') {
                // Append this to config in both instances if you have prod and staging configs:
                // -${build_config.configMap}
                sh "sed 's/^/    /' src/main/resources/config-prod.yaml >> kubernetes/config-${build_config.configMap}.yaml"
            } else {
                // Append this to config in both instances if you have prod and staging configs:
                // -${build_config.configMap}
                sh "sed 's/^/    /' src/main/resources/config-staging.yaml >> kubernetes/config-${build_config.configMap}.yaml"
            }
            sh "echo '  baseConfig.yaml: |' >> kubernetes/config-${build_config.configMap}.yaml"
            sh "sed 's/^/    /' src/main/resources/baseConfig.yaml >> kubernetes/config-${build_config.configMap}.yaml"
        }


        stage('Deploy docker image to Kubernetes') {
            if(build_config.autoDeploy == true) {
                echo "Configured for kube cluster: ${build_config.kubeCluster}"
                echo "Configured for kube deployment: ${build_config.kubeDeploymentFiles?build_config.kubeDeploymentFiles.first():null}"

                if(build_config.requireApproval == true) {
                    util.sendSlackMessage(build_config.deploymentSlackRoom, ":shipit: ${pom.artifactId} ${pom.version} ready for deployment! Approve here: ${env.BUILD_URL}" + "input \n ${changeLogMessage}")
                    // Wait for user input before proceeding
                    approver = util.promptUserForDeployment("Deploy ${pom.artifactId} to the ${build_config.kubeCluster} kubernetes cluster with ${build_config.kubeDeploymentFiles?build_config.kubeDeploymentFiles.first():null}?", "${build_config.deploymentSlackRoom}", slackTeamMessageDestination)
                    util.sendSlackMessage(build_config.deploymentSlackRoom, ":jenkins_ninja: :approved: Deployment approved by ${approver}! Starting deployment.")
                    util.sendSlackMessage(slackTeamMessageDestination, ":jenkins_ninja: :approved: Deployment of ${pom.artifactId} approved by ${approver}! Starting deployment.")
                    if(slackMessageDestination != "@Jenkins") {
                        util.sendSlackMessage(slackMessageDestination, ":jenkins_ninja: :approved: Deployment of ${pom.artifactId} approved by ${approver}! Starting deployment.")
                    }
                } else {
                    util.sendSlackMessage(build_config.deploymentSlackRoom, ":jenkins_general: Starting deployment of ${pom.artifactId}-application ${pom.version}. \n ${changeLogMessage}")
                    util.sendSlackMessage(slackTeamMessageDestination, ":jenkins_general: Starting deployment of ${pom.artifactId}-application ${pom.version}. \n ${changeLogMessage}")
                    if(slackMessageDestination != "@Jenkins") {
                        util.sendSlackMessage(slackMessageDestination, ":jenkins_general: Starting deployment of ${pom.artifactId}-application ${pom.version}. \n ${changeLogMessage}")
                    }
                }
                withEnv(["PRODUCT_VERSION=${pom.version}"]) {
                    kubernetesDeploy(
                        kubeconfigId: "${build_config.kubeCluster}-kubernetes-credentials",
                        configs: "${build_config.kubeDeploymentFiles.join(',')}",
                        dockerCredentials: [[credentialsId: 'dockerhub-userminddeployer']])
                }

                //See if the deployment succeeded, and notify if not
                slackMessage = ":jenkins_general: :live: Deployment of ${pom.artifactId} ${pom.version} succeeded!"
                if (currentBuild.result == 'FAILURE') {
                    slackMessage = ":jenkins_general_rage: Deployment of ${pom.artifactId} ${pom.version} FAILED! Error is here: ${env.BUILD_URL}" + "consoleFull"
                }
                //Send the message to a team related room or DM
                util.sendSlackMessage(slackTeamMessageDestination, slackMessage)
                //Send the message to an environment related room.
                util.sendSlackMessage(build_config.deploymentSlackRoom, slackMessage)
                //Send the message to the person who created the PR.
                if(slackMessageDestination != "@Jenkins") {
                    util.sendSlackMessage(slackMessageDestination, slackMessage)
                }

            } else {
                echo "Not deploying to kubernetes per autoDeploy configuration."
            }
        }
    }

    catch (buildError) {
        currentBuild.result = 'FAILURE'
        if(slackMessageDestination != "@Jenkins") {
            util.sendSlackMessage(slackMessageDestination, ":jenkins_rage: ${pom.artifactId} ${pom.version} build FAILED: ${env.BUILD_URL}consoleFull", "danger")
            util.sendFailureEmail(util.commitAuthorEmail())
        }
        throw buildError
    }

}
