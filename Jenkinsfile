pipeline {
  agent any
  stages {
    stage('Initialize') {
      steps {
        sh '''echo username=sk1erdeploy'\n'password=${github-deploy} > gradle.properties.private'''
        sh '''
        ./gradlew clean
        ./gradlew preprocessResources
        '''
      }
    }

    stage('Build') {
      steps {
        sh "./gradlew publish -PBUILD_ID=${env.BUILD_ID} -PIS_CI=true --no-daemon"
      }
    }

    stage('Report') {
      steps {
        archiveArtifacts 'versions/1.8.9/build/libs/*.jar'
        archiveArtifacts 'versions/1.12.2/build/libs/*.jar'
        archiveArtifacts 'versions/1.15.2/build/libs/*.jar'
      }
    }
  }
}
