package com.hemant.nonOO.tictactoe;

import java.util.Scanner;

public class TicTacToeNonOOGame {

	private static int[][] board;
	
	private static final int EMPTY = 0;
	private static final int CROSS = 1;
	private static final int NOUGHT = 2;
	
	
	private static final int PLAYING = 0;
	private static final int DRAW = 1;
	private static final int CROSS_WON = 2;
	private static final int NOUGHT_WON = 3;
	
	private static final int ROWS = 3;
	private static final int COLS = 3;
	
	private static int currentPlayer;
	private static int currentState;
	private static int currentRow;
	private static int currentCol;
	
	private static Scanner in;
	
	public static void initBoard(){
		in = new Scanner(System.in);
		currentState = PLAYING;
		currentRow = -1;
		currentCol = -1;
		currentPlayer=CROSS;
		board = new int[ROWS][COLS];
		for(int row = 0; row < ROWS ; row++) {
			for(int col = 0 ; col < COLS ; col++) {
				board[row][col] = EMPTY;
			}
		}
	}
	
	public static void main(String[] args) {
		initBoard();
		do {
			playerMove(currentPlayer);
			updateGame(currentPlayer, currentRow,currentCol);
			if(currentState == CROSS_WON) {
				System.out.println("X won");
			}
			else if(currentState == NOUGHT_WON) {
				System.out.println("0 won");
			}
			else if(currentState == DRAW) {
				System.out.println("Its a draw");
			}
		}while(currentState==PLAYING);
	}

	private static void updateGame(int currentPlayer2, int row, int col) {
		if(hasWon(currentPlayer2,row,col)) {
			currentState = (currentPlayer2 == CROSS) ? CROSS_WON : NOUGHT_WON;
		}
		else if (isDraw()) {
			currentState = DRAW;
		}
	}

	private static boolean isDraw() {
		  for (int row = 0; row < ROWS; ++row) {
		         for (int col = 0; col < COLS; ++col) {
		            if (board[row][col] == EMPTY) {
		               return false;  
		            }
		         }
		      }
		      return true;
	}

	private static boolean hasWon(int currentPlayer2, int row, int col) {
		 return (board[currentRow][0] == currentPlayer2         // 3-in-the-row
                 && board[currentRow][1] == currentPlayer2
                 && board[currentRow][2] == currentPlayer2
            || board[0][currentCol] == currentPlayer2      // 3-in-the-column
                 && board[1][currentCol] == currentPlayer2
                 && board[2][currentCol] == currentPlayer2
            || currentRow == currentCol            // 3-in-the-diagonal
                 && board[0][0] == currentPlayer2
                 && board[1][1] == currentPlayer2
                 && board[2][2] == currentPlayer2
            || currentRow + currentCol == 2  // 3-in-the-opposite-diagonal
                 && board[0][2] == currentPlayer2
                 && board[1][1] == currentPlayer2
                 && board[2][0] == currentPlayer2);
	}

	private static void playerMove(int currentPlayer2) {
		boolean validInput = false;
		do {
			if(currentPlayer2 == CROSS) {
				System.out.println("X! Please enter your move row[1-3] and col[1-3]");
			}else {
				System.out.println("0! Please enter your move row[1-3] and col[1-3]");
			}
			int row = in.nextInt()-1;
			int col = in.nextInt()-1;
			if(row >= 0 && col >= 0 && row <= ROWS && col <= COLS && board[row][col]==EMPTY) {
				board[row][col]=currentPlayer2;
				validInput = true;
				currentRow = row;
				currentCol = col;
			}
			else {
				System.out.println("Not a valid move");
			}
			currentPlayer = (currentPlayer2==CROSS) ?  NOUGHT : CROSS;
		}while(!validInput);
	}
	

}
