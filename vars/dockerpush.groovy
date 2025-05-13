def call(String credentialsId, String imageName, String imageTag ){
  echo "Pushing Docker image: ${imageName}:${imageTag}"
    
    withCredentials([usernamePassword(
        credentialsId: credentials,
        usernameVariable: 'DOCKER_USERNAME',
        passwordVariable: 'DOCKER_PASSWORD')]) {
        sh """
            echo "\$DOCKER_PASSWORD" | docker login -u "\$DOCKER_USERNAME" --password-stdin
            docker push ${imageName}:${imageTag}
            docker push ${imageName}:latest
        """
    }
}
