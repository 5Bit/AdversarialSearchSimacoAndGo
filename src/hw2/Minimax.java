package hw2;
import java.util.ArrayList;


public class Minimax implements Player{
	
	int playerNumber;
	int depth;
	int originalPly;
	int oppNum;
	boolean prints = true;
	int abPruneAlpha;
	int abPruneBeta;
	
	public Minimax(int playerNum, int difficulty)
	{
		playerNumber = playerNum;
		originalPly = difficulty;
		depth = 2*difficulty;
		
		if(playerNumber == 1) oppNum = 2;
		else oppNum = 1;
		abPruneAlpha = Integer.MIN_VALUE;
		abPruneBeta = Integer.MAX_VALUE;
		
		
	}
	
	public void togglePrints()
	{
		if(prints) 
			{
			prints = false;
			return;
			}
		prints = true;
	}
	


	public Board takeTurn(Board currentBoard) {

		if(prints)
		{
		System.out.print("Minimax " + playerNumber +", choose a column to place a piece.\n");
		System.out.println("Choose a number. the board is above. The following is the number to enter for each according row.");
		System.out.println("[0, 1, 2, 3, 4, 5, 6, 7, 8]");
		System.out.println("Calculating");
		}
		Board move = null;
		// get move
//		move = makeMove(currentBoard, depth);
		move = makeMoveWithNodes(currentBoard, depth);
		//System.out.println(currentBoard);
		//return move
		return move;
	}



	/**
	 * Calculates the score of Minimax's opponent.
	 * @param currentBoard
	 * @return
	 */
	public int calculateOpponentScore(Board currentBoard)
	{
		int score =0;
		int oppNum;
		if(this.playerNumber !=1) oppNum = 1;
		else oppNum = 0;
		
		// add up the pieces horizontal/vertical from one another.
		for(int x = 0; x< 9; x++)
			for(int y = 0; y<9; y++)
			{
				if(currentBoard.getPosition(x, y) == oppNum)
				{
						// check right as long as it isn't the edge!
					if(x<8)
						if(currentBoard.getPosition(x+1, y) == oppNum)
						{
							
							// add to map
							// count it up.
							score+= 2;
						}
						
						//check bottom as long as it isn't the edge!
					if(y<8)
						if(currentBoard.getPosition(x, y+1) == oppNum)
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
				if(currentBoard.getPosition(x, y) == oppNum)
				{
					if(x<8 && y<8)
						if(currentBoard.getPosition(x+1, y+1) == oppNum)
						{
							score++;
						}
						
						//check bottom as long as it isn't the edge!
					if(y<8 && x >0)
						if(currentBoard.getPosition(x-1, y+1) == oppNum)
						{
							score++;
						}
				}
				
			}
		return score;
	}
	
	
	/**
	 * Calculates the score of Minimax
	 */
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
	
	
	/**
	 * returns the calculated next move with nodes. Possibly a fix to the makeMove
	 * @param currentBoard
	 * @param ply
	 * @return
	 */
	public Board makeMoveWithNodes(Board currentBoard, int ply)
	{
		Node root = new Node(currentBoard, 1, 1);
		
		Node nextMove = move(root, ply);
		return nextMove.getBoard(); // getting the board ahead - fix!
	}
	
	
	private Node move(Node currentState, int ply)
	{
		int chosenPos = 0;
		if(prints)
		System.out.println("On ply" + currentState.getPly());
		if(currentState.getPly() == depth) return currentState;
		if(currentState.getBoard().isFull()) return currentState;
		ArrayList<Board> newSuccessors = calculateSuccessors(currentState.getBoard());
		Node bestChoice = new Node(newSuccessors.get(chosenPos), currentState);
		// then it is the Player's turn to make a move
		if(currentState.getPlayerNum() == 2)
		{
			bestChoice = new Node(newSuccessors.get(chosenPos), currentState);
			for(int i = 0; i < newSuccessors.size(); i++)
			{
				// if the next move is worst for the opponent, kill it with fire and go back up.
				
				Node thisMove = move(new Node(newSuccessors.get(i), currentState), ply-1);
				if(thisMove.getScore() < bestChoice.getScore()) 
					{
					bestChoice = thisMove;
					chosenPos = i;
					}
			}
//			
//			for(Board b: newSuccessors)
//			{
//				Node thisMove = move(new Node(b, currentState), ply-1);// change to pos!
//				if(thisMove.getScore() < bestChoice.getScore()) bestChoice = thisMove; // TODO - discern what is wrong with minimax
//			}
//			return bestChoice;
			return new Node(newSuccessors.get(chosenPos));
		}
		else
		{
			bestChoice = new Node(newSuccessors.get(0), currentState);
			for(int i = 0; i < newSuccessors.size(); i++)
			{
				
				Node thisMove = move(new Node(newSuccessors.get(i), currentState), ply-1);
				if(thisMove.getScore() > bestChoice.getScore())
				{
					bestChoice = thisMove;
					chosenPos = i; // need to keep track of both the chosen pos and the end, passing the chosen
				}
			}
//			return bestChoice;
			return new Node(newSuccessors.get(chosenPos));
		}
		

//		else// AI's turn is next
//		{
//			bestChoice = new Node(newSuccessors.get(0), currentState);
//			for(Board b: newSuccessors)
//			{
//				Node thisMove = move(new Node(b, currentState), ply-1);
//				if(thisMove.getScore() > bestChoice.getScore()) bestChoice = thisMove;
//			}
//			return bestChoice;
//		}
	}
	
	
	
	
	/**
	 * should recursively look through using basic minimax to make a move!
	 * @param currentBoard
	 * @param ply
	 * @return
	 */
	public Board makeMove(Board currentBoard, int ply)
	{
		//TODO - minimax only accounts for own decision at the moment. doesn't take a shot at looking at the opponents choices.
		if(prints)
		System.out.println("On ply" + ply);
		if(ply == 0) return currentBoard;
		if(currentBoard.isFull()) return currentBoard;
		ArrayList<Board> newSuccessors = calculateSuccessors(currentBoard);
		Board move = newSuccessors.get(0);
		Board bestMove = newSuccessors.get(0);// get the first, because it needs something to avoid a null issue
		
		if(originalPly % ply != 0) // miniMax
		{ // should alter so it only returns 1 move. Remove moves?
			// TODO should be predicting player's moves in here - is not. is making two moves and applying them!
						for(Board b : newSuccessors)
						{
							move = makeMove(b, ply-1);
							if(move.calculateScore(playerNumber) < bestMove.calculateScore(playerNumber)) bestMove = move;
						}
						return bestMove;
		}
		else
		{
			for(Board b : newSuccessors)
			{
				move = makeMove(b, ply-1);
				if(move.calculateScore(playerNumber) > bestMove.calculateScore(playerNumber)) bestMove = move;
			}
			return bestMove;
		}
	}
	

	
	

	/**
	 * Creates successors for Minimax on cmd
	 * @param board
	 * @return
	 */
	public  ArrayList<Board> calculateSuccessors(Board board)
	{
		
		ArrayList<Board> successors = new ArrayList<Board>();
		
		for(int i = 0; i < 9; i++)
		{
			// if i position isn't full
			//System.out.println("Column " + i + "is full: " + board.checkColumnIfFull(i));
			if(!board.checkColumnIfFull(i))
			{
				Board tempBoard = new Board(board);
				
				tempBoard.makeMove(playerNumber, i);
				successors.add(tempBoard);
			}
				
			
		} // creates all successors - brute force.
		return successors;
	}

}
