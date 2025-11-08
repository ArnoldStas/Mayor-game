#!/bin/bash
# Build script for City Mayor Game

echo "Building City Mayor Game..."

# Clean output directory
rm -rf out
mkdir out

# Compile
javac -d out src/*.java

if [ $? -eq 0 ]; then
    echo "✓ Build successful!"
    echo ""
    echo "To run the game:"
    echo "  java -cp out Main"
else
    echo "✗ Build failed!"
    exit 1
fi
