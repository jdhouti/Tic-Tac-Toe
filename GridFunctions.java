package TicTacToe;

import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JOptionPane;

public class GridFunctions extends Applet
{
	public GridFunctions()															// Constructor.
	{
		x = 0;																		// Default pointer coordinates.
		y = 0;
	}
	
	public void drawGrid(Graphics2D g2)												// Draws the grid.
	{				
		Line2D.Double line1 = new Line2D.Double(245, 315, 755, 315);
		Line2D.Double line2 = new Line2D.Double(245, 485, 755, 485);
		Line2D.Double line3 = new Line2D.Double(415, 145, 415, 655);		
		Line2D.Double line4 = new Line2D.Double(585, 145, 585, 655);
		Line2D.Double line5 = new Line2D.Double(245, 145, 755, 145);
		Line2D.Double line6 = new Line2D.Double(755, 145, 755, 655);
		Line2D.Double line7 = new Line2D.Double(245, 655, 755, 655);
		Line2D.Double line8 = new Line2D.Double(245, 145, 245, 655);
		
		g2.setStroke(new BasicStroke(3.0F));
		g2.draw(line1);
		g2.draw(line2);
		g2.draw(line3);
		g2.draw(line4);
		g2.setStroke(new BasicStroke(4.5F));
		g2.draw(line5);
		g2.draw(line6);
		g2.draw(line7);
		g2.draw(line8);
		
		Font myFont = new Font("Monospace", Font.BOLD, 40);
		g2.setFont(myFont);
		g2.drawString("Player 1 (X)", 20, 60);
		
		FontRenderContext context = g2.getFontRenderContext();					
	    Rectangle2D firstBounds = myFont.getStringBounds("Player 2 (O)", context);	// Creates a bounds object to find the height and length of "Player 2"
	    double xMessageWidth = firstBounds.getWidth();
	    int xM = (int)xMessageWidth;
	    g2.drawString("Player 2 (O)", 1000 - (xM + 20), 60);						// Draws the string "Player 2" neatly (not cut off) using height and length.
	}

	public void clearGrid(Graphics2D g2)											// Clears the grid of any remaining pieces when the game is finished.
	{
		Rectangle2D.Double b1 = new Rectangle2D.Double(250, 150, 160, 160);
		Rectangle2D.Double b2 = new Rectangle2D.Double(250, 320, 160, 160);
		Rectangle2D.Double b3 = new Rectangle2D.Double(250, 490, 160, 160);
		Rectangle2D.Double b4 = new Rectangle2D.Double(420, 150, 160, 160);
		Rectangle2D.Double b5 = new Rectangle2D.Double(420, 320, 160, 160);
		Rectangle2D.Double b6 = new Rectangle2D.Double(420, 490, 160, 160);
		Rectangle2D.Double b7 = new Rectangle2D.Double(590, 150, 160, 160);
		Rectangle2D.Double b8 = new Rectangle2D.Double(590, 320, 160, 160);
		Rectangle2D.Double b9 = new Rectangle2D.Double(590, 490, 160, 160);
		
		g2.setColor(Color.WHITE);
		
		g2.fill(b9);																// Fills all 9 boxes with white boxes, overlapping any pieces.
		g2.fill(b8);
		g2.fill(b7);
		g2.fill(b6);
		g2.fill(b5);
		g2.fill(b4);
		g2.fill(b3);
		g2.fill(b2);
		g2.fill(b1);
		
		g2.draw(b1);
		g2.draw(b2);
		g2.draw(b3);
		g2.draw(b4);
		g2.draw(b5);
		g2.draw(b6);
		g2.draw(b7);
		g2.draw(b8);
		g2.draw(b9);
	}
	
	public void updateCoords(int newXCoord, int newYCoord)							// Updates the pointer coords sent by the main class.
	{
		x = newXCoord;
		y = newYCoord;
	}
	
	public int returnX()															// Returns the X coordinate needed to either draw the X or O in the correct box. This X value is used in the DrawPieces class as
	{																				// xCorner.
		if(x > 245 && x < 415)														//		+----------+----------+----------+
		{																			//		|.		   |.         |.         |			Those dots are potential X coords that help determine where the piece
			return 255;																//		|		   |		  |			 |			should be drawn.
		}																			//		|		   |		  |		     |
		else if(x > 415 && x < 585)													//      +----------+----------+----------+
		{																			//      |		   |		  |			 |
			return 425;																//      |		   |		  |			 |
		}																			//      |		   |		  |			 |
		else if(x > 585 && x < 755)													//      +----------+----------+----------+
		{																			//      |		   |		  |			 |
			return 595;																//      |		   |		  |			 |
		}																			//      |		   |		  |			 |
		else																		//      +----------+----------+----------+
		{
			return 0;																// Returns 0 if the mouse clicker was not clicked within the grid.
		}
	}
	
	public int returnY()															// Returns the Y coordinate needed to either draw the X or O in the correct box. This Y value is used in the DrawPieces class as
	{																				// yCorner.
		if(y > 145 && y < 315)														//      +----------+----------+----------+
		{																			//      |.   	   |		  |			 |			Those dots are potential Y coords that help determine where the piece 
			return 155;																//      |		   |		  |			 |			should be drawn.
		}																			//      |		   |		  |			 |
		else if(y > 315 && y < 485)													//      +----------+----------+----------+
		{																			//      |.   	   |		  |			 |
			return 325;																//      |		   |		  |			 |
		}																			//      |		   |		  |			 |
		else if(y > 485 && y < 655)													//      +----------+----------+----------+
		{																			//      |.   	   |		  |			 |
			return 495;																//      |		   |		  |			 |
		}																			//      |		   |		  |			 |
		else																		//      +----------+----------+----------+
		{
			return 0;																// Returns 0 if the mouse clicker was not clicked within the grid.
		}
	}
	
