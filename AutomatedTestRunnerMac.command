printf "This Batch file runs all Tests...\n"
printf "Compiling all java source files...\n"
javac *.java

printf "Running tests and appending results to AUnitTests.txt...\n"
java TestLexer.java > AUnitTests.txt
java TestParser.java >> AUnitTests.txt
java TestLineStatement.java >> AUnitTests.txt
java TestLexerSymbolTable.java >> AUnitTests.txt

printf "Running AUnit...\n"
java aunit AUnitTests.txt

printf "Running TestListingGenerator, (which is not an AUnit test) ***REVIEW OUTPUT ON StubbedIR.lst MANUALLY!!!!\n"
java TestListingGenerator.java

printf "Running ErrorReporter related tests (for ErrorReporter itself, Parser and Scanner) which are also not AUnit compatible, ***see ErrorReporterTests.txt***\n"
java TestErrorReporter > ErrorReporterTests.txt
printf "Done!\n"