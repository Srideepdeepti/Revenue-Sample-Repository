pipeline {
    agent any

    tools {
        jdk 'JDK21'          // Jenkins JDK
        maven 'Maven3'       // Maven installed in Jenkins
    }

    triggers {
        pollSCM('H/5 * * * *'') // Run on commit (or use webhook)
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/Srideepdeepti/Revenue-Sample-Repository.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Publish Reports') {
            steps {
				
				sh 'mvn verify'
              

                // HTML report
                publishHTML(target: [
                    reportName: 'Cucumber HTML Report',
                    reportDir: 'target/cucumber-html-reports',
                    reportFiles: 'overview-features.html',
                    keepAll: true,
                    alwaysLinkToLastBuild: true
                ])

                // Archive Log4j logs
                archiveArtifacts artifacts: 'logs/**', fingerprint: true
            }
        }
    }

    post {
        always {
            echo 'Cleaning up workspace...'
            cleanWs()
        }
        success {
            echo '✅ Build & Tests Passed!'
        }
        failure {
            echo '❌ Build/Tests Failed! Sending Notifications...'
        }
    }
}