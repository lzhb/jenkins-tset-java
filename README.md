# Java Hello World Project

这是一个简单的Java Hello World项目，使用Maven构建。

## 项目结构

```
jenkins-test-java/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── example/
│                   └── HelloWorld.java
├── pom.xml
└── README.md
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

## 输出
程序运行后会输出：
```
Hello, World!
This is a simple Java Hello World project.
```