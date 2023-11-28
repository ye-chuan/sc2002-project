# Welcome to our Camp Application and Management System (CAMs)

![](C:\Users\elain\AppData\Roaming\marktext\images\2023-11-28-14-56-05-image.png)

CAMS is a program for students and staff to manage, view and register camps within NTU. The application acts as a centralized hub for all staff and students to check out previous, on-going and up-coming camps in NTU and for their faculty.

## Instructions to compile and run our program using command prompt manually

- Open command prompt

- cd into the diretory where this git repo is in

- **<u>For Unix devices</u>**
  
  - **To compile paste the follow:** javac -encoding utf8 -classpath "lib/commons-collections4-4.1.jar:lib/poi-3.17.jar:lib/poi-ooxml-3.17.jar:lib/poi-ooxml-schemas-3.17.jar:lib/xmlbeans-2.6.0.jar" -d bin -sourcepath src src/scs3grp5/Main.java
  
  - **To run the program, paste the following:** java -classpath "bin:lib/commons-collections4-4.1.jar:lib/poi-3.17.jar:lib/poi-ooxml-3.17.jar:lib/poi-ooxml-schemas-3.17.jar:lib/xmlbeans-2.6.0.jar" scs3grp5/Main

- **<u>For Windows devices</u>**
  
  - **To compile paste the following:** javac -encoding utf8 -classpath "lib/commons-collections4-4.1.jar;lib/poi-3.17.jar;lib/poi-ooxml-3.17.jar;lib/poi-ooxml-schemas-3.17.jar;lib/xmlbeans-2.6.0.jar" -d bin -sourcepath src src/scs3grp5/Main.java
  
  - **To run the program, paste the following:** java -classpath "bin;lib/commons-collections4-4.1.jar;lib/poi-3.17.jar;lib/poi-ooxml-3.17.jar;lib/poi-ooxml-schemas-3.17.jar;lib/xmlbeans-2.6.0.jar" scs3grp5/Main