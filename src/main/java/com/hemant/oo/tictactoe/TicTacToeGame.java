package com.hemant.oo.tictactoe;

import java.util.Scanner;

public class TicTacToeGame {

	private Board board;

	private GameState currentState;

	private ContentType currentPlayer;

	private static Scanner sc = new Scanner(System.in);

	public TicTacToeGame() {
		board = new Board();
		initGame();

		do {
			playerMove(currentPlayer);
			board.paint();
			updateGame(currentPlayer);

			if (currentState == GameState.DRAW) {
				System.out.println("Its a draw!!");
			} else if (currentState == GameState.CROSS_WON) {
				System.out.println("CROSS WON !!");
			} else if (currentState == GameState.NOUGHT_WON) {
				System.out.println("NOUGHT WON !!");
			}
			currentPlayer = (currentPlayer == ContentType.CROSS) ? ContentType.NOUGHT : ContentType.CROSS;
		} while (currentState == GameState.PLAYING);

	}

	private void playerMove(ContentType player) {
		boolean validInput = false;
		do {
			if (player == ContentType.CROSS) {
				System.out.print("Player 'X', enter your move (row[1-3] column[1-3]): ");
			} else {
				System.out.print("Player 'O', enter your move (row[1-3] column[1-3]): ");
			}

			int row = sc.nextInt() - 1;
			int col = sc.nextInt() - 1;
			if (row >= 0 && row < board.ROWS && col >= 0 && col < board.COLS
					&& board.cells[row][col].content == ContentType.EMPTY) {
				board.cells[row][col].content = player;
				board.currentRow = row;
				board.currentCol = col;
				validInput = true; // input okay, exit loop
			} else {
				System.out.println("This move at (" + (row + 1) + "," + (col + 1) + ") is not valid. Try again...");
			}

		} while (!validInput);
	}

	private void updateGame(ContentType player) {
		if (board.hasWon(player)) {
			currentState = (player == ContentType.CROSS) ? GameState.CROSS_WON : GameState.NOUGHT_WON;
		} else if (board.isDraw()) {
			currentState = GameState.DRAW;
		}
	}

	private void initGame() {
		board.init();
		currentPlayer = ContentType.CROSS;
		currentState = GameState.PLAYING;
	}

	public static void main(String[] args) {
		new TicTacToeGame();
	}

}
