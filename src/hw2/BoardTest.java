package hw2;
import java.util.Scanner;

import org.junit.Test;


public class BoardTest {
	
	
	@Test
	public void toStringTest()
	{
		Board x = new Board();
		
		//x.makeMove(1, 4);
		x.makeMove(2, 5);
		x.makeMove(1, 6);
		x.makeMove(1, 8);
		x.makeMove(1, 6);
		x.makeMove(1, 8);
		x.makeMove(1, 5);

		
		System.out.println(x.toString());
		
		Scanner in = new Scanner(System.in);
	
		Player p1 = new HumanPlayer(1, in);
		
		Player p2 = new HumanPlayer(2, in);
		
		
		System.out.println("Player1's score is " + p1.calculateScore(x));
		
		x.makeMove(2, 5);
		x.makeMove(1, 5);
		x.makeMove(2, 4);
		x.makeMove(2, 4);
		x.makeMove(1, 4);

		System.out.println(x.toString());

 
	}

}
