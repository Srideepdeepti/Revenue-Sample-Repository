pipeline {
    agent any
   tools {
    jdk 'Java11'
    maven 'Maven3'
}

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Srideepdeepti/Revenue-Sample-Repository.git'
            }
        }
        stage('Run Tests') {
            steps {
                sh 'mvn clean verify -Dtest=cucumberOptions.TestRunner'
            }
        }
        stage('Publish Reports') {
            steps {
                cucumber buildStatus: 'UNSTABLE', jsonReportDirectory: 'target/jsonReports'
            }
        }
    }
}
