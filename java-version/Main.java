import SudokuSolver.*;

public class Main
{
	public static void main (String[] args)
	{
		SudokuSolver.BaseSolver solver = new SudokuSolver.RecursiveSolver();
	
		solver.load_sudoku("010203040000150000060007032801540060050800070600000020002630000000000008000014000");
		solver.print_sudoku_str();
		System.out.println();
		solver.print_sudoku();
		System.out.println();
		solver.solve();
		System.out.println();
		solver.print_sudoku();
		System.out.println();
		solver.print_sudoku_str();
		
		System.out.println();
		
		solver = new SudokuSolver.IterativeSolver();
		
		solver.load_sudoku("010203040000150000060007032801540060050800070600000020002630000000000008000014000");
		solver.print_sudoku_str();
		System.out.println();
		solver.print_sudoku();
		System.out.println();
		solver.solve();
		System.out.println();
		solver.print_sudoku();
		System.out.println();
		solver.print_sudoku_str();
	}
}
