/*
 * Author(s): Angie Wang & Helen Wang
 * 6/15/22
 * 
 * This program creates a 10 by 4 button grid where the user can choose colors (on the south container) to
 *  add them to the top row of the grid. The user has 10 “tries” to guess the correct randomly generated color sequence. 
 *  The text box on the east container will display hints for the user.
 * 
 * */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Mastermind implements ActionListener {
	
	JButton [][] button = new JButton[4][10];
	JTextField [][] easttext = new JTextField [10][1];
	int[][] board = new int[4][10];
	int [] Sequence = new int [4];
	int [] sequenceHard = new int[4];
	
	int turn = 1;
	
	// Variables
	JFrame frame = new JFrame("Mastermind");
	Container center = new Container();
	final int BLANK = 0;
	final int RED = 1;
	final int ORANGE = 2;
	final int YELLOW = 3;
	final int GREEN = 4;
	final int BLUE = 5;
	final int PURPLE = 6; 
	final int PINK = 7;
	final int PRESS = 0;
	final int NOPRESS = 1;
	final int NORMAL = 0;
	final int HARD = 1;
	int move = NOPRESS;
	int mode = NORMAL;
	int rowNum = 0;
	JButton current;
	// West container
	
	Container west = new Container();
	JButton start = new JButton("Start");
	JButton reset = new JButton("Reset");
	JButton normal = new JButton("Normal");
	JButton hard = new JButton("Hard");
	JButton check = new JButton("Check");
	JButton delete = new JButton("Delete");
	JTextField score = new JTextField("...");
	
	// East container
	Container east = new Container();
	JButton red = new JButton("Red");
	JButton orange = new JButton("Orange");
	JButton yellow = new JButton("Yellow");
	JButton green = new JButton("Green");
	JButton blue = new JButton("Blue");
	JButton purple = new JButton("Purple");
	JButton pink = new JButton("Pink");
	
	// South container
	Container south = new Container();
	JButton one = new JButton();
	JButton two = new JButton();
	JButton three = new JButton();
	JButton four = new JButton();
	
	int[] secret;
    int[] secretHard;
	
	// Constructor for GraphCreator
	public Mastermind() {
		
		//generate code
		secret = secretCode(NORMAL);
		secretHard = secretCode(HARD);
		
		// Create the frame
		frame.setSize(900, 900);
		frame.setLayout(new BorderLayout());
		frame.add(center, BorderLayout.CENTER);
		
		// West side: contains buttons for creating nodes and edges
		west.setLayout(new GridLayout(7,1));
		
		west.add(start);
		start.addActionListener(this);
		start.setEnabled(false);
		
		west.add(reset);
		reset.addActionListener(this);
		reset.setEnabled(false);
		
		west.add(normal);
		normal.addActionListener(this);
		normal.setEnabled(true);

		
		west.add(hard);
		hard.addActionListener(this);
		hard.setEnabled(true);

		west.add(check);
		check.addActionListener(this);
		check.setEnabled(false);

		west.add(delete);
		delete.addActionListener(this);
		delete.setEnabled(false);

		
		west.add(score);
		frame.add(west, BorderLayout.WEST);
		
		
		// East side: textfields
		east.setLayout(new GridLayout(10,1));
		frame.add(east, BorderLayout.EAST);
		
		for (int j = 0; j < 10; j++) {
            easttext[j][0] = new JTextField();
            east.add(easttext[j][0]);
            easttext[j][0].addActionListener(this);
        }
		
		
		
		
		// South side: colors
		south.setLayout(new GridLayout(1,7));
		frame.add(south, BorderLayout.SOUTH);

		//Colors
		south.add(red);
		red.addActionListener(this);
		red.setBackground(Color.red);
		red.setOpaque(true);
		red.setBorderPainted(false);
		red.setForeground(Color.WHITE);
		red.setEnabled(false);


		south.add(orange);
		orange.addActionListener(this);
		orange.setBackground(Color.orange);
		orange.setOpaque(true);
		orange.setBorderPainted(false);
		orange.setEnabled(false);


		south.add(yellow);
		yellow.addActionListener(this);
		yellow.setBackground(Color.yellow);
		yellow.setOpaque(true);
		yellow.setBorderPainted(false);
		yellow.setEnabled(false);


		south.add(green);
		green.addActionListener(this);
		green.setBackground(Color.green);
		green.setOpaque(true);
		green.setBorderPainted(false);
		green.setEnabled(false);


		south.add(blue);
		blue.addActionListener(this);
		blue.setBackground(Color.blue);
		blue.setOpaque(true);
		blue.setBorderPainted(false);
		blue.setForeground(Color.WHITE);
		blue.setEnabled(false);


		south.add(purple);
		purple.addActionListener(this);
		purple.setBackground(Color.magenta);
		purple.setOpaque(true);
		purple.setBorderPainted(false);
		purple.setEnabled(false);


		south.add(pink);
		pink.addActionListener(this);
		pink.setBackground(Color.pink);
		pink.setOpaque(true);
		pink.setBorderPainted(false);
		pink.setEnabled(false);



		// Center
		center.setLayout(new GridLayout(10,4));
		frame.add(center, BorderLayout.CENTER);
		//add buttons
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                button[j][i] = new JButton();
                center.add(button[j][i]);
                button[j][i].addActionListener(this);
                board[j][i] = BLANK;
                button[j][i].setBackground(Color.WHITE);
            }
        }
        
        System.out.println("Normal sequence:");
        System.out.println(Arrays.toString(secret));
        System.out.println("Hard sequence:");
        System.out.println(Arrays.toString(secretHard));
					
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.repaint();
	}

	public static void main(String[] args) {
		new Mastermind();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if (e.getSource().equals(normal)) {
			
			start.setEnabled(true);
			hard.setEnabled(false);
			pink.setEnabled(false);
			
			//Mode is normal
			mode = NORMAL;
			
		}
		
		//enable pink
		if (e.getSource().equals(hard)) {
			start.setEnabled(true);
			normal.setEnabled(false);
			pink.setEnabled(true);
			mode = HARD;
			
			}
			
		if (e.getSource().equals(start)) {
			
			start.setEnabled(false);
			
			normal.setEnabled(false);
			hard.setEnabled(false);
			
			reset.setEnabled(true);
			check.setEnabled(true);
			delete.setEnabled(true);
			
			red.setEnabled(true);
			orange.setEnabled(true);
			yellow.setEnabled(true);
			green.setEnabled(true);
			blue.setEnabled(true);
			purple.setEnabled(true);
		}
	
		
		//red is clicked
		if(e.getSource().equals(red)) {
			move = PRESS; 
			for (int i = 0; i < 4;  i++) { //change the color of most far left blank tile 
				current = button[i][rowNum];
				if (board[i][rowNum] == BLANK) {
					if(move == PRESS) {
						current.setBackground(Color.RED);
						current.setOpaque(true);
						current.setBorderPainted(false);
						board[i][rowNum] = RED;
						move = NOPRESS; //makes sure to stop loop after being pressed once
						
					}
				}
			}
		}
		
		//orange is clicked
		if(e.getSource().equals(orange)) {
			move = PRESS;
			for (int i = 0; i < 4;  i++) { 
				current = button[i][rowNum];
				if (board[i][rowNum] == BLANK) {
					if(move == PRESS) {
						current.setBackground(Color.ORANGE);
						current.setOpaque(true);
						current.setBorderPainted(false);
						board[i][rowNum] = ORANGE;
						move = NOPRESS;
					}
				}
			}
		}

		//yellow is pressed
		if(e.getSource().equals(yellow)) {
			move = PRESS;
			for (int i = 0; i < 4;  i++) {
				current = button[i][rowNum];
				if (board[i][rowNum] == BLANK) {
					if(move == PRESS) {
						current.setBackground(Color.YELLOW);
						current.setOpaque(true);
						current.setBorderPainted(false);
						board[i][rowNum] = YELLOW;
						move = NOPRESS;
					}
				}
			}
		}
		
		//green is pressed
		if(e.getSource().equals(green)) {
			move = PRESS;
			for (int i = 0; i < 4;  i++) {
				current = button[i][rowNum];
				if (board[i][rowNum] == BLANK) {
					if(move == PRESS) {
						current.setBackground(Color.GREEN);
						current.setOpaque(true);
						current.setBorderPainted(false);				
						board[i][rowNum] = GREEN;
						move = NOPRESS;
					}
				}
			}
		}
				
		//blue is pressed
		if(e.getSource().equals(blue)) {
			move = PRESS;
			for (int i = 0; i < 4;  i++) { 
				current = button[i][rowNum];
				if (board[i][rowNum] == BLANK) {
					if(move == PRESS) {
						current.setBackground(Color.BLUE);
						current.setOpaque(true);
						current.setBorderPainted(false);
						board[i][rowNum] = BLUE;
						move = NOPRESS;
					}
				}
			}
		}
			
		//purple is pressed
		if(e.getSource().equals(purple)) {
			move = PRESS;
			for (int i = 0; i < 4;  i++) { 
				current = button[i][rowNum];
				if (board[i][rowNum] == BLANK) {
					if(move == PRESS) {
						current.setBackground(Color.MAGENTA);
						current.setOpaque(true);
						current.setBorderPainted(false);
						board[i][rowNum] = PURPLE;
						move = NOPRESS;
					}
				}
			}
		}
		
		//pink is pressed
		if(e.getSource().equals(pink)) {
			move = PRESS;
			for (int i = 0; i < 4;  i++) { 
				current = button[i][rowNum];
				if (board[i][rowNum] == BLANK) {
					if(move == PRESS) {
						current.setBackground(Color.PINK);
						current.setOpaque(true);
						current.setBorderPainted(false);
						board[i][rowNum] = PINK;
						move = NOPRESS;
					}
				}
			}
		}

		if(e.getSource().equals(check)) {
			int black = 0;
			int white = 0;
			
			//Normal mode
			if(mode == NORMAL) {
			
				for (int i = 0; i < 4; i++) {
					
					//Check black pegs:  right color, right place 
					if(board[i][rowNum]== secret[i]){
						black = black+1;
					}
					
					// Check white pegs: right color, wrong place
					if(board[i][rowNum] != secret[i]){ //if the color does not match, check the sequence and count one white peg for each color that matches in the other positions
						 if(board[i][rowNum] == secret[0]) {
							white = white +1;
						}
						 else if(board[i][rowNum] == secret[1]) {
							white = white +1;
						}
						 else if(board[i][rowNum] == secret[2]) {
							white = white +1;
						}
						 else if(board[i][rowNum] == secret[3]) {
							white = white +1;
						}
					}
						
					
					
					easttext[rowNum][0].setText(String.valueOf("black: " + black + " white: " + white));
					mode = NORMAL;
					}
			}
			//Hard Mode
			else if(mode == HARD) {
				
				for (int i = 0; i < 4; i++) {
					
					//Check black pegs:  right color, right place 
					if(board[i][rowNum]== secretHard[i]){
						black = black+1;
					}
					
					// Check white pegs: right color, wrong place
					if(board[i][rowNum] != secretHard[i]){
						 if(board[i][rowNum] == secretHard[0]) {
							white = white +1;
						}
						 else if(board[i][rowNum] == secretHard[1]) {
							white = white +1;
						}
						 else if(board[i][rowNum] == secretHard[2]) {
							white = white +1;
						}
						 else if(board[i][rowNum] == secretHard[3]) {
							white = white +1;
						}
					}
						
					
					
					easttext[rowNum][0].setText(String.valueOf("black: " + black + " white: " + white));
					}
				mode = HARD;
				
				
				
			}
			
			// check win
			
			for (int i = 0; i < 4; i++) {
				if (black == 4) {
					score.setText("You won!");
				}
			}
		
			//go to the next row
			rowNum = rowNum +1;
			System.out.println(rowNum);
			        
	      
	        frame.revalidate();
	        
	        
			        
		}
		
		//reset the board
		if(e.getSource().equals(reset)) {
			resetBoard();
		}
		
		//deletes current row
		if(e.getSource().equals(delete)) {
			for(int a = 0; a < 4; a++) {
				
					board[a][rowNum] = BLANK;
					button[a][rowNum].setBackground(Color.WHITE);
					
				
			}

		}
	}
	
	public void resetBoard() {
		// reset every color tile
		for(int a = 0; a < 4; a++) {
			for(int b = 0; b < 10; b++) {
				board[a][b] = BLANK;
				button[a][b].setBackground(Color.WHITE);
				
			}
		}
		// reset 
		for (int i = 0; i < 10; i++) {
			easttext[i][0].setText("");
		}
		rowNum = 0;
		//set buttons back to disabled
		normal.setEnabled(true);
		hard.setEnabled(true);
		check.setEnabled(false);
		reset.setEnabled(false);
		delete.setEnabled(false);
		red.setEnabled(false);
		orange.setEnabled(false);
		yellow.setEnabled(false);
		green.setEnabled(false);
		blue.setEnabled(false);
		purple.setEnabled(false);
	}
	public int[] secretCode(int diffuculty) {
		if(diffuculty == 0) {
			// generate random sequence 
						int min = 1;  
						int max = 6;  
						
						Random rand = new Random();

						// nextInt as provided by Random is exclusive of the top value so you need to add 1 

						int randomNum = rand.nextInt((max - min) + 1) + min;
						
						return new int[] {rand.nextInt((max - min) + 1) + min,rand.nextInt((max - min) + 1) + min,rand.nextInt((max - min) + 1) + min,rand.nextInt((max - min) + 1) + min};
						
		}
		if(diffuculty == 1) {
			// generate random sequence 
						int min = 1;  
						int max = 7;  
						
						Random rand = new Random();

						// nextInt as provided by Random is exclusive of the top value so you need to add 1 

						int randomNum = rand.nextInt((max - min) + 1) + min;
						
						int [] Sequence = {rand.nextInt((max - min) + 1) + min,rand.nextInt((max - min) + 1) + min,rand.nextInt((max - min) + 1) + min,rand.nextInt((max - min) + 1) + min};
						
					
						
						return new int[] {rand.nextInt((max - min) + 1) + min,rand.nextInt((max - min) + 1) + min,rand.nextInt((max - min) + 1) + min,rand.nextInt((max - min) + 1) + min};
		}
		return Sequence;
	}
}