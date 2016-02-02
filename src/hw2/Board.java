package hw2;
import java.util.Arrays;


public class Board {
	//TODO - verify nothing is missing.
	
	private int[][] grid = new int[9][9]; // make das grid
	private static final int playerOne = 1;
	private static final int playerTwo = 2;
	private static final int empty = 0;
	
	// 0 = empty
	// 1 = player 1
	// 2 = player 2
	
	
	/**
	 * Empty board constructor
	 */
	Board()
	{
		
		// set up grid.
		for( int x = 0; x < 9; x++)
			for(int y= 0; y< 9; y++)
				grid[x][y] = 0;
	}
	
	Board(Board original)
	{
		// copy board
		for( int x = 0; x < 9; x++)
			for(int y= 0; y< 9; y++)
				grid[x][y] = original.getPosition(x, y);
	}
	

	public int numberOfPieces(int playerNum)
	{
		int ret = 0;
		for(int x = 0; x < 9; x++)
			for(int y = 0; y < 9; y++)
				if(grid[x][y] == playerNum) ret++;
		return ret;
	}
	
	
	/**
	 * used by players to make moves.
	 * @param playerNum
	 * @param slotInsertedInto
	 */
	public void makeMove(int playerNum, int slotInsertedInto)
	{
		grid[slotInsertedInto][0] = playerNum;
		pieceGravity(slotInsertedInto);
	}
	
	
	/**
	 * Handles gravity movement for the pieces. Moves any pieces inserted with makeMove down to the last blank slot.
	 * @param xPos
	 */
	private void pieceGravity(int xPos)
	{
		if(grid[xPos][1] != 0) 
		return;
			
			
		int player = grid[xPos][0];
		
		int lowestYPos = 0;
		
		for(int i = 1; i < 9; i++)
		{
			if(grid[xPos][i] != 0) break;
			lowestYPos = i;
		}
		grid[xPos][lowestYPos] = player;
		grid[xPos][0] = 0; // reset the start pos
	}
	
	/**
	 * Tells if the column is full. Returns true if it is full
	 * @param slotInsertedInto
	 * @return
	 */
	public boolean checkColumnIfFull(int slotInsertedInto)
	{
		if(grid[slotInsertedInto][0] != 0) return true;
		else return false;
	}
	
	
	/**
	 * Returns the a deep copy of the current grid
	 * @return
	 */
	public int[][] getGridCopy()
	{
		int[][] tempGrid = new int[9][9];
		
		// do hard copy here!
		for( int x = 0; x < 9; x++)
			for(int y= 0; y< 9; y++)
				tempGrid[x][y] = (int) grid[x][y];
		return tempGrid;
	}
	
	/**
	 * Returns the item in position x,y. If 0, nobody. 1, player 1. 2, player 2.
	 * @param x
	 * @param y
	 * @return
	 */
	public int getPosition(int x, int y)
	{
		return (int) grid[x][y];
	}
	
	public boolean isFull()
	{
		for( int x = 0; x < 9; x++)
			for(int y= 0; y< 9; y++)
				if(grid[x][y] == 0) return false;
		
		return true;
	}
	
	
	/**
	 * Checks if the two objects are equal.
	 */
	public boolean equals(Object that) {
		if (this == that)
			return true;
		if (that == null)
			return false;
		if (this.getClass() != that.getClass())
			return false;
		Board thatLocal = (Board) that;
		for (int x = 0; x < thatLocal.grid.length; x++) {
			for(int y = 0; y < thatLocal.grid.length; y++)
				if (thatLocal.grid[x][y] != this.grid[x][y]) return false;
		}
		return true;
	}
	
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		int[][] representation = new int[9][9];

		for (int x = 0; x < 9; x++) {
			int temp[] = grid[x];
			
			for(int y = 0; y <9; y++)
				representation[y][x] = temp[y];
		}
		for(int x = 0; x < 9; x++)
		{
			int temp[] = representation[x];
			
			str.append(Arrays.toString(temp));
			str.append("\n");
		}
		return str.toString();
	}
	
	public int calculateScore(int plyr)
	{
		int score =0;
		
		// add up the pieces horizontal/vertical from one another.
		for(int x = 0; x< 9; x++)
			for(int y = 0; y<9; y++)
			{
				if(this.getPosition(x, y) == plyr)
				{
						// check right as long as it isn't the edge!
					if(x<8)
						if(this.getPosition(x+1, y) == plyr)
						{
							
							// add to map
							// count it up.
							score+= 2;
						}
						
						//check bottom as long as it isn't the edge!
					if(y<8)
						if(this.getPosition(x, y+1) == plyr)
						{
							score += 2;
						}
				}
			}
		
		
		// calculate diagonal ones.
		// calculate them by checking the immediate bottom left and bottom right array positions, and incrementing by one.
		
		for(int x = 0; x< 9; x++)
			for(int y = 0; y < 9; y++)
			{
				if(this.getPosition(x, y) == plyr)
				{
					if(x<8 && y<8)
						if(this.getPosition(x+1, y+1) == plyr)
						{
							score++;
						}
						
						//check bottom as long as it isn't the edge!
					if(y<8 && x >0)
						if(this.getPosition(x-1, y+1) == plyr)
						{
							score++;
						}
				}
				
			}
		return score;
	}
	
}
