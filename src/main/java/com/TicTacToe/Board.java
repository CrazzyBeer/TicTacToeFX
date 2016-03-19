package com.TicTacToe;

public class Board {
	//Board [i][0] and [0][i] shows the amount of X's in each row and column
	private int[][] board;
	private int test;
	
	public Board() {
		board = new int[4][4];
		
	}
	
	public Board(int[][] board) {
		this.board = board;
	}
	
	//X = 1, O = 2
	public void activate(int i, int j, int val) {
		board[i][j] = val;
		if (val == 1) {
			board[0][j]++;
			board[i][0]++;
		}
		
	}
	
	public int getValue(int i, int j) {
		return board[i][j];
	}
	
	public int checkResult() {
		for (int i = 1; i <= 3; i++) {
			if (board[i][0] == 3 || board[0][i] == 3) return 1;
			else if (board[i][0] == 0 || board[0][i] == 0) return 2;
		}
		return 0;
	}
	
	public int[][] getBoard() {
		return board;
	}
	
	public void setBoard(int[][] board) {
		this.board = board;
	}
	
	public Board getCopy() {
		Board b = new Board(board);
		return b;
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
	
}
