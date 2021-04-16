@echo off
echo "This Batch file runs all Tests..."
echo "Compiling all java source files..."
javac *.java

echo "Running tests and appending results to AUnitTests.txt..."
java TestLexer.java > AUnitTests.txt
java TestParser.java >> AUnitTests.txt
java TestLineStatement.java >> AUnitTests.txt
java TestLexerSymbolTable.java >> AUnitTests.txt
java TestInstructionSize.java >> AunitTests.txt

echo "Running AUnit..."
java aunit AUnitTests.txt

echo "Running TestListingGenerator, (which is not an AUnit test) ***REVIEW OUTPUT ON StubbedIR.lst MANUALLY!!!!"
java TestListingGenerator

echo "Running ErrorReporter related tests (for ErrorReporter itself, Parser and Scanner) which are also not AUnit compatible, ***see ErrorReporterTests.txt***"
java TestErrorReporter.java > ErrorReporterTests.txt
java TestParserErrors.java >> ErrorReporterTests.txt
java TestErrorReporterLexer >> ErrorReporterTests.txt
echo "Done!"
pause