pipeline {
  agent any
  stages {
    stage('Initialize 1.8.9') {
      steps {
        sh '''echo username=sk1erdeploy'\n'password=d2eb5509b27c5df1bda08f5c7d652f1b22017cc2 > gradle.properties.private'''
        sh '''./gradlew clean
        ./gradlew changeMcVersion -PminecraftVersion=10809
        ./gradlew setupCiWorkspace'''
      }
    }
    stage('Build 1.8.9') {
      steps {
        sh "./gradlew build -PminecraftVersion=10809 -PBUILD_ID=${env.BUILD_ID}"
      }
    }

    stage('Initialize 1.12.2') {
      steps {
        sh '''echo username=sk1erdeploy'\n'password=d2eb5509b27c5df1bda08f5c7d652f1b22017cc2 > gradle.properties.private'''
        sh '''./gradlew clean
            ./gradlew changeMcVersion -PminecraftVersion=11202
            ./gradlew setupCiWorkspace'''
      }
    }
    stage('Build 1.12.2') {
      steps {
        sh "./gradlew build -PminecraftVersion=11202 -PBUILD_ID=${env.BUILD_ID}"
      }
    }
    stage('Report') {
      steps {
        archiveArtifacts 'build/libs/*.jar'
      }
    }
  }
}
