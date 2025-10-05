@echo off
title Student Mini Job Platform - Java 25
color 0A

echo.
echo ================================================
echo    Student Mini Job Platform
echo    Starting with Java 25 + JavaFX 25
echo ================================================
echo.

REM تعيين المسارات
set JAVA_HOME=C:\Program Files\Java\jdk-25
set JAVAFX_LIB=C:\Program Files\javafx-sdk-25\lib
set PROJECT_LIB=lib
set PATH=%JAVA_HOME%\bin;%PATH%

REM عرض الإصدار
echo [INFO] Java Version:
java -version
echo.

REM التحقق من JavaFX
if not exist "%JAVAFX_LIB%" (
    echo [ERROR] JavaFX SDK not found at: %JAVAFX_LIB%
    pause
    exit /b 1
)

echo [INFO] JavaFX SDK found at: %JAVAFX_LIB%

REM التحقق من مكتبات المشروع
if not exist "%PROJECT_LIB%" (
    echo [WARNING] Project lib folder not found
    echo [INFO] Using system JavaFX only
) else (
    echo [INFO] Project libraries found at: %PROJECT_LIB%
)
echo.

REM إعداد classpath مع مكتبات المشروع
set CP=%PROJECT_LIB%\*

REM الترجمة إذا لزم
if not exist "bin" (
    echo [INFO] Compiling project...
    mkdir bin
    
    javac --module-path "%JAVAFX_LIB%" ^
          --add-modules javafx.controls,javafx.fxml,javafx.graphics ^
          -cp "%CP%" ^
          -d bin -encoding UTF-8 -sourcepath src ^
          src/main/MainApp.java ^
          src/main/admin/*.java ^
          src/main/employer/*.java ^
          src/main/student/*.java ^
          src/main/models/*.java
    
    if errorlevel 1 (
        echo.
        echo [ERROR] Compilation failed!
        echo Check the error messages above.
        pause
        exit /b 1
    )
    
    echo [SUCCESS] Compilation completed!
    echo.
)

REM تشغيل التطبيق
echo [INFO] Launching application...
echo.

java --module-path "%JAVAFX_LIB%;%PROJECT_LIB%" ^
     --add-modules javafx.controls,javafx.fxml,javafx.graphics ^
     -Dfile.encoding=UTF-8 ^
     -cp "bin;%CP%" main.MainApp

if errorlevel 1 (
    echo.
    echo [ERROR] Application failed to start!
    echo.
    echo Possible issues:
    echo - JavaFX modules not loaded
    echo - Missing dependencies
    echo - Runtime errors
    echo.
    echo Check error messages above.
)

echo.
echo ================================================
echo    Application Closed
echo ================================================
pause