#include <iostream>
#include <string>
#include <stack>
#include "SudokuSolver.h"
using namespace std;

bool SudokuSolver::BaseSolver::is_valid(int digit, int x, int y)
{
	if (digit == 0)
		return true;
				
	for (int i = 0; i < 9; i++)
	{
		if (i != y && digit == this->table[i][x])
			return false;
	}
			
	for (int i = 0; i < 9; i++)
	{
		if (i != x && digit == this->table[y][i])
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
						
			if (digit == this->table[vy][vx])
				return false;
		}
	}
	
	return true;
}
		
void SudokuSolver::BaseSolver::find_empty(int* fx, int* fy, bool* found)
{
	for (int y = 0; y < 9; y++)
	{
		for (int x = 0; x < 9; x++)
		{
			if (this->table[y][x] == 0)
			{
				*fx = x;
				*fy = y;
				*found = true;
				break;
			}
		}
				
		if (*found)
			break;
	}
}

void SudokuSolver::BaseSolver::load_sudoku(string input)
{
	for (int i = 0; i < 81; i++)
	{
		this->table[i / 9][i - (i / 9) * 9] = input[i] - '0';
	}
}
		
void SudokuSolver::BaseSolver::print_sudoku()
{
	for (int i = 0; i < 9; i++)
	{
		if (i != 0 && i % 3 == 0)
		{
			for (int j = 0; j < 9; j++)
			{
				if (j != 0 && j % 3 == 0)
					cout << "+";
				cout << "-";
			}
			cout << endl;
		}
				
		for (int j = 0; j < 9; j++)
		{
			if (j != 0 && j % 3 == 0)
				cout << "|";
			cout << this->table[i][j];
		}
		cout << endl;
	}
}
		
void SudokuSolver::BaseSolver::print_sudoku_str()
{
	for (int i = 0; i < 9; i++)
	{
		for (int j = 0; j < 9; j++)
		{
			cout << this->table[i][j];
		}
	}
	cout << endl;
}

void SudokuSolver::RecursiveSolver::solve()
{
	int free_x = 0;
	int free_y = 0;
	bool found_empty = false;
			
	this->find_empty(&free_x, &free_y, &found_empty);
			
	if (!found_empty)
	{
		this->solved = true;
		cout << "Solved!" << endl;
		return;
	}
			
	for (int i = 1; i <= 9; i++)
	{
		if (this->is_valid(i, free_x, free_y))
		{
			this->table[free_y][free_x] = i;
			this->solve();
			if (!(this->solved))
				this->table[free_y][free_x] = 0;
		}
	}
}

void SudokuSolver::IterativeSolver::solve()
{
	int start_digit = 1;
			
	int free_x = 0;
	int free_y = 0;
	bool found_first_empty = false;
			
	this->find_empty(&free_x, &free_y, &found_first_empty);
			
	while (!(this->solved) && !(this->impossible))
	{
		while (start_digit <= 9 && !(this->is_valid(start_digit, free_x, free_y)))
			start_digit++;
				
		if (start_digit <= 9)
		{
			this->table[free_y][free_x] = start_digit;
			(this->st).push({start_digit, free_x, free_y});
			start_digit = 1;
					
			bool found_empty = false;
			
			this->find_empty(&free_x, &free_y, &found_empty);
					
			if (!found_empty)
			{
				this->solved = true;
				while (!((this->st).empty()))
					(this->st).pop();
				cout << "Solved!" << endl;
			}
		}
		else
		{
			if ((this->st).empty())
			{
				this->impossible = true;
				cout << "Impossible!" << endl;
			}
			else
			{
				Checkpoint rev = (this->st).top();
				(this->st).pop();
				free_x = rev.x;
				free_y = rev.y;
				start_digit = rev.digit + 1;
				this->table[free_y][free_x] = 0;
			}
		}
	}
}
