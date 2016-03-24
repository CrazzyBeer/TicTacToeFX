package com.TicTacToe.Helper_Classes;

public class Board {
	//Board [i][0] and [0][i] shows the amount of X's in each row and column
	private int[][] board;
	private int[][] color;
	private int totalCells;
	public Board() {
		
		board = new int[4][4];
		color = new int[4][4];
		totalCells = 0;
	}
	
	//X = 1, O = 2
	public void activate(int i, int j, int val) {
		board[i][j] = val;
		color[i][j] = 1;
		totalCells++;
	}
	public void deactivate(int i, int j) {
		board[i][j] = 0;
		color[i][j] = 0;
		totalCells--;
	}
	
	public int getValue(int i, int j) {
		return board[i][j];
	}
	
	public int getColor(int i, int j) {
		return color[i][j];
	}
	
	public int checkResult() {

		for (int i = 1; i <= 3; i++) {
			if (board[i][1] == 1 && board[i][2] == 1 && board[i][3] == 1) {
				setWinning(i+3);
				return 1;
			}
			if (board[1][i] == 1 && board[2][i] == 1 && board[3][i] == 1) {
				setWinning(i);
				return 1;
			}
			if (board[i][1] == 2 && board[i][2] == 2 && board[i][3] == 2) {
				setWinning(i+3);
				return 2;
			}
			if (board[1][i] == 2 && board[2][i] == 2 && board[3][i] == 2) {
				setWinning(i);
				return 2;
			}
		}
		if (board[1][1] == 1 && board[2][2] == 1 && board[3][3] == 1) {
			setWinning(7);
			return 1;
		}
		if (board[1][3] == 1 && board[2][2] == 1 && board[3][1] == 1) {
			setWinning(8);
			return 1;
		}
		if (board[1][1] == 2 && board[2][2] == 2 && board[3][3] == 2) {
			setWinning(7);
			return 2;
		}
	    if (board[1][3] == 2 && board[2][2] == 2 && board[3][1] == 2) {
			setWinning(8);
			return 2;
		}
	    
		if (totalCells == 9) return 3;
		return 0;
	}

	public void setWinning(int streak) {
		
		if (streak < 4) { for (int i = 1; i <= 3; i++) color[i][streak] = 2; }
		else if (streak < 7) { for (int j = 1; j <= 3; j++) color[streak-3][j] = 2; }
		else if (streak == 7) { color[1][1] = 2; color[2][2] = 2; color[3][3] = 2; }
		else if (streak == 8) { color[1][3] = 2; color[2][2] = 2; color[3][1] = 2; }
	}
	
	public int[][] getBoard() {
		return board;
	}
	
	public int[][] getColors() {
		return color;
	}
	
	public void setBoard(int[][] board) {
		for (int i = 0; i<=3; i++) 
			for (int j = 0; j<=3; j++)
				this.board[i][j] = board[i][j];
	}
	
	public void setColor(int[][] color) {
		for (int i = 0; i<=3; i++) 
			for (int j = 0; j<=3; j++)
				this.color[i][j] = color[i][j];
	}
	
	@Override 
	public String toString() {
		String res = "";
		for (int i = 1; i <= 3; i++) {
			for (int j = 1; j<=3; j++) {
				res += board[i][j] + " ";
			}
			res += "\n";
		}
		return res;
	}

	public int getTotalCells() {
		return totalCells;
	}

	public void setTotalCells(int totalCells) {
		this.totalCells = totalCells;
	}
	
	
	
}
