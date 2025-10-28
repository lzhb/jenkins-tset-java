pipeline {
    agent any
    
    environment {
        // 定义环境变量
        MAVEN_OPTS = '-Xmx1024m'
        // 使用系统Java和Maven路径（macOS Homebrew安装）
        JAVA_HOME = '/opt/homebrew/opt/openjdk@11'
        // 确保包含所有必要的路径
        PATH = "/opt/homebrew/bin:/opt/homebrew/opt/openjdk@11/bin:/usr/local/bin:/usr/bin:/bin:${env.PATH}"
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }
        
        stage('Environment Check') {
            steps {
                echo 'Checking build environment...'
                sh '''
                    echo "=== Environment Information ==="
                    echo "Current PATH: $PATH"
                    echo "JAVA_HOME: $JAVA_HOME"
                    echo "Working Directory: $(pwd)"
                    echo ""
                    echo "=== Tool Locations ==="
                    which java || echo "Java not found in PATH"
                    which mvn || echo "Maven not found in PATH"
                    echo ""
                    echo "=== Homebrew Locations ==="
                    ls -la /opt/homebrew/bin/mvn || echo "Maven not found in /opt/homebrew/bin/"
                    ls -la /opt/homebrew/bin/java || echo "Java not found in /opt/homebrew/bin/"
                '''
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
                // 显示测试结果摘要
                sh '''
                    echo "=== Test Results Summary ==="
                    if [ -d "target/surefire-reports" ]; then
                        echo "Test reports directory exists"
                        ls -la target/surefire-reports/
                        echo "Test result files:"
                        find target/surefire-reports -name "*.xml" -exec basename {} \\; || echo "No XML test results found"
                    else
                        echo "No test reports directory found"
                    fi
                '''
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