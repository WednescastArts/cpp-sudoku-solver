# Sudoku Solver C++ project


This is my <u>experimental</u> sudoku solver project written in **C++**.

Created on **Linux Ubuntu Desktop** in <u>Raspberry Pi 5</u> *(w/ GNU GCC/g++)*
Also **works on Windows**. *(checked w/ Bloodshed <u>Dev-C++</u> IDE)*

---

The project contains:

- C++ static library implementing SudokuSolver namespace.
  
- 1. *BaseSolver* class - <u>abstract class</u> implementing common variables (i.e. 9x9 table) and necessary methods (i.e. checking empty cell, load sudoku etc.)
    
  2. **RecursiveSolver** class - solve method's recursive implementation using *<u>backtracking/**Depth-First Search**</u>*.
    
  3. **IterativeSolver** class - solve method's iterative implementation using recursion mechanism knowledge (in fact, mechanism through <u>function calling stack</u>) and custom stack of so-called *"checkpoints"* <u>(raw filled cells with digit)</u>
    
- *main.cpp* file w/ tests.
  

---

How to use it?

1. You need to adapt sudoku table to one format. Mark empty cells with zeros, then merge every row into a single string. 
  For example, this table:
  x1x 2x3 x4x
  xxx 15x xxx
  x6x xx7 x32
  8x1 54x x6x
  x5x 8xx x7x
  6xx xxx x2x
  xx2 63x xxx
  xxx xxx xx8
  xxx x14 xxx
  should be imported in such form:
  010203040000150000060007032801540060050800070600000020002630000000000008000014000
  
2. Choose solving method: recursive or iterative.
  
3. **Profit!**
  

---

Written in 2026. Published on June 2, 2026.

Enjoy!
