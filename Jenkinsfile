pipeline {
    agent any

    environment {
        IMAGE_NAME = 'snowman'
        CONTAINER_NAME = 'snowman'
        HOST_PORT = '8081'
        CONTAINER_PORT = '8080'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Java & Maven') {
            steps {
                sh '''
                    set -e

                    export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
                    export PATH="$JAVA_HOME/bin:/usr/bin:/bin"

                    echo "========================================"
                    echo "JAVA"
                    echo "========================================"

                    echo "JAVA_HOME=$JAVA_HOME"
                    java -version

                    echo "========================================"
                    echo "MAVEN"
                    echo "========================================"

                    mvn -version
                '''
            }
        }

        stage('Maven Build') {
            steps {
                sh '''
                    set -e

                    export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
                    export PATH="$JAVA_HOME/bin:/usr/bin:/bin"

                    echo "========================================"
                    echo "MAVEN BUILD"
                    echo "========================================"

                    mvn clean package \
                        -DskipTests \
                        -Dmaven.test.skip=true
                '''
            }
        }

        stage('Verify JAR') {
            steps {
                sh '''
                    set -e

                    echo "========================================"
                    echo "VERIFY JAR"
                    echo "========================================"

                    test -f target/enterprise-application-1.0-SNAPSHOT.jar

                    ls -lh target/enterprise-application-1.0-SNAPSHOT.jar
                '''
            }
        }

        stage('Docker Build') {
            steps {
                sh '''
                    set -e

                    echo "========================================"
                    echo "DOCKER BUILD"
                    echo "========================================"

                    docker build \
                        -t ${IMAGE_NAME}:${BUILD_NUMBER} \
                        -t ${IMAGE_NAME}:latest \
                        .
                '''
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    set -e

                    echo "========================================"
                    echo "DEPLOY"
                    echo "========================================"

                    echo "Stopping old container..."

                    docker stop ${CONTAINER_NAME} 2>/dev/null || true

                    echo "Removing old container..."

                    docker rm ${CONTAINER_NAME} 2>/dev/null || true

                    echo "Starting new container..."

                    docker run -d \
                        --name ${CONTAINER_NAME} \
                        --restart unless-stopped \
                        -p ${HOST_PORT}:${CONTAINER_PORT} \
                        ${IMAGE_NAME}:${BUILD_NUMBER}

                    echo "========================================"
                    echo "CONTAINER STATUS"
                    echo "========================================"

                    docker ps --filter "name=${CONTAINER_NAME}"

                    echo "========================================"
                    echo "DEPLOYMENT COMPLETE"
                    echo "========================================"

                    echo "Application: http://<SERVER-IP>:${HOST_PORT}"
                '''
            }
        }

        stage('Deployment Verify') {
            steps {
                sh '''
                    set -e

                    echo "========================================"
                    echo "DEPLOYMENT VERIFICATION"
                    echo "========================================"

                    docker ps --filter "name=${CONTAINER_NAME}"

                    echo ""
                    echo "Container logs:"
                    docker logs --tail 30 ${CONTAINER_NAME}
                '''
            }
        }
    }

    post {
        success {
            echo '''
========================================
PIPELINE SUCCESS
========================================

Build:     ${BUILD_NUMBER}
Image:     ${IMAGE_NAME}:${BUILD_NUMBER}
Container: ${CONTAINER_NAME}
Port:      8081 -> 8080

Snowman deployment completed successfully.
'''
        }

        failure {
            echo '''
========================================
PIPELINE FAILED
========================================

Check the failed stage and console output.
'''
        }
    }
}
