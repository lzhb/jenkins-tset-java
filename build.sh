#!/bin/bash

# Jenkins Build Script for Java Hello World Project
# This script can be used for local testing or as part of Jenkins pipeline

set -e  # Exit on any error

echo "=========================================="
echo "Starting Java Hello World Build Process"
echo "=========================================="

# Print environment information
echo "Build Information:"
echo "- Date: $(date)"
echo "- User: $(whoami)"
echo "- Working Directory: $(pwd)"
echo "- Java Version:"
java -version
echo "- Maven Version:"
mvn -version

echo ""
echo "=========================================="
echo "Step 1: Cleaning previous builds"
echo "=========================================="
mvn clean

echo ""
echo "=========================================="
echo "Step 2: Compiling source code"
echo "=========================================="
mvn compile

echo ""
echo "=========================================="
echo "Step 3: Running tests"
echo "=========================================="
mvn test

echo ""
echo "=========================================="
echo "Step 4: Packaging application"
echo "=========================================="
mvn package -DskipTests

echo ""
echo "=========================================="
echo "Step 5: Testing packaged application"
echo "=========================================="
if [ -f "target/hello-world-1.0.0.jar" ]; then
    echo "JAR file created successfully!"
    echo "Testing application output:"
    java -jar target/hello-world-1.0.0.jar
else
    echo "ERROR: JAR file not found!"
    exit 1
fi

echo ""
echo "=========================================="
echo "Build completed successfully!"
echo "=========================================="
echo "Artifacts created:"
ls -la target/*.jar 2>/dev/null || echo "No JAR files found"

echo ""
echo "Build Summary:"
echo "- Source compiled: ✓"
echo "- Tests passed: ✓"
echo "- JAR packaged: ✓"
echo "- Application tested: ✓"