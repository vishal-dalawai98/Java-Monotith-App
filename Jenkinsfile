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
                    set -e

                    export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
                    export PATH="$JAVA_HOME/bin:/usr/bin:/bin"

                    echo "========================================"
                    echo "JAVA"
                    echo "========================================"
                    java -version

                    echo "========================================"
                    echo "MAVEN"
                    echo "========================================"
                    mvn -version

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
                    echo "BUILT ARTIFACT"
                    echo "========================================"

                    ls -lh target/

                    test -f target/enterprise-application-1.0-SNAPSHOT.jar

                    echo "JAR found successfully:"
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

                    docker --version

                    docker build \
                        -t snowman:${BUILD_NUMBER} \
                        -t snowman:latest \
                        .
                '''
            }
        }

        stage('Docker Verify') {
            steps {
                sh '''
                    echo "========================================"
                    echo "DOCKER IMAGE"
                    echo "========================================"

                    docker images snowman
                '''
            }
        }
    }

    post {
        success {
            echo '========================================'
            echo 'PIPELINE SUCCESS'
            echo 'Maven build + Docker image completed successfully.'
            echo '========================================'
        }

        failure {
            echo '========================================'
            echo 'PIPELINE FAILED'
            echo 'Check the stage above for the error.'
            echo '========================================'
        }
    }
}
