pipeline {
    agent any

    tools {
        jdk 'JDK8'
    }

<<<<<<< HEAD
=======
    environment {
        JAVA_HOME = '/usr/lib/jvm/java-8-openjdk-amd64'
        PATH = "/usr/lib/jvm/java-8-openjdk-amd64/bin:/usr/local/bin:/usr/bin:/bin"
        IMAGE_NAME = 'snowman'
    }

>>>>>>> 3ab668a (edited Jenkins file for docker build)
    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Verify Java') {
            steps {
                sh '''
                    set -e

                    echo "========================================"
                    echo "JAVA"
                    echo "========================================"

                    echo "JAVA_HOME=$JAVA_HOME"

                    which java
                    java -version

                    which javac
                    javac -version

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
<<<<<<< HEAD

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
=======

                    echo "========================================"
>>>>>>> 3ab668a (edited Jenkins file for docker build)
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
<<<<<<< HEAD

                    echo "========================================"
                    echo "BUILT ARTIFACT"
                    echo "========================================"

                    ls -lh target/

                    test -f target/enterprise-application-1.0-SNAPSHOT.jar

                    echo "JAR found successfully:"
                    ls -lh target/enterprise-application-1.0-SNAPSHOT.jar
=======

                    echo "========================================"
                    echo "VERIFY JAR"
                    echo "========================================"

                    ls -lh target/

                    test -f target/enterprise-application-1.0-SNAPSHOT.jar

                    echo "JAR:"
                    ls -lh target/enterprise-application-1.0-SNAPSHOT.jar
                '''
            }
        }

        stage('Docker Check') {
            steps {
                sh '''
                    set -e

                    echo "========================================"
                    echo "DOCKER"
                    echo "========================================"

                    docker --version

                    echo "Docker access:"
                    docker ps
>>>>>>> 3ab668a (edited Jenkins file for docker build)
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

<<<<<<< HEAD
                    docker --version

                    docker build \
                        -t snowman:${BUILD_NUMBER} \
                        -t snowman:latest \
=======
                    docker build \
                        -t ${IMAGE_NAME}:${BUILD_NUMBER} \
                        -t ${IMAGE_NAME}:latest \
>>>>>>> 3ab668a (edited Jenkins file for docker build)
                        .
                '''
            }
        }

<<<<<<< HEAD
        stage('Docker Verify') {
            steps {
                sh '''
=======
        stage('Docker Image Verify') {
            steps {
                sh '''
                    set -e

>>>>>>> 3ab668a (edited Jenkins file for docker build)
                    echo "========================================"
                    echo "DOCKER IMAGE"
                    echo "========================================"

<<<<<<< HEAD
                    docker images snowman
=======
                    docker images ${IMAGE_NAME}

                    echo "Build completed:"
                    echo "${IMAGE_NAME}:${BUILD_NUMBER}"
>>>>>>> 3ab668a (edited Jenkins file for docker build)
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
<<<<<<< HEAD
=======
            echo 'Maven JAR created successfully.'
            echo 'Docker image created successfully.'
>>>>>>> 3ab668a (edited Jenkins file for docker build)
        }

        failure {
            echo '========================================'
            echo 'PIPELINE FAILED'
<<<<<<< HEAD
            echo 'Check the stage above for the error.'
            echo '========================================'
=======
            echo '========================================'
            echo 'Check the failed stage above.'
>>>>>>> 3ab668a (edited Jenkins file for docker build)
        }
    }
}
