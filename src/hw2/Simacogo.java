package hw2;
import java.util.Scanner;


public class Simacogo {
	// main game function. Main will handle loop, this will handle the overhead game rules - nothing like gravity though.
	Board gameBoard;
	Scanner input;
	
	Player p1, p2;
	
	
	/**
	 * Game start!
	 */
	Simacogo()
	{
		gameBoard = new Board(); // make blank board
	}
	
	void setInput(Scanner input){
		this.input = input;
	}
	
	
	
	void setPlayers(int mode)
	{
		if (mode == 1) {
			p1 = new HumanPlayer(1, this.input);
			p2 = new HumanPlayer(2, this.input);
			return;
		}
		else if (mode == 2)
		{
			p1 = new HumanPlayer(1, this.input);

			
			int diff = 0;
			while(diff <= 0)
			{
				System.out.println("Set a difficulty for p2 - any number greater than 0.");
				System.out.println("1 - Easy");
				System.out.println("5 - Medium");
				System.out.println("10 and Above - Get Rekt");
				diff = input.nextInt();
			}
			
			p2 = new Minimax(2, diff);
		}
		else if (mode == 3)
		{
			int diff = 0;
			while(diff <= 0)
			{
				System.out.println("Set a difficulty for p2 - any number greater than 0.");
				System.out.println("1 - Easy");
				System.out.println("5 - Medium");
				System.out.println("10 and Above - Get Rekt");
				diff = input.nextInt();
			}
			p1 = new Minimax(1, diff);
			
			diff = 0;
			while(diff <= 0)
			{
				System.out.println("Set a difficulty for p2 - any number greater than 0.");
				System.out.println("1 - Easy");
				System.out.println("5 - Medium");
				System.out.println("10 and Above - Get Rekt");
				diff = input.nextInt();
			}
			
			p2 = new Minimax(2, diff);
			
		}
		else 
		{
			System.out.println("An incorrect player mode was selected."); 
		}
		
	}
	
	void setAIFight(int diff1, int diff2)
	{
		p1 = new Minimax(1, diff1);
		p2 = new Minimax(2, diff2);
	}
	
	void run()
	{
		int p1Score = 0, p2Score = 0;
		int round = 0;
		String winner;
		do{
		
			
		System.out.println("Round " + round);
		
		gameBoard = p1.takeTurn(gameBoard);
		gameBoard = p2.takeTurn(gameBoard);

		
		p1Score =  p1.calculateScore(gameBoard);
		p2Score =  p2.calculateScore(gameBoard);
		
		System.out.println("Player1's score is " + p1Score);
		System.out.println("Player2's score is " + p2Score);
		
		
//		System.out.println("Would someone like to ragequit? 1: Yes");
//		int x = 0;
//		x = input.nextInt();
//		
//		if(x != 1) break;
//		else System.out.println("Assuming desire to continue.");
		
		
		round++;
		}
		while(!gameBoard.isFull());
		
		if(p1Score > p2Score)
			winner = new String("Player 1");
		else if(p1Score < p2Score)
			winner = new String("Player 2");
		else
			winner = new String("nobody: It is a draw!");
		
		System.out.println(gameBoard.toString());
		System.out.println("The winner of the match is " + winner);
		System.out.println("Player 1's number of pieces on board: " + gameBoard.numberOfPieces(1));
		System.out.println("Player 2's number of pieces on board: " + gameBoard.numberOfPieces(2));
	}
	
	
	
	
	
	

}
