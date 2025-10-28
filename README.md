# Java Hello World Project

这是一个简单的Java Hello World项目，使用Maven构建，支持Jenkins CI/CD流水线。

## 项目结构

```
jenkins-test-java/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── example/
│   │               └── HelloWorld.java
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── HelloWorldTest.java
├── Jenkinsfile              # Jenkins流水线配置
├── jenkins.properties       # Jenkins构建属性
├── build.sh                # 构建脚本
├── Dockerfile              # Docker容器配置
├── .dockerignore           # Docker忽略文件
├── pom.xml                 # Maven配置
├── .gitignore              # Git忽略文件
└── README.md               # 项目文档
```

## 构建和运行

### 前提条件
- Java 11 或更高版本
- Maven 3.6 或更高版本

### 编译项目
```bash
mvn compile
```

### 打包成JAR文件
```bash
mvn package
```

### 运行JAR文件
```bash
java -jar target/hello-world-1.0.0.jar
```

### 直接运行
```bash
mvn exec:java -Dexec.mainClass="com.example.HelloWorld"
```

## Jenkins CI/CD 支持

### Jenkins 流水线功能
- ✅ 自动代码检出
- ✅ Maven编译构建
- ✅ 单元测试执行
- ✅ JAR包打包
- ✅ 构件归档
- ✅ 应用测试运行
- ✅ 构建状态通知

### Jenkins 配置要求
在Jenkins中需要配置以下工具：
- **Maven**: 版本 3.9.0 或更高，工具名称: `Maven-3.9.0`
- **JDK**: Java 11，工具名称: `JDK-11`

### 使用Jenkins构建
1. 在Jenkins中创建新的Pipeline项目
2. 配置Git仓库地址
3. 选择"Pipeline script from SCM"
4. 指定Jenkinsfile路径（项目根目录）
5. 保存并运行构建

### 本地构建脚本
```bash
# 使用构建脚本
./build.sh

# 或者手动执行Maven命令
mvn clean compile test package
```

### Docker 支持
```bash
# 构建Docker镜像
docker build -t hello-world-java .

# 运行Docker容器
docker run hello-world-java
```

## 输出
程序运行后会输出：
```
Hello, World!
This is a simple Java Hello World project.
```

## 测试
项目包含单元测试，验证应用程序的基本功能：
```bash
mvn test
```