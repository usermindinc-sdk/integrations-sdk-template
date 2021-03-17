#!/usr/bin/env groovy

node {

    try {

        stage('checkout') {
            deleteDir()
            checkout scm
            changeLogMessage = util.changeLogs()
        }

        stage('Update Pom') {
            // Update the parent library to the latest
            //Update the integrations base library to the latest (1.0 will go to the latest)
            lock('mavenUpdate') {
                sh 'mvn -U validate'
            }

            if ( env.BRANCH_NAME == 'master' || env.BRANCH_NAME == 'hotfix' ) {
                sh 'mvn build-helper:parse-version versions:set -DnewVersion=\\${parsedVersion.majorVersion}.\\${parsedVersion.minorVersion}.\\${env.BUILD_NUMBER} versions:commit'
            } else if ( env.BRANCH_NAME == 'develop' ) {
                sh 'mvn build-helper:parse-version versions:set -DnewVersion=\\${parsedVersion.majorVersion}.\\${parsedVersion.minorVersion}.\\${env.BUILD_NUMBER}-SNAPSHOT versions:commit'
            }
        }

        stage('Configure environment') {
            if(env.BRANCH_NAME == 'master') {
                properties([pipelineTriggers([upstream('Usermind/integrations-sdk-base/master')])])
            } else {
                //Remove any existing triggers
                properties([pipelineTriggers([])])
            }

            build_config = util.loadJenkinsConfiguration("jenkins.yaml")
            util.useJDKVersion(build_config.javaVersion)
            util.useMavenVersion(build_config.mavenVersion)
            pom = readMavenPom file: 'pom.xml'

            slackMessageDestination = "@${util.committerSlackName()}"
            if(util.isPullRequest() || env.BRANCH_NAME == 'develop' || env.BRANCH_NAME == 'master' || env.BRANCH_NAME == 'hotfix') {
                slackTeamMessageDestination = build_config.channel
                slackTeamFailureMessageDestination = build_config.failureChannel
            }

            gitCommit = util.commitSha()
        }

        stage('build') {
            // Let people know a build has begun
            try {
                if(env.BRANCH_NAME == 'develop' || env.BRANCH_NAME == 'master' || env.BRANCH_NAME == 'hotfix') {
                    // Ensure that the application name is appropriate may need to include -application after artifactid
                    util.sendSlackMessage(slackTeamMessageDestination, ":jenkins: ${pom.artifactId} ${pom.version} build started: <${env.BUILD_URL}|${env.JOB_NAME}#${env.BUILD_NUMBER}> \n ${changeLogMessage}")

                    // Ensure that the application name is appropriate may need to include -application after artifactid
                    util.sendSlackMessage(slackTeamMessageDestination, ":jenkins: ${pom.artifactId} ${pom.version} build started: <${env.BUILD_URL}|${env.JOB_NAME}#${env.BUILD_NUMBER}> \n ${changeLogMessage}")

                    sh 'mvn -B clean org.jacoco:jacoco-maven-plugin:prepare-agent deploy'
                } else {
                    sh 'mvn -B clean org.jacoco:jacoco-maven-plugin:prepare-agent install'
                }
            }
            catch (buildError) {
                sh "echo Build failed: ${buildError.toString()}"
                util.sendSlackMessage(slackTeamFailureMessageDestination, ":jenkins_rage: ${pom.artifactId} ${pom.version} build FAILED:  ${env.BUILD_URL}consoleFull \n" + buildError.toString(), "danger")

                withSonarQubeEnv('sonar.usermind.com') {
                    sh 'mvn -B sonar:sonar -Dsonar.host.url=https://sonar.usermind.com'
                }
                throw buildError
            }
        }

        try {
            withSonarQubeEnv('sonar.usermind.com') {
                stage('sonar') {
                    sh 'mvn -B sonar:sonar -Dsonar.host.url=https://sonar.usermind.com'
                }
            }
        } catch (error) {
            util.sendSlackMessage(slackMessageDestination, ":jenkins_rage: The sonar build failed!  ${env.BUILD_URL}consoleFull", "danger")
            util.sendSlackMessage(slackTeamFailureMessageDestination, ":jenkins_general_rage: The sonar build failed! ${env.BUILD_URL}consoleFull", "danger")
        }

        //If this is a pull request or a branch other than master and develop - then stop here. Failsafe to keep from going though the deployment steps as
        //well as things like SourceClear that are slower and that aren't useful for side branches.
        if( util.isPullRequest() || (env.BRANCH_NAME != 'develop' && env.BRANCH_NAME != 'master'  && env.BRANCH_NAME != 'hotfix')) {
            currentBuild.result = 'SUCCESS'
            return
        }

        //Scan with SourceClear to identify vulnerabilities
        stage('SourceClear scan') {
            withCredentials([string(credentialsId: 'SRCCLR_API_TOKEN', variable: 'SRCCLR_API_TOKEN')]) {
                sh "curl -sSL https://download.sourceclear.com/ci.sh | sh"
            }
        }

        stage('Copy files to NiFi') {
            if ((env.BRANCH_NAME == 'master'  || env.BRANCH_NAME == 'hotfix' ) && build_config.autoDeploy == true) {
                sh "git --no-pager log --oneline -10 > ${pom.artifactId}_changes.txt"
                sh "echo '\nSDK Base Pom Version' >> ${pom.artifactId}_changes.txt"
                sh "grep -A1 integrations-sdk-base-pom pom.xml | tail -n 1 >> ${pom.artifactId}_changes.txt"
                sh "echo '\nSDK Base Library Version' >> ${pom.artifactId}_changes.txt"
                sh "grep '<integrations.base.version>' pom.xml >> ${pom.artifactId}_changes.txt"
                sh "echo '\nTracking Library Version' >> ${pom.artifactId}_changes.txt"
                sh "grep '<usermind.testlib.version>' pom.xml >> ${pom.artifactId}_changes.txt"
                sshPublisher(publishers:[
                    sshPublisherDesc(configName: 'NiFi 0 Staging', transfers: [sshTransfer(cleanRemote: false, excludes: 'target/*docker-info*', execCommand: '', execTimeout: 120000, flatten: true, patternSeparator: '[, ]+', remoteDirectory: "${build_config.deploymentDirectory}", remoteDirectorySDF: false, removePrefix: '', sourceFiles: 'target/integrations-sdk*.jar,*changes.txt')]),
                    sshPublisherDesc(configName: 'NiFi 1 Staging', transfers: [sshTransfer(cleanRemote: false, excludes: 'target/*docker-info*', execCommand: '', execTimeout: 120000, flatten: true, patternSeparator: '[, ]+', remoteDirectory: "${build_config.deploymentDirectory}", remoteDirectorySDF: false, removePrefix: '', sourceFiles: 'target/integrations-sdk*.jar,*changes.txt')]),
                    sshPublisherDesc(configName: 'NiFi 2 Staging', transfers: [sshTransfer(cleanRemote: false, excludes: 'target/*docker-info*', execCommand: '', execTimeout: 120000, flatten: true, patternSeparator: '[, ]+', remoteDirectory: "${build_config.deploymentDirectory}", remoteDirectorySDF: false, removePrefix: '', sourceFiles: 'target/integrations-sdk*.jar,*changes.txt')])
                ])
            }
        }

        stage('Write to Slack') {
            if (env.BRANCH_NAME == 'master' || env.BRANCH_NAME == 'hotfix') {
                //See if the deployment succeeded, and notify if not
                slackMessage = ":jenkins_general: :live: Deployment of ${pom.artifactId} ${pom.version} Jar File to Staging NIFI cluster is succeeded!"
                if (currentBuild.result == 'FAILURE') {
                    slackMessage = ":jenkins_general_rage: Deployment of ${pom.artifactId} ${pom.version} FAILED! Error is here: ${env.BUILD_URL}" + "consoleFull"
                    util.sendSlackMessage(slackTeamFailureMessageDestination, ":jenkins_general_rage: Deployment of ${pom.artifactId} ${pom.version} FAILED! Error is here: ${env.BUILD_URL}" + "consoleFull")
                }
            }
        }
    }

    catch (buildError) {
        currentBuild.result = 'FAILURE'
        util.sendSlackMessage(slackMessageDestination, ":jenkins_rage: ${pom.artifactId} ${pom.version} build FAILED: ${env.BUILD_URL}consoleFull", "danger")
        util.sendSlackMessage(slackTeamFailureMessageDestination, ":jenkins_rage: ${pom.artifactId} ${pom.version} build FAILED:  ${env.BUILD_URL}consoleFull \n" + buildError.toString(), "danger")
        util.sendFailureEmail(util.commitAuthorEmail())
        throw buildError
    }

}
