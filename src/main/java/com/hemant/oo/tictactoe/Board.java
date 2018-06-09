package com.hemant.oo.tictactoe;


public class Board {

	public static final int ROWS = 3;
	
	public static final int COLS = 3;
	
	Cell[][] cells;
	
	int currentRow, currentCol;
	
	public Board() {
		cells = new Cell[ROWS][COLS];
		for(int row = 0 ; row < ROWS ; row++) {
			for(int col = 0 ; col < COLS;  col++) {
				cells[row][col] = new Cell(row,col);
			}
		}
	}
	
	public void init() {
		for(int row = 0 ; row < ROWS ; row++) {
			for(int col = 0 ; col < COLS;  col++) {
				cells[row][col].clear();
			}
		}
	}
	
	public boolean isDraw() { 
		for(int row = 0 ; row < ROWS ; row++) {
			for(int col = 0; col < COLS; col++) {
				if (cells[row][col].content == ContentType.EMPTY) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean hasWon(ContentType content) {
		return (
				// Current row is same.
				(cells[currentRow][0].content == content && 
				cells[currentRow][1].content == content &&
				cells[currentRow][2].content == content) 
				// Current column is same.
				|| (cells[0][currentCol].content == content && 
					cells[1][currentCol].content == content && 
					cells[2][currentCol].content == content) 
				// Diagonal
				|| (currentRow == currentCol && 
					cells[0][0].content == content && 
					cells[1][1].content == content &&
					cells[2][2].content == content) 
				// reverse diagonal.
				|| (currentRow + currentCol == 2 && 
					cells[0][2].content == content && 
					cells[1][1].content == content &&
					cells[2][0].content == content));
	}
	
	/** Paint itself */
	   public void paint() {
	      for (int row = 0; row < ROWS; ++row) {
	         for (int col = 0; col < COLS; ++col) {
	            cells[row][col].paint();   // each cell paints itself
	            if (col < COLS - 1) System.out.print("|");
	         }
	         System.out.println();
	         if (row < ROWS - 1) {
	            System.out.println("-----------");
	         }
	      }
	   }
}
