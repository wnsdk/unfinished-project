pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    credentialsId: 'github_cred',
                    url: 'https://github.com/wnsdk/owners.git'
            }
        }
    }
}

// node {
//      stage('Clone repository') {
//          checkout scm
//      }
//      stage('Build image') {
//          app = docker.build("wnsdk/owners")
         
//      }
//      stage('Push image') {
//          docker.withRegistry('https://registry.hub.docker.com', 'docker-hub') {
//              app.push("${env.BUILD_NUMBER}")
//              app.push("latest")
//          }
//      }
// }

// stage('Build image') {
//   app = docker.build("wnsdk/owners")
// }

// stage('Push image') {
//   docker.withRegistry('https://registry.hub.docker.com', 'docker-hub')
//   {
//     app.push("${env.BUILD_NUMBER}")
//     app.push("latest")
//   }
// }
