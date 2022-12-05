package com.example.androidttt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    Button[][] grid = new Button[3][3];
    int[][] board = new int[3][3];
    final int BLANK = 0;
    final int X_MOVE = 1;
    final int O_MOVE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid[0][0] = (Button) this.findViewById(R.id.button1);
        grid[0][1] = (Button) this.findViewById(R.id.button2);
        grid[0][2] = (Button) this.findViewById(R.id.button3);
        grid[1][0] = (Button) this.findViewById(R.id.button4);
        grid[1][1] = (Button) this.findViewById(R.id.button5);
        grid[1][2] = (Button) this.findViewById(R.id.button6);
        grid[2][0] = (Button) this.findViewById(R.id.button7);
        grid[2][1] = (Button) this.findViewById(R.id.button8);
        grid[2][2] = (Button) this.findViewById(R.id.button9);


        // continues to run the program as long as no player has won or tied yet
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                grid[x][y].setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (b.equals(grid[x][y])) {
                    if (board[x][y] == BLANK) {
                        b.setText("X");
                        b.setEnabled(false);
                        board[x][y] = X_MOVE;

                        //check for win
                        if (checkWin(X_MOVE)) {
                            Toast.makeText(this, "X won", Toast.LENGTH_LONG).show();
                            clearBoard();
                            return;
                        }

                        if (checkWin(O_MOVE)) {
                            Toast.makeText(this, "O won", Toast.LENGTH_LONG).show();
                            clearBoard();
                            return;
                        }

                        if (checkTie()) {
                            Toast.makeText(this, "It's a tie", Toast.LENGTH_LONG).show();
                            clearBoard();
                            return;
                        }

                        aiMove();

                        if (checkWin(X_MOVE)) {
                            Toast.makeText(this, "X won", Toast.LENGTH_LONG).show();
                            clearBoard();
                            return;
                        }

                        if (checkWin(O_MOVE)) {
                            Toast.makeText(this, "O won", Toast.LENGTH_LONG).show();
                            clearBoard();
                            return;
                        }

                        if (checkTie()) {
                            Toast.makeText(this, "It's a tie", Toast.LENGTH_LONG).show();
                            clearBoard();
                            return;
                        }

                    }
                }
            }
        }
    }

    //AI's turn
    public void aiMove() {
        //try to win
        if (checkSingleBlank(O_MOVE)) {
            return;
        }

        //try to block
        if (checkSingleBlank(X_MOVE)) {
            return;
        }

        //play randomly
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (board[x][y] == BLANK) {
                    list.add(x * 10 + y);
                }
            }
        }
        int choice = (int) (Math.random() * list.size());
        board[list.get(choice) / 10][list.get(choice) % 10] = O_MOVE;
        grid[list.get(choice) / 10][list.get(choice) % 10].setText("O");
        grid[list.get(choice) / 10][list.get(choice) % 10].setEnabled(false);
        //context of move played by AI
        Toast.makeText(this, "Played Randomly", Toast.LENGTH_SHORT).show();
    }

    //AI checks for blank spots on the board to place a letter on
    public boolean checkSingleBlank(int player) {
        int oCount = 0;
        int blankCount = 0;
        int blankX = 0;
        int blankY = 0;

        //check columns win
        for (int x = 0; x < 3; x++) {
            oCount = 0;
            blankCount = 0;
            blankX = 0;
            blankY = 0;
            for (int y = 0; y < 3; y++) {
                if (board[x][y] == BLANK) {
                    blankCount++;
                    blankX = x;
                    blankY = y;
                }
                if (board[x][y] == player) {
                    oCount++;
                }
            }
            if (oCount == 2 && blankCount == 1) {
                board[blankX][blankY] = O_MOVE;
                grid[blankX][blankY].setText("O");
                grid[blankX][blankY].setEnabled(false);
                return true;
            }
        }

        //check row win
        for (int y = 0; y < 3; y++) {
            oCount = 0;
            blankCount = 0;
            blankX = 0;
            blankY = 0;
            for (int x = 0; x < 3; x++) {
                if (board[x][y] == BLANK) {
                    blankCount++;
                    blankX = x;
                    blankY = y;
                }
                if (board[x][y] == player) {
                    oCount++;
                }
            }
            if (oCount == 2 && blankCount == 1) {
                board[blankX][blankY] = O_MOVE;
                grid[blankX][blankY].setText("O");
                grid[blankX][blankY].setEnabled(false);
                return true;
            }
        }

        //check top left to bottom right diagonal
        oCount = 0;
        blankCount = 0;
        blankX = 0;
        blankY = 0;

        if (board[0][0] == BLANK) {
            blankCount++;
            blankX = 0;
            blankY = 0;
        }
        if (board[0][0] == player) {
            oCount++;
        }
        if (board[1][1] == BLANK) {
            blankCount++;
            blankX = 1;
            blankY = 1;
        }
        if (board[1][1] == player) {
            oCount++;
        }
        if (board[2][2] == BLANK) {
            blankCount++;
            blankX = 2;
            blankY = 2;
        }
        if (board[2][2] == player) {
            oCount++;
        }
        if (oCount == 2 && blankCount == 1) {
            board[blankX][blankY] = O_MOVE;
            grid[blankX][blankY].setText("O");
            grid[blankX][blankY].setEnabled(false);
            return true;
        }

        //top right to bottom left diagonal
        oCount = 0;
        blankCount = 0;
        blankX = 0;
        blankY = 0;

        if (board[2][0] == BLANK) {
            blankCount++;
            blankX = 2;
            blankY = 0;
        }
        if (board[2][0] == player) {
            oCount++;
        }
        if (board[1][1] == BLANK) {
            blankCount++;
            blankX = 1;
            blankY = 1;
        }
        if (board[1][1] == player) {
            oCount++;
        }
        if (board[0][2] == BLANK) {
            blankCount++;
            blankX = 0;
            blankY = 2;
        }
        if (board[0][2] == player) {
            oCount++;
        }
        if (oCount == 2 && blankCount == 1) {
            board[blankX][blankY] = O_MOVE;
            grid[blankX][blankY].setText("O");
            grid[blankX][blankY].setEnabled(false);
            return true;
        }
        return false;

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
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                board[x][y] = BLANK;
                grid[x][y].setText("");
                grid[x][y].setEnabled(true);
            }
        }
    }

}
