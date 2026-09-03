package SudokuSolver

import java.util.Stack

class SudokuSolver
{
    public abstract class BaseSolver
    {
        protected var table: Array<Array<Int>> = Array(9){Array(9){0}}

        protected var solved: Boolean = false
        protected var impossible: Boolean = false

        protected class Position(
            var x: Int = 0,
            var y: Int = 0,
            var found: Boolean = false
        )

        protected fun is_valid(digit: Int, x: Int, y: Int): Boolean
        {
            if (digit == 0)
                return true

            for (i in 0..8)
                if (i != y && digit == this.table[i][x])
                    return false

            for (i in 0..8)
                if (i != x && digit == this.table[y][i])
                    return false

            var field_x: Int = x / 3
            var field_y: Int = y / 3

            for (dy in 0..2)
            {
                for (dx in 0..2)
                {
                    var vx: Int = field_x * 3 + dx
                    var vy: Int = field_y * 3 + dy

                    if (vx == x && vy == y)
                        continue

                    if (digit == this.table[vy][vx])
                        return false
                }
            }

            return true
        }

        protected fun find_empty(): Position
        {
            for (y in 0..8)
                for (x in 0..8)
                    if (this.table[y][x] == 0)
                        return Position(x, y, true)

            return Position(0, 0, false)
        }

        public fun load_sudoku(input: String)
        {
            for (i in 0..80)
                this.table[i / 9][i - (i / 9) * 9] = input[i] - '0'
        }

        public fun print_sudoku()
        {
            for (i in 0..8)
            {
                if (i != 0 && i % 3 == 0)
                {
                    for (j in 0..8)
                    {
                        if (j != 0 && j % 3 == 0)
                            print("+")
                        print("-")
                    }
                    println()
                }

                for (j in 0..8)
                {
                    if (j != 0 && j % 3 == 0)
                        print("|")
                    print(this.table[i][j])
                }
                println()
            }
        }

        public fun print_sudoku_str()
        {
            for (i in 0..8)
                for (j in 0..8)
                    print(this.table[i][j])
            println()
        }

        public abstract fun solve()
    }

    public class RecursiveSolver : BaseSolver()
    {
        public override fun solve()
        {
            var free_pos: Position = super.find_empty()

            if (!free_pos.found)
            {
                super.solved = true
                println("Solved!")
                return
            }

            for (i in 1..9)
            {
                if (super.is_valid(i, free_pos.x, free_pos.y))
                {
                    super.table[free_pos.y][free_pos.x] = i
                    this.solve()
                    if (!super.solved)
                        super.table[free_pos.y][free_pos.x] = 0
                }
            }
        }
    }

    public class IterativeSolver : BaseSolver()
    {
        private class Checkpoint (
            var digit: Int,
            var x: Int,
            var y: Int
        )

        private var st: Stack<Checkpoint> = Stack<Checkpoint>()

        public override fun solve()
        {
            var start_digit: Int = 1
            var free_pos: Position = Position(0, 0, false)
            free_pos = super.find_empty()

            while (!super.solved && !super.impossible)
            {
                while (start_digit <= 9 && !super.is_valid(start_digit, free_pos.x, free_pos.y))
                    start_digit++

                if (start_digit <= 9)
                {
                    super.table[free_pos.y][free_pos.x] = start_digit
                    this.st.push(Checkpoint(start_digit, free_pos.x, free_pos.y))
                    start_digit = 1

                    free_pos = super.find_empty()

                    if (!free_pos.found)
                    {
                        super.solved = true
                        this.st.clear()
                        println("Solved!")
                    }
                }
                else
                {
                    if (this.st.empty())
                    {
                        super.impossible = true
                        println("Impossible!")
                    }
                    else
                    {
                        var rev: Checkpoint = this.st.pop()
                        free_pos.x = rev.x
                        free_pos.y = rev.y
                        start_digit = rev.digit + 1
                        super.table[free_pos.y][free_pos.x] = 0
                    }
                }
            }
        }
    }
}