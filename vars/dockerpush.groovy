def call(String credId, String imageName, String imageTag ){
  echo "Pushing Docker image: ${imageName}:${imageTag}"
    
    withCredentials([usernamePassword(
        credentialsId: credId,
        usernameVariable: "dockerHubUser",
        passwordVariable: "dockerHubPass")]) {
        sh """"
            echo "\$dockerHubPass" | docker login -u "\$dockerHubUser" --password-stdin
            docker image tag ${imageName}:${imageTag} ${env.dockerHubUser}/${imageName}:${imageTag}
            docker push ${env.dockerHubUser}/${imageName}:${imageTag} 
        """"
    }
}
