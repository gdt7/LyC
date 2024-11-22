@echo off
echo Compiling test.txt
java "-jar" "target\lyc-compiler-2.0.0.jar" "target\input\test.txt"

REM Verifica si el archivo final.asm fue generado correctamente
IF NOT EXIST "target\output\final.asm" (
    echo Error: El archivo final.asm no se generó.
    pause
    exit /b 1
)

REM Copia el archivo final.asm a la carpeta target\asm
COPY "target\output\final.asm" "target\asm\TASM\final.asm"

REM Cambia a la carpeta donde está final.asm
cd target\asm

REM Inicia DOSBox para compilar y enlazar el archivo final.asm
echo Compilando y enlazando final.asm con DOSBox...
"C:\Program Files (x86)\DOSBox-0.74-3\DOSBox.exe" -c "mount c .\target\asm" -c "c:" -c "cd TASM" -c "tasm final.asm" -c "if errorlevel 1 echo Error en compilacion con TASM" -c "tlink final.obj" -c "if errorlevel 1 echo Error en enlace con TLINK" -c "exit"

REM Verifica si se generó el archivo ejecutable en el subdirectorio TASM
IF NOT EXIST "target\asm\TASM\final.exe"" (
    echo Error: No se generó el archivo final en target\asm\TASM.
    pause
    exit /b 1
)



echo Compilación y enlace completados exitosamente.
pause
