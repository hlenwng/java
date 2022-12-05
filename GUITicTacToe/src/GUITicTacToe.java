import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/* This program generates a tic-tac-toe board using GUI, and allows for two players to take turns playing in the window.
 * After a player wins, the program resets the board and the players get to play again.
 * Players also have the option to change their names in the text fields, where their win score is also kept. 
 * 
 * Helen Wang
 * October 7, 2021
 */

public class GUITicTacToe implements ActionListener {

	//declares all the starting values
	JFrame frame = new JFrame();
	JButton[][] button = new JButton[3][3];
	int[][] board = new int[3][3];
	final int BLANK = 0;
	final int X_MOVE = 1;
	final int O_MOVE = 2;
	final int X_TURN = 0;                                               
	final int O_TURN = 1;
	int turn = X_TURN;

	Container center = new Container();
	JLabel xLabel = new JLabel("X wins: 0");
	JLabel oLabel = new JLabel("O wins: 0");
	JButton xChangeName = new JButton("Change X's Name.");
	JButton oChangeName = new JButton("Change O's Name.");
	JTextField xChangeField = new JTextField();
	JTextField oChangeField = new JTextField();

	Container north = new Container();
	String xPlayerName = "X";
	String oPlayerName = "O";
	int xwins = 0;
	int owins = 0;
	
	//sets up the board
	public GUITicTacToe() {
		frame.setSize(400, 400);	
		//Center grid container
		frame.setLayout(new BorderLayout());
		center.setLayout(new GridLayout(3,3));
		for (int i = 0; i < button.length; i++) {
			for (int j = 0; j < button[0].length; j++) {
				button[j][i] = new JButton(j+","+i);
				center.add(button[j][i]);
				button[j][i].addActionListener(this);
			}
		}
		frame.add(center, BorderLayout.CENTER);
		//North container
		north.setLayout(new GridLayout(1,2));
		north.add(xLabel);
		north.add(oLabel);
		north.add(xChangeName);
		xChangeName.addActionListener(this);
		north.add(oChangeName);
		oChangeName.addActionListener(this);
		north.add(xChangeField);
		north.add(oChangeField);
		frame.add(north, BorderLayout.NORTH);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		new GUITicTacToe();

	}
	
	//keeps track of who's turn it is, and checks for wins and ties
	@Override
	public void actionPerformed(ActionEvent event) {
		JButton current;
		boolean gridButton = false;
		for (int i = 0; i < button.length; i++) {
			for (int j = 0; j < button[0].length; j++) {
				if (event.getSource().equals(button[j][i])) {
					gridButton = true;
					current = button[j][i];
					if (board[j][i] == BLANK) {
						if (turn == X_TURN) {
							current.setText("X");
							//current.setEnabled(false);
							board[j][i] = X_MOVE;
							turn = O_TURN;
						}
						else {
							current.setText("O");
							board[j][i] = O_MOVE;
							turn = X_TURN;
						}
						//check for wins and ties
						if (checkWin(X_MOVE) == true) {
							//X wins!
							xwins++;
							xLabel.setText(xPlayerName + " wins:" + xwins);
							current.setEnabled(true);
							clearBoard();
						}
						else if (checkWin(O_MOVE) == true) {
							//O wins!
							owins++;
							oLabel.setText(oPlayerName + " wins:" + owins);
							current.setEnabled(true);
							clearBoard();
						}
						else if (checkTie() == true) {
							//Tie!
							current.setEnabled(true);
							clearBoard();
						}
 					}
				}
			}
		}
		
		//displays in window the number of wins per player, and allows user to change the player names
		if (gridButton == false) {
			if(!xChangeField.getText().equals("")) {
				if (event.getSource().equals(xChangeName) == true) {
					xPlayerName = xChangeField.getText();
					xLabel.setText(xPlayerName + " wins:" + xwins);
				}
			}	
		
			if(!oChangeField.getText().equals("")) {
				if (event.getSource().equals(oChangeName) == true) {
					//oChangeField.setText("O Change");
					oPlayerName = oChangeField.getText();
					oLabel.setText(oPlayerName + " wins:" + owins);
				}
			}
		}
	}
	
	//checks all the possible winning combinations for a win
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
	
	//checks for ties
	public boolean checkTie() {
		for (int a = 0; a < board.length; a++) {
			for (int b = 0; b < board[0].length; b++) {
				if (board[a][b] == BLANK) {
					
					return false;
				}
			}
		}
		return true;
	}
	
	//clears the board 
	public void clearBoard() {
		for (int a = 0; a < board.length; a++) {
			for (int b = 0; b < board.length; b++) {
				board[a][b] = BLANK;
				button[a][b].setText("");
			}
			
		}
		turn = X_TURN;
	}
}
