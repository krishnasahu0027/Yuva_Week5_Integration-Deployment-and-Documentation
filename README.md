# Yuva_Week5_Integration-Deployment-and-Documentation

## Project
Library Book Inventory Management System — Java command-line application.

## Requirements
- JDK 8 or newer installed.
- Windows PowerShell or Command Prompt (the source code itself is platform independent).

## Quick start (Windows)
From the project root, run either:
- Double-click `scripts/build-and-run.bat`, or
- In PowerShell: `powershell -ExecutionPolicy Bypass -File .\scripts\build-and-run.ps1`

Manual commands:
```powershell
mkdir out
javac -d out src/main/java/Book.java src/main/java/LibraryManager.java src/main/java/Main.java
java -cp out Main
```
If `out` already exists, skip the `mkdir out` command.

## Features
- Create, list, search, update, and delete book records.
- Rejects blank fields, invalid publication years, and duplicate ISBNs.
- Separates the book model, inventory logic, and console interface.
- Includes three sample books for demonstration.

## Project structure
```text
src/main/java/Book.java
src/main/java/LibraryManager.java
src/main/java/Main.java
scripts/build-and-run.bat
scripts/build-and-run.ps1
docs/Developer_Documentation.docx
docs/Developer_Documentation.md
```

## Packaging
Compile the classes into `out`, then create a runnable JAR:
```powershell
jar --create --file library-inventory.jar --main-class Main -C out .
java -jar library-inventory.jar
```
The JAR can be moved to another computer with a compatible Java runtime. The current app stores data in memory, so records reset after exit.

## Important limitation
This is a demonstration application, not a production database-backed system. It has no persistent storage, authentication, network API, or automated CI pipeline.
