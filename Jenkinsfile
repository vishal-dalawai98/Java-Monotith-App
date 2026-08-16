pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code'
                checkout scm
            }
        }

        stage('Maven Build') {
            steps {
                echo 'Building application with Maven'

                sh '''
                    java -version
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
                echo 'OWASP Dependency Check stage'

                sh '''
                    mvn org.owasp:dependency-check-maven:check \
                        -DskipTests
                '''
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Building Docker image'

                sh '''
                    docker build \
                        -t enterprise-application:${BUILD_NUMBER} \
                        .
                '''
            }
        }

        stage('Trivy Scan') {
            steps {
                echo 'Scanning Docker image with Trivy'

                sh '''
                    trivy image \
                        --severity HIGH,CRITICAL \
                        --exit-code 1 \
                        enterprise-application:${BUILD_NUMBER}
                '''
            }
        }
    }

    post {
        always {
            echo "Pipeline completed: ${currentBuild.currentResult}"
        }

        success {
            echo 'Pipeline completed successfully'
        }

        failure {
            echo 'Pipeline failed - check the stage logs'
        }
    }
}
