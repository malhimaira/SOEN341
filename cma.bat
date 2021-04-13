@echo off
echo.
::booleans to check what to give back to the user depending on input
set notation=false
set help=false
set verbose=false
set banner=false
set listing=false
set validOption=false

::Checking how many arguments the user is giving
set /A numArg=0
for %%x in (%*) do (
   set /A numArg=numArg+1
)

::If the user give too many arguments, they will be told that they gave too many
::They'll be shown what notation of how they have to write it
if %numArg% gtr 2 (
    set notation=true
    set validOption=true
    echo There is too many arguments ^(max 2^)
)

::If the user give no argument, they will receive the possible action of cma
::They'll be shown what notation of how they have to write it
if %numArg% equ 0 (
    set notation=true
     set validOption=true
    echo Please precise command
    )

if [%1]==[-h] (
    set help=true
    set validOption=true)
if [%1]==[-help] (
    set help=true
    set validOption=true)

if [%1]==[-v] (
    set verbose=true
    set validOption=true)
if [%1]==[-verbose] (
    set verbose=true
    set validOption=true)

if [%1]==[-b] (
    set banner=true
    set validOption=true)
if [%1]==[-banner] (
    set banner=true
    set validOption=true)

if [%1]==[-l] (
    set listing=true
    set validOption=true)
if [%1]==[-listing] (
    set listing=true
    set validOption=true)

::Happens when the user give an invalid option
if [%validOption%]==[false] (
    set notation=true
    echo Invalid Option
)

if [%notation%]==[true] (
    echo -----------------------------------------------
    echo CmCommand      = "cma" [ Options ] ^<file^>.asm & echo.
    echo Option         = HelpOption ^| VerboseOption ^| BannerOption ^| ListingOption . & echo.
    echo HelpOption     = "-h" ^| "-help" .
    echo VerboseOption  = "-v" ^| "-verbose" .
    echo BannerOption   = "-b" ^| "-banner" .
    echo ListingOption  = "-l" ^| "-listing" .
)

if [%help%]==[true] (
    echo Usage: cma [ Options ] ^<file^>.asm & echo.
    echo where options are: & echo.
    echo Short version  Long version    Meaning
    echo -h             -help           Print the usage of the program.
    echo -v             -verbose        Verbose during the execution of the program.
    echo -b             -banner         Print the banner of the program.
    echo -l             -listing        Generate a listing of the assembly file. & echo.
    echo "C:\> cma -listing Pgm.asm (Generate Pgm.exe and Pgm.lst)" & echo.
    echo "C:\> cma Pgm.asm (Generate Pgm.exe only)"
)

if [%verbose%]==[true] (
    echo verbose to be implemented
)

if [%banner%]==[true] (
    echo Cm Cross-Assembler Version 4.1 - Developed by Team 2.
)

if [%listing%]==[true] (
    echo listing to be implemented
)

echo.
exit /b
