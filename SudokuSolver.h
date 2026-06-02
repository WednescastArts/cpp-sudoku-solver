#pragma once

#include <string>
#include <stack>
using namespace std;

namespace SudokuSolver
{
	class BaseSolver
	{	
	protected:
		int table[9][9];
		
		bool solved = false;
		bool impossible = false;
		
		bool is_valid(int digit, int x, int y);
		void find_empty(int* fx, int* fy, bool* found);

	public:
		void load_sudoku(string input);
		void print_sudoku();
		void print_sudoku_str();

		virtual void solve() = 0;
		virtual ~BaseSolver() = default;
	};

	class RecursiveSolver : public BaseSolver
	{
	public:
		void solve() override;
	};

	class IterativeSolver : public BaseSolver
	{
	private:
		typedef struct Checkpoint {
			int digit;
			int x;
			int y;
		} Checkpoint;
		
		stack<Checkpoint> st;

	public:
		void solve() override;
	};
}
