@echo off
title Camp Application and Management System (CAMs)
echo compiling...
javac -encoding utf8 -classpath "lib/commons-collections4-4.1.jar;lib/poi-3.17.jar;lib/poi-ooxml-3.17.jar;lib/poi-ooxml-schemas-3.17.jar;lib/xmlbeans-2.6.0.jar" -d bin -sourcepath src src/scs3grp5/Main.java
:loop
IF %ErrorLevel% EQU 0 java -classpath "bin;lib/commons-collections4-4.1.jar;lib/poi-3.17.jar;lib/poi-ooxml-3.17.jar;lib/poi-ooxml-schemas-3.17.jar;lib/xmlbeans-2.6.0.jar" scs3grp5/Main
pause
goto loop


