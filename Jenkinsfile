pipeline {
    agent any
    
    environment {
        // 定义环境变量
        MAVEN_OPTS = '-Xmx1024m'
        // 使用系统Java路径（macOS Homebrew安装的Java）
        JAVA_HOME = '/opt/homebrew/opt/openjdk@11'
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }
        
        stage('Build Info') {
            steps {
                echo "Building ${env.JOB_NAME} - ${env.BUILD_NUMBER}"
                echo "Branch: ${env.BRANCH_NAME ?: 'main'}"
                sh 'java -version'
                sh 'mvn -version'
            }
        }
        
        stage('Clean') {
            steps {
                echo 'Cleaning previous builds...'
                sh 'mvn clean'
            }
        }
        
        stage('Compile') {
            steps {
                echo 'Compiling the project...'
                sh 'mvn compile'
            }
        }
        
        stage('Test') {
            steps {
                echo 'Running tests...'
                sh 'mvn test'
            }
            post {
                always {
                    // 发布测试结果（如果有测试）
                    publishTestResults testResultsPattern: 'target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Package') {
            steps {
                echo 'Packaging the application...'
                sh 'mvn package -DskipTests'
            }
        }
        
        stage('Archive Artifacts') {
            steps {
                echo 'Archiving artifacts...'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                archiveArtifacts artifacts: 'pom.xml', fingerprint: true
            }
        }
        
        stage('Run Application') {
            steps {
                echo 'Testing the packaged application...'
                sh 'java -jar target/hello-world-1.0.0.jar'
            }
        }
    }
    
    post {
        always {
            echo 'Pipeline completed!'
            // 清理工作空间（可选）
            cleanWs()
        }
        success {
            echo 'Build succeeded!'
            // 可以添加成功通知
        }
        failure {
            echo 'Build failed!'
            // 可以添加失败通知
        }
        unstable {
            echo 'Build is unstable!'
        }
    }
}