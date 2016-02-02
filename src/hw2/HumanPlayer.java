package hw2;
import java.util.Scanner;




public class HumanPlayer implements Player {
	
	int playerNumber;
	Scanner input;
	boolean prints = true;
	
	HumanPlayer(int playerNum, Scanner in)
	{
		playerNumber = playerNum;
		this.input = in;
	}
	
	public void togglePrints()
	{
		if(prints)
			{
			prints = false;
			return;
			}
		prints = false;
	}
	
	public Board takeTurn(Board currentBoard){
		
		if(prints)
		{
		System.out.println(currentBoard.toString());
		System.out.print("Player " + playerNumber +", choose a column to place a piece.\n");
		System.out.println("Choose a number. the board is above. The following is the number to enter for each according row.");
		System.out.println("[0, 1, 2, 3, 4, 5, 6, 7, 8]");
		}
		int x = -1;
		while(x < 0 || x > 8)
		{
			x = input.nextInt();
			if(!currentBoard.checkColumnIfFull(x)) currentBoard.makeMove(playerNumber, x);
			else x = -1;
		}
		if(prints)
		System.out.println("Move confirmed.");
		return currentBoard;
	}
	
	public int calculateScore(Board currentBoard){
		
		int score =0;
		
		// add up the pieces horizontal/vertical from one another.
		for(int x = 0; x< 9; x++)
			for(int y = 0; y<9; y++)
			{
				if(currentBoard.getPosition(x, y) == this.playerNumber)
				{
						// check right as long as it isn't the edge!
					if(x<8)
						if(currentBoard.getPosition(x+1, y) == this.playerNumber)
						{
							
							// add to map
							// count it up.
							score+= 2;
						}
						
						//check bottom as long as it isn't the edge!
					if(y<8)
						if(currentBoard.getPosition(x, y+1) == this.playerNumber)
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
				if(currentBoard.getPosition(x, y) == this.playerNumber)
				{
					if(x<8 && y<8)
						if(currentBoard.getPosition(x+1, y+1) == this.playerNumber)
						{
							score++;
						}
						
						//check bottom as long as it isn't the edge!
					if(y<8 && x >0)
						if(currentBoard.getPosition(x-1, y+1) == this.playerNumber)
						{
							score++;
						}
				}
				
			}
		return score;
	}
	
	
	
	
	

}
