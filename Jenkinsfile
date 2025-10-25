pipeline {
    agent any
   tools {
    jdk 'Java21'
    maven 'Maven3'
}

    stages {
		
		
		 stages {
        stage('Verify Tools') {
            steps {
                sh 'java -version'
                sh 'mvn -v'
            } 
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
