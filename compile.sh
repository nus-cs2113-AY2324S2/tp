#!/bin/bash

# Define the base directory variables
SRC_DIR="src"
BUILD_DIR="build"
CLASSES_DIR="$BUILD_DIR/classes"
LIBS_DIR="$BUILD_DIR/libs"
JAR_NAME="project.jar"


# Compile the Java files
find "$SRC_DIR" -name "*.java" > sources.txt
javac -d "$CLASSES_DIR" @sources.txt
rm sources.txt

# Package the .class files into a .jar file
cd "$CLASSES_DIR"
jar cvf "../libs/$JAR_NAME" *
cd -

echo "Compilation and packaging complete."
