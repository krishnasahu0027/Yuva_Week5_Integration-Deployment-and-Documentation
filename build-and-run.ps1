$ErrorActionPreference = "Stop"
Set-Location (Join-Path $PSScriptRoot "..")
if (-not (Test-Path "out")) { New-Item -ItemType Directory -Path "out" | Out-Null }
Write-Host "Compiling Library Book Inventory..."
javac -d out src/main/java/Book.java src/main/java/LibraryManager.java src/main/java/Main.java
if ($LASTEXITCODE -ne 0) {
    throw "Compilation failed. Check that a JDK is installed and javac is on PATH."
}
Write-Host "Starting application..."
java -cp out Main
