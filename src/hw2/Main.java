package hw2;
import java.util.Scanner;


public class Main {
	
	public static void main(String[] args) {
		
		
		int difficulty;

		String inLine;
		Scanner in = new Scanner(System.in);

		System.out.println("Shall we play a game? Y/N");

		inLine = in.nextLine();

		inLine = inLine.toLowerCase();
		
		if(inLine.equalsIgnoreCase("T"))
		{
			System.out.println("Test selected. Running test versions now.");
			
			Simacogo thisGame = new Simacogo();
			thisGame.setInput(in);
			thisGame.setAIFight(1, 3);
			//thisGame.p1.togglePrints();
			//thisGame.p2.togglePrints();
			thisGame.run();
		}

		while(inLine.equalsIgnoreCase("Y"))
		{
			System.out.println("Choose your difficulty: 1-9");
			
			
			
			//call game here TODO
			Simacogo thisGame = new Simacogo();
			
			int i = 0;
			do {

				System.out.println("What mode would you like to play? ");
				System.out.println("1 | Player versus Player");
				System.out.println("2 | Player versus Minimax");
				i = in.nextInt();

			} while (i > 3 || i <= 0);
			
			thisGame.setInput(in);
			thisGame.setPlayers(i);
			
			thisGame.run();
			
			// check if want to try again?
			System.out.println("Shall we play another game? Y/N");
			inLine = in.nextLine();
			inLine = inLine.toLowerCase();
		}
		in.close();
		System.out.println("Exiting program.");
		return;
	}

}
