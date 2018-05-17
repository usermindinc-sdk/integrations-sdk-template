#!/usr/bin/env groovy

node {
    try {

        stage('checkout') {
            checkout scm
            changeLogMessage = changeLogs()
        }

        stage('Configure environment') {
            build_config = util.loadJenkinsConfiguration("jenkins.yaml")
            util.useJDKVersion(build_config.javaVersion)
            util.useMavenVersion(build_config.mavenVersion)
            pom = readMavenPom file: 'pom.xml'

            // for you/your team to do this. For example, Skylab has a slack channel just for this. If you just want the messages
            // to go to the author of the latest git commit, leave this as is (and delete the if block).
            // Remember that you need '@' (for direct messages) or '#' (for channels) on the front of the slackMessageDestination value.
            slackMessageDestination = "@${util.committerSlackName()}"
            //More complex example:
            if(util.isPullRequest() || env.BRANCH_NAME == 'staging' || env.BRANCH_NAME == 'master') {
                slackMessageDestination = "#discovery"
            }
            gitCommit = util.commitSha()

        }
        stage('build') {
            // Let people know a build has begun
            if(env.BRANCH_NAME == 'staging' || env.BRANCH_NAME == 'master') {
                util.sendSlackMessage(slackMessageDestination, ":jenkins: ${pom.artifactId} ${pom.version} build started: <${env.BUILD_URL}|${env.JOB_NAME}#${env.BUILD_NUMBER}> \n ${changeLogMessage}")
                sh 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent deploy'
                sh 'mvn sonar:sonar -Dsonar.host.url=http://sonar.usermind.com:9000'
            } else {
                sh 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install'
            }
        }

        stage('Publish docker image') {
            //Push the image to docker hub only if config says to.
            if (build_config.pushDockerImage) {
                docker.withRegistry('https://registry.hub.docker.com', 'dockerhub-userminddeployer') {
                    dockerImage = docker.image("usermindinc/${pom.artifactId}:${pom.version}")
                    echo "Trying to push usermindinc/${pom.artifactId}:${pom.version}"
                    dockerImage.push("${pom.version}")
                }
            } else {
                echo "Not pushing docker image per configuration."
            }
        }

        stage('Deploy docker image to Kubernetes') {
            if(build_config.autoDeploy == true) {
                echo "Configured for kube cluster: ${build_config.kubeCluster}"
                echo "Configured for kube deployment: ${build_config.kubeDeploymentFile}"

                if(build_config.requireApproval == true) {
                    util.sendSlackMessage(build_config.deploymentSlackRoom, ":shipit: ${pom.artifactId} ${pom.version} ready for deployment! Approve here: ${env.BUILD_URL}" + "input \n ${changeLogMessage}")
                    // Wait for user input before proceeding
                    approver = util.promptUserForDeployment("Deploy Failure Analyzer to the ${build_config.kubeCluster} kubernetes cluster with ${build_config.kubeDeploymentFile}?")
                    util.sendSlackMessage(build_config.deploymentSlackRoom, ":jenkins_ninja: :approved: Deployment approved by ${approver}! Starting deployment.")
                } else {
                    util.sendSlackMessage(build_config.deploymentSlackRoom, ":jenkins_general: Starting deployment of ${pom.artifactId} ${pom.version}. \n ${changeLogMessage}")
                }
                withEnv(["PRODUCT_VERSION=${pom.version}"]) {
                    kubernetesDeploy(
                            kubeconfigId: "${build_config.kubeCluster}-kubernetes-credentials",
                            configs: build_config.kubeDeploymentFile,
                            dockerCredentials: [[credentialsId: 'dockerhub-userminddeployer']])
                }

                //See if the deployment succeeded, and notify if not
                slackMessage = ":jenkins_general: :live: Deployment of ${pom.artifactId} ${pom.version} succeeded!"
                if (currentBuild.result == 'FAILURE') {
                    slackMessage = ":jenkins_general_rage: Deployment of ${pom.artifactId} ${pom.version} FAILED! Error is here: ${env.BUILD_URL}" + "consoleFull"
                }
                //Send the message to a team related room or DM
                util.sendSlackMessage(slackMessageDestination, slackMessage)
                //Send the message to an environment related room.
                util.sendSlackMessage(build_config.deploymentSlackRoom, slackMessage)

            } else {
                echo "Not deploying to kubernetes per autoDeploy configuration."
            }
        }
    }

    catch (buildError) {
        currentBuild.result = 'FAILURE'
        util.sendSlackMessage(slackMessageDestination, ":jenkins_rage: ${pom.artifactId} ${pom.version} build FAILED: ${env.BUILD_URL}consoleFull", "danger")
        util.sendFailureEmail(util.commitAuthorEmail())
        throw buildError
    }

}

//Temporary until this is accepted to the main jenkins util file
@NonCPS
def commitInfo(commit) {
    return commit != null ? "`${commit.commitId.take(7)}`  *${commit.msg}*  _by ${commit.author}_ \n" : ""
}

@NonCPS
def changeLogs() {
    String changeLog = ""
    def changeLogSets = currentBuild.changeSets
    for (int i = 0; i < changeLogSets.size(); i++) {
        def entries = changeLogSets[i].items
        for (int j = 0; j < entries.length; j++) {
            def entry = entries[j]
            changeLog = changeLog + "${commitInfo(entry)}"
        }
    }

    if (changeLog) {
        changeLog = "Changes on *${env.BRANCH_NAME}* branch detected\n" + changeLog
    } else {
        commitHash = sh(returnStdout: true, script: 'git rev-parse HEAD').trim().take(7)
        commitText = sh(returnStdout: true, script: 'git show -s --format=format:"*%s*  _by %an_" HEAD').trim()
        changeLog = "No changes on *${env.BRANCH_NAME}* branch detected\n"
        changeLog = changeLog + "Building for commit: \n`${commitHash}` ${commitText}"
    }

    return changeLog
}
