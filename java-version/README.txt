That's my Java version of my C++ sudoku solver.

It was my first experiment with Java, just messing around... just wanted to try Java with real project, with usage of CLI tools of JDK. 
(Notice: I was hater of Java...)
So I thought about what could be if I get my C++ code and paste it to Java files.

There were some obvious differences between Java and C++ which made me fix and adapt my code to another language (although Java has C-like syntax)
1. ONLY CLASSES. There's no namespaces. There's no structs. Only classes. I know that there are records in Java but I didn't want to use some local "alternatives"...
2. "boolean" instead of "bool"... And in Java, there's no possibility of bool -> int conversion, so it isn't possible to say that "true is 1, false is 0" but "true is true, false is false"... I hate it.
3. Lack of pointers in Java. 

I made this version using Notepad++ code editor. Compiled using javac and jar.

Some tips:
To execute project: "java -jar solver.jar"
To recompile project: "javac *.java SudokuSolver/*.java", then "jar --create --file solver.jar --main-class Main.main Main.class SudokuSolver/*.class"

To conclude, I would say that my Java code would look like C++/low-level code just because of my C++/low-level pervert mind.

Good luck.