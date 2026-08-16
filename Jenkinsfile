pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                echo '========================================'
                echo 'CHECKOUT'
                echo '========================================'

                checkout scm
            }
        }

        stage('Maven Build') {
            steps {
                echo '========================================'
                echo 'MAVEN BUILD'
                echo '========================================'

                sh '''
                    export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
                    export PATH="$JAVA_HOME/bin:/usr/bin:/bin"

                    echo "JAVA_HOME=$JAVA_HOME"

                    echo "----- Java -----"
                    which java
                    readlink -f "$(which java)"
                    java -version

                    echo "----- Javac -----"
                    which javac
                    javac -version

                    echo "----- Maven -----"
                    which mvn
                    mvn -version

                    echo "----- Maven Build -----"
                    mvn clean package \
                        -DskipTests \
                        -Dmaven.test.skip=true
                '''
            }
        }

        stage('JUnit Tests') {
            steps {
                echo '========================================'
                echo 'JUNIT TESTS'
                echo '========================================'
                echo 'JUnit tests intentionally skipped.'
            }
        }

        stage('SonarQube') {
            steps {
                echo '========================================'
                echo 'SONARQUBE'
                echo '========================================'
                echo 'SonarQube intentionally skipped for now.'
            }
        }

        stage('OWASP Dependency Check') {
            steps {
                echo '========================================'
                echo 'OWASP DEPENDENCY CHECK'
                echo '========================================'
                echo 'OWASP Dependency Check temporarily skipped.'
            }
        }

        stage('Docker Build') {
            steps {
                echo '========================================'
                echo 'DOCKER BUILD'
                echo '========================================'
                echo 'Docker Build temporarily skipped.'
            }
        }

        stage('Trivy Scan') {
            steps {
                echo '========================================'
                echo 'TRIVY SCAN'
                echo '========================================'
                echo 'Trivy Scan temporarily skipped.'
            }
        }
    }

    post {

        success {
            echo '========================================'
            echo 'PIPELINE SUCCESS'
            echo '========================================'
            echo 'Pipeline completed successfully.'
        }

        failure {
            echo '========================================'
            echo 'PIPELINE FAILED'
            echo '========================================'
            echo 'Check the failed stage in the Jenkins console.'
        }

        always {
            echo '========================================'
            echo 'PIPELINE FINISHED'
            echo '========================================'
        }
    }
}