	public void updateGrid(int a)													// This is the least optimized part of the entire program. 
	{
		if(this.returnX() == 255 && this.returnY() == 155)							// If the X returned from .returnX() and the Y returned from .returnY() match the statement, the clicker was clicked in that box.
		{
			if(a == 1)																// Determines whether an X or O is occupying that box. This is later used to determine when there is a winner.
			{
				box1 = 1;															// If box1 = 1, it is occupied by an O
			}
			else if(a == 2)
			{
				box1 = 2;															// If it 2, it is occupied by an X.
			}
		}
		else if(this.returnX() == 255 && this.returnY() == 325)
		{
			if(a == 1)
			{
				box2 = 1;
			}
			else if(a == 2)
			{
				box2 = 2;
			}
		}
		else if(this.returnX() == 255 && this.returnY() == 495)
		{
			if(a == 1)
			{
				box3 = 1;
			}
			else if(a == 2)
			{
				box3 = 2;
			}
		}
		else if(this.returnX() == 425 && this.returnY() == 155)
		{
			if(a == 1)
			{
				box4 = 1;
			}
			else if(a == 2)
			{
				box4 = 2;
			}
		}
		else if(this.returnX() == 425 && this.returnY() == 325)
		{
			if(a == 1)
			{
				box5 = 1;
			}
			else if(a == 2)
			{
				box5 = 2;
			}
		}
		else if(this.returnX() == 425 && this.returnY() == 495)
		{
			if(a == 1)
			{
				box6 = 1;
			}
			else if(a == 2)
			{
				box6 = 2;
			}
		}
		else if(this.returnX() == 595 && this.returnY() == 155)
		{
			if(a == 1)
			{
				box7 = 1;
			}
			else if(a == 2)
			{
				box7 = 2;
			}
		}
		else if(this.returnX() == 595 && this.returnY() == 325)
		{
			if(a == 1)
			{
				box8 = 1;
			}
			else if(a == 2)
			{
				box8 = 2;
			}
		}
		else if(this.returnX() == 595 && this.returnY() == 495)
		{
			if(a == 1)
			{
				box9 = 1;
			}
			else if(a == 2)
			{
				box9 = 2;
			}
		}
	}
	
	public int checkBox()															// This is also not very optimized.
	{
		int a = 1;																	// a is initialized at 1.
		if(this.returnY() == 155 && this.returnX() == 255)							// Updates a in function of which box is taken up by a piece.
		{																			// This method will make more sense if you check the .checkWinner() method in this class.
			a = box1;
		}
		else if(this.returnX() == 255 && this.returnY() == 325)
		{
			a = box2;
		}
		else if(this.returnX() == 255 && this.returnY() == 495)
		{
			a = box3;
		}
		else if(this.returnX() == 425 && this.returnY() == 155)
		{
			a = box4;
		}
		else if(this.returnX() == 425 && this.returnY() == 325)
		{
			a = box5;
		}
		else if(this.returnX() == 425 && this.returnY() == 495)
		{
			a = box6;
		}
		else if(this.returnX() == 595 && this.returnY() == 155)
		{
			a = box7;
		}
		else if(this.returnX() == 595 && this.returnY() == 325)
		{
			a = box8;
		}
		else if(this.returnX() == 595 && this.returnY() == 495)
		{
			a = box9;
		}
		
		return a;
	}
	
	public int checkWinner()														// Checks whether there is a winner of it's a tie.
	{
		if((box1 == 2 && box2 == 2 && box3 == 2) || (box1 == 2 && box4 == 2 && box7 == 2) || (box2 == 2 && box5 == 2 && box8 == 2) || (box3 == 2 && box6 == 2 && box9 == 2) 
				|| (box4 == 2 && box5 == 2 && box6 == 2) || (box7 == 2 && box8 == 2 && box9 == 2) || (box3 == 2 && box5 == 2 && box7 == 2) || (box1 == 2 && box5 == 2 && box9 == 2))
		{
			return 1;																// If this returns 1, player 1 has won.
		}
		else if((box1 == 1 && box2 == 1 && box3 == 1) || (box1 == 1 && box4 == 1 && box7 == 1) || (box2 == 1 && box5 == 1 && box8 == 1) || (box3 == 1 && box6 == 1 && box9 == 1)
				|| (box4 == 1 && box5 == 1 && box6 == 1) || (box7 == 1 && box8 == 1 && box9 == 1) || (box3 == 1 && box5 == 1 && box7 == 1) || (box1 == 1 && box5 == 1 && box9 == 1))
		{
			return 2;																// If this returns 2, player 2 has won.
		}
		else if(box1 > 0 && box2 > 0 && box3 > 0 && box4 > 0 && box5 > 0 && box6 > 0 && box7 > 0 && box8 > 0 && box9 > 0)
		{
			return 3;																// Returns 3 if the game is tied.
		}
		else return 4;
	}

	public void resetBoxes()														// When a new game is started, all box values are reset.
	{
		box1 = 0;
		box2 = 0;
		box3 = 0;
		box4 = 0;
		box5 = 0;
		box6 = 0;
		box7 = 0;
		box8 = 0;
		box9 = 0;
	}
	
	private int x, y, box1, box2, box3, box4, box5, box6, box7, box8, box9;
}