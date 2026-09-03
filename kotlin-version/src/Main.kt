import SudokuSolver.*

fun main(args: Array<String>)
{
    var solver: SudokuSolver.BaseSolver = SudokuSolver.RecursiveSolver()

    solver.load_sudoku("010203040000150000060007032801540060050800070600000020002630000000000008000014000")
    solver.print_sudoku_str()
    println()
    solver.print_sudoku()
    println()
    solver.solve()
    println()
    solver.print_sudoku()
    println()
    solver.print_sudoku_str()

    println()

    solver = SudokuSolver.IterativeSolver()

    solver.load_sudoku("010203040000150000060007032801540060050800070600000020002630000000000008000014000")
    solver.print_sudoku_str()
    println()
    solver.print_sudoku()
    println()
    solver.solve()
    println()
    solver.print_sudoku()
    println()
    solver.print_sudoku_str()
}