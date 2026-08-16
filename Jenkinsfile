pipeline {
    agent any

    tools {
        jdk 'JDK8'
    }

    environment {
        JAVA_HOME = '/usr/lib/jvm/java-8-openjdk-amd64'
        PATH = "/usr/lib/jvm/java-8-openjdk-amd64/bin:/usr/local/bin:/usr/bin:/bin"
        IMAGE_NAME = 'snowman'
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

                    echo "========================================"
                    echo "JAVA"
                    echo "========================================"

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

                    ls -lh target/

                    test -f target/enterprise-application-1.0-SNAPSHOT.jar

                    echo "JAR BUILD SUCCESSFUL"
                    ls -lh target/enterprise-application-1.0-SNAPSHOT.jar
                '''
            }
        }

        stage('Docker Check') {
            steps {
                sh '''
                    set -e

                    echo "========================================"
                    echo "DOCKER CHECK"
                    echo "========================================"

                    docker --version
                    docker ps
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

        stage('Verify Docker Image') {
            steps {
                sh '''
                    set -e

                    echo "========================================"
                    echo "DOCKER IMAGE"
                    echo "========================================"

                    docker images ${IMAGE_NAME}

                    echo ""
                    echo "IMAGE CREATED:"
                    echo "${IMAGE_NAME}:${BUILD_NUMBER}"
                    echo "${IMAGE_NAME}:latest"
                '''
            }
        }
    }

    post {
        success {
            echo '========================================'
            echo 'PIPELINE SUCCESS'
            echo '========================================'
            echo 'Maven build successful.'
            echo 'Docker image build successful.'
        }

        failure {
            echo '========================================'
            echo 'PIPELINE FAILED'
            echo '========================================'
            echo 'Check the failed stage and console output.'
        }
    }
}
