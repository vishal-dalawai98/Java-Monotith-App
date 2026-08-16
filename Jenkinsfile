pipeline {

    agent any

    tools {
        jdk 'JDK8'
    }

    stages {

        stage('Checkout') {
            steps {
                echo '========================================'
                echo 'CHECKOUT'
                echo '========================================'

                checkout scm
            }
        }

        stage('Java & Maven') {
            steps {
                sh '''
                    set -e

                    echo "========================================"
                    echo "JAVA"
                    echo "========================================"

                    export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
                    export PATH="$JAVA_HOME/bin:/usr/bin:/bin"

                    echo "JAVA_HOME=$JAVA_HOME"

                    which java
                    java -version

                    echo "========================================"
                    echo "MAVEN"
                    echo "========================================"

                    which mvn
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

                    echo "========================================"
                    echo "BUILD ARTIFACT"
                    echo "========================================"

                    ls -lh target/

                    test -f target/Snowman.jar

                    echo "Snowman.jar created successfully"
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

                    docker --version

                    echo "Checking artifact..."
                    ls -lh target/Snowman.jar

                    test -f target/Snowman.jar

                    echo "Building Docker image..."

                    docker build \
                        -t snowman:${BUILD_NUMBER} \
                        -t snowman:latest \
                        .

                    echo "========================================"
                    echo "DOCKER IMAGES"
                    echo "========================================"

                    docker images snowman
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

                    echo "Docker network..."

                    docker network inspect snowman-net >/dev/null 2>&1 || \
                        docker network create snowman-net

                    echo "Stopping old Snowman container..."

                    docker stop snowman 2>/dev/null || true
                    docker rm snowman 2>/dev/null || true

                    echo "Starting new Snowman container..."

                    docker run -d \
                        --name snowman \
                        --network snowman-net \
                        -p 8081:8080 \
                        snowman:${BUILD_NUMBER}

                    echo "========================================"
                    echo "CONTAINER STATUS"
                    echo "========================================"

                    sleep 5

                    docker ps -a --filter name=snowman

                    echo "========================================"
                    echo "APPLICATION LOGS"
                    echo "========================================"

                    docker logs --tail 50 snowman

                    echo "========================================"
                    echo "DEPLOYMENT COMPLETE"
                    echo "========================================"

                    echo "Snowman: http://20.9.78.68:8081"
                '''
            }
        }
    }

    post {
        success {
            echo '========================================'
            echo 'PIPELINE SUCCESS'
            echo '========================================'
            echo 'Snowman deployed successfully on port 8081'
        }

        failure {
            echo '========================================'
            echo 'PIPELINE FAILED'
            echo '========================================'

            sh '''
                docker ps -a --filter name=snowman || true
            '''
        }
    }
}
