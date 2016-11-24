package TicTacToe;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.EventListener;
import java.util.Random;
import javax.swing.JApplet;
import javax.swing.JOptionPane;

/* +---------------------------------------------------------------------------------------------------------+
 * | Julien Dhouti (julien@email.virginia.edu)
 * | Period 2
 * | 05/11/2016
 * | This program is a simple game of Tic Tac Toe. It does not contain an AI and requires 2 players to play.
 * | It keeps tracks of each player's wins and displays them momentarily before the program is exited.
 * | It also rotates the player's turns. If a player wins one game, the losing player will get to play first
 * | in the next one.
 * +---------------------------------------------------------------------------------------------------------+
 * | 534 lines of code.
 * | Please critique my code and message or email me about improvements I could make in the following:
 * |		- Style/Syntax
 * |		- Optimization
 * |		- Algorithms
 * |		- Extra features
 * | Thank you <3
 * +---------------------------------------------------------------------------------------------------------+
 */

public class TicTacToe extends JApplet implements MouseListener
{
	public TicTacToe()
	{
		player1 = new Player("Player 1");			// Creates a player object for player 1.
		player2 = new Player("Player 2");			// Creates a player object for player 2.
		mouseClicked = false;
		xMouse = 0;
		yMouse = 0;
		grid = new GridFunctions();					// Creates a new GridFunctions object so the grid can be operated.
		counter = 0;								// Integer variable to help determine who's turn it is.
		roundCounter = 0;							// Counts how many rounds have been played.
		tieCounter = 0;

		addMouseListener(this);						// Adds the mouse listener capability to the class.
	}

	DrawPieces piece = new DrawPieces();

	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;

		this.setSize(1000, 700);		// sets the size of the playing window.

		grid.drawGrid(g2);							// Draws the grid so the game can begin.

		if(mouseClicked)							// This if statement is only executed if the mouse clicked is clicked anywhere on the Applet.
		{
			grid.updateCoords(xMouse, yMouse);		// When the player clicks with the pointer, the coordinates of the pointer are sent to the GridFunctions class.

			if(grid.returnX() > 0 && grid.returnY() > 0)	// This statement checks whether the mouse pointer was clicked within the 3x3 grid.
			{
				if((counter % 2) == 0)				// If the mouse was clicked within the grid, this statement checks who's turn it is. If counter = even #, player 1's turn.
				{
					if(!(grid.checkBox() > 0))		// Uses the .checkBox() method to check if the square the player clicked on has already been taken.
					{
						grid.updateGrid(2);			// If square has not been taken, the grid is updated by associating the clicked on box with 2 or 1. 2 means it has an X, 1 means it has an O.
						piece.drawX(g2, grid.returnX(), grid.returnY());	// Draws the x using the drawX method from the X class. Player 1 is assigned to X, player 2 is assigned to O.
						counter++;					// Updates the counter so that it is the next player's turn. Even counter means it's player 1's turn, odd counter means it's player 2's.
					}
				}
				else if((counter % 2) != 0)			// If counter is odd, it is player 2's turn.
				{
					if(!(grid.checkBox() > 0))
					{
						grid.updateGrid(1);
						piece.drawO(g2, grid.returnX(), grid.returnY());
						counter++;
					}
				}

				if(grid.checkWinner() == 1)			// Uses the .checkWinner() method of the GridFunctions class to determine whether in this case, player 1 was the winner.
				{
					player1.addWin();				// Increments player 1's win total.
					counter = 1;
					//piece.drawWhosTurn(g2, 1);	// Method is not finished.

					if(player1.returnWin() > 1)
					{
						JOptionPane.showMessageDialog(null, "Player 1 has won!\nPlayer 1 now has " + player1.returnWin() + " wins.");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Player 1 has won!\nPlayer 1 now has 1 win!");
					}
					roundCounter++;					// Increments the total amount of rounds elapsed.
				}
				else if(grid.checkWinner() == 2)	// Checks if the winner is player 2.
				{
					player2.addWin();
					counter = 2;
					//piece.drawWhosTurn(g2, 2);	// Method not finished.

					if(player2.returnWin() > 1)
					{
						JOptionPane.showMessageDialog(null, "Player 2 has won!\nPlayer 2 now has " + player2.returnWin() + " wins.");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Player 2 has won!\nPlayer 2 now has 1 win!");
					}
					roundCounter++;
				}
				else if(grid.checkWinner() == 3)	// If the .checkWinner() method returns 3, there is no winner. It's a tie.
				{
					JOptionPane.showMessageDialog(null, "It's a tie!");
					roundCounter++;					// The round counter however, still increments.
					tieCounter++;
				}

				if(grid.checkWinner() == 1 || grid.checkWinner() == 2 || grid.checkWinner() == 3)	// Checks if the game is over to prompt for the end of game options.
				{
					while(true)
					{
						int reply = JOptionPane.showConfirmDialog(null, "Would you like to restart?", "Restart?", JOptionPane.YES_NO_OPTION);

						if(reply == JOptionPane.YES_OPTION)		// Executes if the player selects YES.
						{
							grid.resetBoxes();					// Uses .resetBoxes() of the GridFunction class to clear ids stored for each boxes. These ids are there to determine whether a piece is present within
																// that box.
							grid.clearGrid(g2);					// Uses .clearGrid() of the GirdFunction class to clear all pieces on the grid.
							break;
						}
						else if(reply == JOptionPane.NO_OPTION)	// Executes if the player selected NO.
						{
							JOptionPane.showMessageDialog(null, "Before you go, here are your stats..."
									+ "\n\nTotal rounds played: " + roundCounter
									+ "\nPlayer 1 score: " + player1.returnWin()
									+ "\nPlayer 2 score: " + player2.returnWin()
									+ "\nRounds tied: " + tieCounter);		// Final print statement with statistics before the system exits.

							System.exit(0);						// Exits the program.
						}
						else if(reply == JOptionPane.CLOSED_OPTION)
						{
							continue;
						}
					}
				}
			}
		}
	}

	public void mouseClicked(MouseEvent e) 			// This executes every time the clicker is clicked anywhere on the Applet.
	{
		mouseClicked = true;						// Changes this boolean to true so above statements can detect whether the mouse was clicked or not.
		xMouse = e.getX();							// Stores the x coord of the clicker in this variable.
		yMouse = e.getY();							// Stores the y coord of the clicker in this variable.

		repaint();									// Repaints so new symbols and things can be drawn on the Applet.
	}

	public void mouseEntered(MouseEvent e) {}		// Non-used methods that need to be in the program for Mouse Listener to work.

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}

	private Player player1, player2;
	private boolean mouseClicked, gameEnded;
	private int xMouse, yMouse, counter, roundCounter, tieCounter;
	private GridFunctions grid;
}
