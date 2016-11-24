package TicTacToe;

public class Player
{
	// Creates two of these to keep track of the amount of wins for each player.
	public Player(String playerNum)
	{
		numWins = 0;
	}

	// Adds a win to each player's win count.
	public void addWin()
	{
		numWins++;
	}

	// Tells the console how many wins each player has.
	public int returnWin()
	{
		return numWins;
	}

	private int numWins;
	private String player, name;
}
