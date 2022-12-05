import java.util.Iterator;
import java.util.Scanner;

/* This program generates a tic-tac-toe board, and allows for two players to take turns playing in the console.
 * After a player wins, the program asks the players if they want to play again... if so, the board resets
 * and the players get to play again, if not, the program is terminated.
 * 
 * Helen Wang
 * September 29, 2021
 */

public class TicTacToe {
	// declares all the starting values
	int [][] board = new int[3][3];
	final int BLANK = 0;
	final int X_MOVE = 1;
	final int O_MOVE = 2;
	final int X_TURN = 0;                                               
	final int O_TURN = 1;
	
	int turn = X_TURN;
	
	Scanner scanner;
	String input = "";
	
	int numXWins = 0;
	int numOWins = 0;
	int numTies = 0;
	
	public TicTacToe() {
		scanner = new Scanner(System.in);
		boolean stillPlaying = true;
		
		// runs the program as long as the "still playing" is true / the player is still playing
		while (stillPlaying == true) {
			
			// continues to run the program as long as no player has won or tied yet 
			while (checkWin(X_MOVE) == false && checkWin(O_MOVE) == false && checkTie() == false) {
				printBoard();
				input = scanner.nextLine();
				
				/* asks for the player to enter in a valid and not-taken letter & number to place corresponding piece on the board
				 * if player enters non-valid unit, the computer tells them what to change, or why it it's not valid
				 */
				if (input.length() != 2) {
					System.out.println("Enter a letter followed by a number");
				}
				else if (input.charAt(0) != 'a' && 
						 input.charAt(0) != 'b' &&
					 	 input.charAt(0) != 'c') {
					System.out.println("Row must be an a, b, or c.");
				}
				else if (input.charAt(1) != '1' && 
						 input.charAt(1) != '2' &&
					 	 input.charAt(1) != '3') {
					System.out.println("Column must be an 1, 2, or 3.");
				}
				else {
					int row = input.charAt(0) - 'a';
					int column = input.charAt(1) - '1';
					if (board[row][column] == BLANK) {
						if (turn == X_TURN) {
							board[row][column] = X_MOVE;
							turn = O_TURN;
						}
						else {
							board[row][column] = O_MOVE;
							turn = X_TURN;
						}
					}
					else {
						System.out.println("There is a piece there!");
					}
				}
			}
			
			// if either player wins, the program announces who won, and adds a win to the scoreboard
			if (checkWin(X_MOVE) == true) {
				System.out.println("X wins!");
				numXWins ++;
			}
			else if (checkWin(O_MOVE) == true) {
				System.out.println("O wins!");
				numOWins ++;
			}
			
			System.out.println();
			System.out.println("Scoreboard:");
			System.out.println("X wins = " + numXWins + " , O wins = " + numOWins + " , Ties = " + numTies);
			System.out.println();
			
			/* the program asks players if they want to play again or not
	   		 * if yes, the board resets and the program runs again
			 * if not, the program is then terminated
			 */
			System.out.println("Play again? (Yes or No?)");
			String yesno = scanner.nextLine();
			if (yesno.equals("yes") || yesno.equals("Yes")){
				resetGame();
				stillPlaying =  true;
				
			} else {
				stillPlaying = false;
			}
		}
	}

	public static void main(String[] args) {
		new TicTacToe();
		
	}
	
	// program prints out the board, and based on the value entered by the player, the program places the corresponding unit onto the board
	public void printBoard() {
		System.out.println(" \t1\t2\t3");
		for (int row = 0; row < board.length; row++) {
			String output = (char) ('a'+row) + "\t";
			for (int column = 0; column < board[0].length; column++) {
				if (board[row][column] == BLANK) {
					output += " \t";
				}
				else if (board[row][column] == X_MOVE) {
					output += "X\t";
				}
				else if (board[row][column] == O_MOVE) {
					output += "O\t";
				}
			}
			System.out.println(output);
		}	
	}
	
	// resets and clears the board
	public void resetGame() {
		board = new int [3][3];
		turn = X_TURN;
	}
	
	// checks for all the possible winning combinations after each turn to check for a win
	public boolean checkWin(int player) {
		//row 1
		if (board[0][0] == player && board[0][1] == player && board [0][2] == player) {
			return true;
		}
		//row 2
		if (board[1][0] == player && board[1][1] == player && board [1][2] == player) {
			return true;
		}
		//row 3
		if (board[2][0] == player && board[2][1] == player && board [2][2] == player) {
			return true;
		}
		
		
		//column 1
		if (board[0][0] == player && board[1][0] == player && board [2][0] == player) {
			return true;
		}
		//column 2
		if (board[0][1] == player && board[1][1] == player && board [2][1] == player) {
			return true;
		}
		//column 3
		if (board[0][2] == player && board[1][2] == player && board [2][2] == player) {
			return true;
		}
		
		// diagonal top left - bottom right
		if (board[0][0] == player && board[1][1] == player && board [2][2] == player) {
			return true;
		}
		// diagonal bottom left - top right
		if (board[2][0] == player && board[1][1] == player && board [0][2] == player) {
			return true;
		}
		 
		return false;
	}
	
	// checks to see if any player has tied
	public boolean checkTie() {
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[0].length; column++) {
				if (board[row][column] == BLANK) {
					
					return false;
				}
			}
		}
		System.out.println("It's a tie!");
		numTies ++;
		return true;
	}
}
