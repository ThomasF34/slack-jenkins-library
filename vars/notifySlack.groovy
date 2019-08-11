#!/usr/bin/env groovy

def call(String buildStatus, String userSlackId = 'nouser') {
    // Build status of null means success.
    buildStatus = buildStatus ?: 'SUCCESS'

    def color

    if (buildStatus == 'SUCCESS') {
        color = '#00A81B'
    } else if (buildStatus == 'UNSTABLE') {
        color = '#FFEA00'
    } else {
        color = '#A61600'
    }

		def msg
		if(userSlackId != 'nouser'){
			msg = "<@${userSlackId}> ${buildStatus}: `${env.JOB_NAME}` #${env.BUILD_NUMBER}:\n${env.BUILD_URL}"
		} else {
			msg = "${buildStatus}: `${env.JOB_NAME}` #${env.BUILD_NUMBER}:\n${env.BUILD_URL}"
		}

    slackSend(color: color, message: msg)
}
