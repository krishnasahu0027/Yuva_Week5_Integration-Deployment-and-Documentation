@echo off
setlocal
cd /d "%~dp0\.."
if not exist out mkdir out
echo Compiling Library Book Inventory...
javac -d out src\main\java\Book.java src\main\java\LibraryManager.java src\main\java\Main.java
if errorlevel 1 (
  echo Compilation failed. Check that a JDK is installed and javac is on PATH.
  exit /b 1
)
echo Starting application...
java -cp out Main
endlocal
