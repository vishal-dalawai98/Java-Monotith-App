pipeline {

    agent any

    tools {
        jdk 'JDK8'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Maven Build') {
            steps {
                sh '''
                    echo "===== JAVA ====="
                    java -version

                    echo "===== MAVEN ====="
                    mvn -version

                    mvn clean package \
                        -DskipTests \
                        -Dmaven.test.skip=true
                '''
            }
        }

        stage('JUnit Tests') {
            steps {
                echo 'JUnit stage - tests intentionally skipped'
            }
        }

        stage('SonarQube') {
            steps {
                echo 'SonarQube stage - temporarily skipped'
            }
        }

        stage('OWASP Dependency Check') {
            steps {
                echo 'OWASP Dependency Check stage - temporarily skipped'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Docker Build stage - waiting for successful Maven build'
            }
        }

        stage('Trivy Scan') {
            steps {
                echo 'Trivy Scan stage - waiting for Docker image'
            }
        }
    }
}
