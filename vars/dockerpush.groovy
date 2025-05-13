def call(String credentials, String imageName, String imageTag ){
  echo "Pushing Docker image: ${imageName}:${imageTag}"
    
    withCredentials([usernamePassword(
        credentialsId: credentials,
        usernameVariable: 'DOCKER_USERNAME',
        passwordVariable: 'DOCKER_PASSWORD')]) {
        sh """""
            echo "\$DOCKER_PASSWORD" | docker login -u "\$DOCKER_USERNAME" --password-stdin
            docker image tag ${imageName}:${imageTag} ${env.dockerHubUser}/${imageName}:${imageTag}
            docker push ${imageName}:${imageTag}
            docker push ${imageName}:latest
        """""
    }
}
