@echo off
echo "This Batch file runs all Tests..."
echo "Compiling all java source files..."
javac *.java

echo "Running tests and appending results to AUnitTests.txt..."
java TestLexer.java > AUnitTests.txt
java TestParser.java >> AUnitTests.txt
java TestLineStatement.java >> AUnitTests.txt
java TestLexerSymbolTable.java >> AUnitTests.txt

echo "Running AUnit..."
java aunit AUnitTests.txt

echo "Running TestListingGenerator, (which is not an AUnit test) ***REVIEW OUTPUT ON StubbedIR.lst MANUALLY!!!!"
echo "Done!"
pause