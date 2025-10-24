pipeline {
    agent any
    tools {
        maven 'Maven3'
        jdk 'Java11'
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
                cucumber buildStatus: 'UNSTABLE', jsonReportDirectory: 'target/cucumber-html-reports'
            }
        }
    }
}
