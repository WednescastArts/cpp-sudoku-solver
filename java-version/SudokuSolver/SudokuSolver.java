package SudokuSolver;

import java.util.Stack;

public class SudokuSolver
{
	public static abstract class BaseSolver
	{
		protected int[][] table = new int[9][9];
		
		protected boolean solved = false;
		protected boolean impossible = false;
		
		protected static class Position
		{
			int x = 0;
			int y = 0;
			boolean found = false;
			
			Position (int x, int y, boolean found)
			{
				this.x = x;
				this.y = y;
				this.found = found;
			}
		}
		
		protected boolean is_valid(int digit, int x, int y)
		{
			if (digit == 0)
				return true;
						
			for (int i = 0; i < 9; i++)
			{
				if (i != y && digit == this.table[i][x])
					return false;
			}
					
			for (int i = 0; i < 9; i++)
			{
				if (i != x && digit == this.table[y][i])
					return false;
			}
					
			int field_x = x / 3;
			int field_y = y / 3;
					
			for (int dy = 0; dy < 3; dy++)
			{
				for (int dx = 0; dx < 3; dx++)
				{
					int vx = field_x * 3 + dx;
					int vy = field_y * 3 + dy;
							
					if (vx == x && vy == y)
						continue;
								
					if (digit == this.table[vy][vx])
						return false;
				}
			}
			
			return true;
		}
		
		protected Position find_empty()
		{
			for (int y = 0; y < 9; y++)
			{
				for (int x = 0; x < 9; x++)
				{
					if (this.table[y][x] == 0)
					{
						return new Position(x, y, true);
					}
				}
			}
			
			return new Position(0, 0, false);
		}

		public void load_sudoku(String input)
		{
			for (int i = 0; i < 81; i++)
			{
				this.table[i / 9][i - (i / 9) * 9] = input.charAt(i) - '0';
			}
		}
		
		public void print_sudoku()
		{
			for (int i = 0; i < 9; i++)
			{
				if (i != 0 && i % 3 == 0)
				{
					for (int j = 0; j < 9; j++)
					{
						if (j != 0 && j % 3 == 0)
							System.out.print("+");
						System.out.print("-");
					}
					System.out.println();
				}
						
				for (int j = 0; j < 9; j++)
				{
					if (j != 0 && j % 3 == 0)
						System.out.print("|");
					System.out.print(this.table[i][j]);
				}
				System.out.println();;
			}
		}
		
		public void print_sudoku_str()
		{
			for (int i = 0; i < 9; i++)
			{
				for (int j = 0; j < 9; j++)
				{
					System.out.print(this.table[i][j]);
				}
			}
			System.out.println();
		}

		public abstract void solve();
	}
	
	public static class RecursiveSolver extends BaseSolver
	{
		public void solve()
		{
			Position free_pos = new Position(0, 0, false);
			
			free_pos = super.find_empty();
					
			if (!free_pos.found)
			{
				super.solved = true;
				System.out.println("Solved!");
				return;
			}
					
			for (int i = 1; i <= 9; i++)
			{
				if (super.is_valid(i, free_pos.x, free_pos.y))
				{
					super.table[free_pos.y][free_pos.x] = i;
					this.solve();
					if (!super.solved)
						super.table[free_pos.y][free_pos.x] = 0;
				}
			}
		}
	}
	
	public static class IterativeSolver extends BaseSolver
	{
		private static class Checkpoint {
			int digit;
			int x;
			int y;
			
			Checkpoint (int digit, int x, int y)
			{
				this.digit = digit;
				this.x = x;
				this.y = y;
			}
		}
		
		private Stack<Checkpoint> st = new Stack<>();

		public void solve()
		{
			int start_digit = 1;
			
			Position free_pos = new Position(0, 0, false);
					
			free_pos = super.find_empty();
					
			while (!super.solved && !super.impossible)
			{
				while (start_digit <= 9 && !super.is_valid(start_digit, free_pos.x, free_pos.y))
					start_digit++;
						
				if (start_digit <= 9)
				{
					super.table[free_pos.y][free_pos.x] = start_digit;
					this.st.push(new Checkpoint(start_digit, free_pos.x, free_pos.y));
					start_digit = 1;
					
					free_pos = super.find_empty();
							
					if (!free_pos.found)
					{
						super.solved = true;
						Checkpoint rem;
						while (!this.st.empty())
							rem = this.st.pop();
						System.out.println("Solved!");
					}
				}
				else
				{
					if (this.st.empty())
					{
						super.impossible = true;
						System.out.println("Impossible!");
					}
					else
					{
						Checkpoint rev = this.st.pop();
						free_pos.x = rev.x;
						free_pos.y = rev.y;
						start_digit = rev.digit + 1;
						super.table[free_pos.y][free_pos.x] = 0;
					}
				}
			}
		}
	}
}