package TicTacToe;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class DrawPieces
{
	// Draws the X piece based on the xCorner and yCorner of the box.
	public void drawX(Graphics2D g2, int xCorner, int yCorner)
	{
		Line2D.Double line1 = new Line2D.Double(xCorner, yCorner, xCorner + 150, yCorner + 150);
		Line2D.Double line2 = new Line2D.Double(xCorner, yCorner + 150, xCorner + 150, yCorner);
		Color pink = new Color(255, 0, 102);
		g2.setColor(pink);
		g2.setStroke(new BasicStroke(4.0F));
		g2.draw(line1);
		g2.draw(line2);
	}

	// Draws the O piece based on the xCorner and yCorner of the box.
	public void drawO(Graphics2D g2, int xCorner, int yCorner)
	{
		Ellipse2D.Double circle = new Ellipse2D.Double(xCorner, yCorner, 150, 150);
		Color blue = new Color(0, 162, 255);
		g2.setColor(blue);
		g2.setStroke(new BasicStroke(4.0F));
		g2.draw(circle);
	}

	public void drawWhosTurn(Graphics2D g2, int p)
	{
		Font myFont = new Font("Monospace", Font.BOLD, 40);
		FontRenderContext context = g2.getFontRenderContext();
		// Creates a bounds object to find the height and length of "Player 2"
	  Rectangle2D firstBounds = myFont.getStringBounds("Player 2 (X)", context);
	  Rectangle2D secondBounds = myFont.getStringBounds("Player (O)", context);
	  double xMessageWidth = firstBounds.getWidth();
	  double xMessageHeight = firstBounds.getHeight();
	  double xMessageWidth2 = secondBounds.getWidth();

		Rectangle2D.Double cover = new Rectangle2D.Double(xMessageWidth + 20, 60 - xMessageHeight, xMessageHeight, (1000 - ((xMessageWidth + 20) + (xMessageWidth2 = 20))));
		g2.setColor(Color.WHITE);
		g2.fill(cover);

		if(p == 1)
		{
			g2.draw(cover);
			g2.setColor(Color.BLACK);
			g2.drawString("Player 1's turn!", (int) (40 + xMessageWidth), 60);
		}
		else if(p == 2)
		{
			g2.draw(cover);
			g2.setColor(Color.BLACK);
			g2.drawString("Player 2's turn!", (int) (40 + xMessageWidth), 60);
		}
	}
}
