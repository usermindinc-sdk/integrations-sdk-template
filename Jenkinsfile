#!/usr/bin/env groovy

properties([pipelineTriggers([upstream('Usermind/integrations-sdk-base/master')])])

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
                sh 'mvn -B clean org.jacoco:jacoco-maven-plugin:prepare-agent deploy'
            } else {
                // Ensure that the application name is appropriate may need to include -application after artifactid
                if(slackMessageDestination != "@Jenkins") {
                    util.sendSlackMessage(slackMessageDestination, ":jenkins: ${pom.artifactId} ${pom.version} build started: <${env.BUILD_URL}|${env.JOB_NAME}#${env.BUILD_NUMBER}> \n ${changeLogMessage}")
                }
                // Add test related commands ass appropriate eg -Dbasepom.test.timeout=0 -Dbasepom.failsafe.timeout=0
                sh 'mvn -B clean org.jacoco:jacoco-maven-plugin:prepare-agent install'
            }
        }

        try {
            stage('sonar') {
                sh 'mvn -B sonar:sonar -Dsonar.host.url=https://sonar.usermind.com'
            }
        } catch (error) {
            util.sendSlackMessage(slackMessageDestination, ":jenkins_rage: The sonar build failed!", "danger")
        }

        //Scan with SourceClear to identify vulnerabilities
        stage('SourceClear scan') {
            withCredentials([string(credentialsId: 'SRCCLR_API_TOKEN', variable: 'SRCCLR_API_TOKEN')]) {
                sh "curl -sSL https://download.sourceclear.com/ci.sh | sh"
            }
        }

        //If this is a pull request - then stop here. Failsafe to keep from going though the deployment steps on PRs.
        if( util.isPullRequest() ) {
            if(slackMessageDestination != "@Jenkins") {
                util.sendSlackMessage(slackMessageDestination, ":jenkins: ${pom.artifactId}-application ${pom.version} build FAILED: ${env.BUILD_URL}consoleFull")
            }
            currentBuild.result = 'SUCCESS'
            return
        }

        stage('Copy files to NiFi') {
            if(build_config.autoDeploy == true) {
                sh "git --no-pager log --oneline -20 > ${pom.artifactId}_changes.txt"

                sshPublisher(publishers:[
                    sshPublisherDesc(configName: 'NiFi 0 Staging', transfers: [sshTransfer(cleanRemote: false, excludes: 'target/*docker-info*', execCommand: '', execTimeout: 120000, flatten: true, patternSeparator: '[, ]+', remoteDirectory: "${build_config.deploymentDirectory}", remoteDirectorySDF: false, removePrefix: '', sourceFiles: 'target/integrations-sdk*.jar,*changes.txt')]),
                    sshPublisherDesc(configName: 'NiFi 1 Staging', transfers: [sshTransfer(cleanRemote: false, excludes: 'target/*docker-info*', execCommand: '', execTimeout: 120000, flatten: true, patternSeparator: '[, ]+', remoteDirectory: "${build_config.deploymentDirectory}", remoteDirectorySDF: false, removePrefix: '', sourceFiles: 'target/integrations-sdk*.jar,*changes.txt')]),
                    sshPublisherDesc(configName: 'NiFi 2 Staging', transfers: [sshTransfer(cleanRemote: false, excludes: 'target/*docker-info*', execCommand: '', execTimeout: 120000, flatten: true, patternSeparator: '[, ]+', remoteDirectory: "${build_config.deploymentDirectory}", remoteDirectorySDF: false, removePrefix: '', sourceFiles: 'target/integrations-sdk*.jar,*changes.txt')])
                ])
            }
        }
        stage('Write to Slack') {
            if (env.BRANCH_NAME == 'master') {
                //See if the deployment succeeded, and notify if not
                slackTeamMessageDestination = '#staging'
                slackMessage = ":jenkins_general: :live: Deployment of ${pom.artifactId} ${pom.version} Jar File to Staging NIFI cluster is succeeded!"
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
