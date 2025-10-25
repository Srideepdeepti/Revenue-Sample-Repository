pipeline {
    agent any
   tools {
    jdk 'Java21'
    maven 'Maven3'
        } 

    stages {
		
		
      
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Srideepdeepti/Revenue-Sample-Repository.git'
            }
        }
        stage('Run Tests') {
            steps {
                sh 'mvn clean verify -Dtest=cucumberOptions.TestRunner'
            }
        }
        stage('Publish Reports') {
			steps{
         publishHTML(target: [
        reportName : 'Cucumber HTML Report',
        reportDir  : 'target/cucumber-html-reports/cucumber-html-reports',
        reportFiles: 'overview-features.html',
        keepAll    : true,
        alwaysLinkToLastBuild: true,
        allowMissing: false
          ])
        }
      }

    }
    
    post {
        always {
            echo 'Publishing reports, even if build failed...'

            // ✅ Publish HTML Cucumber Report (always)
            publishHTML(target: [
                reportName: 'Cucumber HTML Report',
                reportDir: 'target/cucumber-html-reports/cucumber-html-reports',
                reportFiles: 'overview-features.html',
                keepAll: true,
                alwaysLinkToLastBuild: true,
                allowMissing: true  // prevents failure if report folder missing
            ]) 
            
            
            // ✅ Archive all Cucumber reports (HTML + JSON)
            archiveArtifacts artifacts: 'target/**/*.html, target/**/*.json', 
                              fingerprint: true,
                              onlyIfSuccessful: false

            }
           } 
}
