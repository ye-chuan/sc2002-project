# Welcome to our Camp Application and Management System

```
  .g8"""bgd     db      `7MMM.     ,MMF' .M"""bgd
.dP'     `M    ;MM:       MMMb    dPMM  ,MI    "Y
dM'       `   ,V^MM.      M YM   ,M MM  `MMb.    
MM           ,M  `MM      M  Mb  M' MM    `YMMNq.
MM.          AbmmmqMA     M  YM.P'  MM  .     `MM
`Mb.     ,' A'     VML    M  `YM'   MM  Mb     dM
  `"bmmmd'.AMA.   .AMMA..JML. `'  .JMML.P"Ybmmd"
```

CAMS is a program for students and staff to manage, view and register camps within NTU. The application acts as a centralized hub for all staff and students to check out previous, ongoing and upcoming camps in NTU and for their faculty.

## Instructions to compile and run our program using command prompt manually
`cd` into the directory where this git repo is in

#### For Unix-like
- Compile: `javac -encoding utf8 -classpath "lib/commons-collections4-4.1.jar:lib/poi-3.17.jar:lib/poi-ooxml-3.17.jar:lib/poi-ooxml-schemas-3.17.jar:lib/xmlbeans-2.6.0.jar" -d bin -sourcepath src src/scs3grp5/Main.java`
- Run: `java -classpath "bin:lib/commons-collections4-4.1.jar:lib/poi-3.17.jar:lib/poi-ooxml-3.17.jar:lib/poi-ooxml-schemas-3.17.jar:lib/xmlbeans-2.6.0.jar" scs3grp5/Main`

#### For Windows
- Compile: `javac -encoding utf8 -classpath "lib/commons-collections4-4.1.jar;lib/poi-3.17.jar;lib/poi-ooxml-3.17.jar;lib/poi-ooxml-schemas-3.17.jar;lib/xmlbeans-2.6.0.jar" -d bin -sourcepath src src/scs3grp5/Main.java`
- Run: `java -classpath "bin;lib/commons-collections4-4.1.jar;lib/poi-3.17.jar;lib/poi-ooxml-3.17.jar;lib/poi-ooxml-schemas-3.17.jar;lib/xmlbeans-2.6.0.jar" scs3grp5/Main`

## Important Directories
- `doc/` - Javadoc for this program.  
- `data/` - .xlsx files to be read by the program.  
- `src/` - our codes and packages.  
- `lib/` - external jar files used.  