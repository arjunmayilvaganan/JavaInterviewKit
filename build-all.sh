#!/bin/bash

# Set the base directory of the project
BASE_DIR=$(dirname "$0")

# Find all Java files and compile them
echo "Compiling all Java files in the project..."
find "$BASE_DIR/src" -name "*.java" > sources.txt

# Compile the Java files
javac -d "$BASE_DIR/out" @sources.txt

# Check if the compilation was successful
if [ $? -eq 0 ]; then
    echo "Build successful. Compiled classes are in the 'out' directory."
    rm sources.txt
    exit 0
else
    echo "Build failed. Check the errors above."
    rm sources.txt
    exit 1
fi
