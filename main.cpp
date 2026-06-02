#include <iostream>
#include <string>
#include <stack>
#include "SudokuSolver.h"

using namespace std;
using namespace SudokuSolver;

int main()
{
	BaseSolver* solver = new RecursiveSolver();
	
	solver->load_sudoku("010203040000150000060007032801540060050800070600000020002630000000000008000014000");
	solver->print_sudoku_str();
	cout << endl;
	solver->print_sudoku();
	cout << endl;
	solver->solve();
	cout << endl;
	solver->print_sudoku();
	cout << endl;
	solver->print_sudoku_str();
	
	delete solver;
	
	cout << endl;
	
	solver = new IterativeSolver();
	
	solver->load_sudoku("010203040000150000060007032801540060050800070600000020002630000000000008000014000");
	solver->print_sudoku_str();
	cout << endl;
	solver->print_sudoku();
	cout << endl;
	solver->solve();
	cout << endl;
	solver->print_sudoku();
	cout << endl;
	solver->print_sudoku_str();
	
	delete solver;
	
	return 0;
}
