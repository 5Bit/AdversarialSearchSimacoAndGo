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
	
	/**
	 * Minimax Constructor.
	 * @param playerNum
	 * @param difficulty
	 */
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
	
	/**
	 * Toggles minimax's prints. Useful for running debugging.
	 */
	public void togglePrints()
	{
		if(prints) 
			{
			prints = false;
			return;
			}
		prints = true;
	}
	

	/**
	 * Prompts minimax to take it's turn
	 */
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
		move = makeMoveWithNodes(currentBoard, depth);
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
					if(x<8 && y<8)
						if(currentBoard.getPosition(x+1, y+1) == oppNum)
						{
							score++;
						}
					if(x<8)
						if(currentBoard.getPosition(x+1, y) == oppNum)
						{
							score+= 2;
						}
						//check bottom as long as it isn't the edge!
					if(y<8)
						if(currentBoard.getPosition(x, y+1) == oppNum)
						{
							score += 2;
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
		
		Node nextMove = move(root, ply, abPruneAlpha, abPruneBeta);
		return nextMove.getBoard(); // getting the board ahead - fix!
	}
	
	/**
	 * Helper used by makeMoveWithNodes. does all of the heavy lifting.
	 * @param currentState
	 * @param ply
	 * @param alpha
	 * @param beta
	 * @return
	 */
	private Node move(Node currentState, int ply, int alpha, int beta)
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
			// loop through the successors
			for(int i = 0; i < newSuccessors.size(); i++)
			{
				//get the move
					Node thisMove = move(new Node(newSuccessors.get(i), currentState), ply-1, alpha, beta);
					
					//if the move is worse than the previous for the opponent...
					if(thisMove.getScore() < bestChoice.getScore()) 
						{
						bestChoice = thisMove; // this is ideal
						chosenPos = i;
						}
					// alpha beta pruning - sets beta and prunes this path if it proves bad.
					if(thisMove.getScore() < beta) beta = thisMove.getScore();
					if(alpha >= beta) break; 
			}

			return new Node(newSuccessors.get(chosenPos));
		}
		else //AIs turn
		{
			bestChoice = new Node(newSuccessors.get(0), currentState);
			int thisMinNodeValB = bestChoice.getScore();
			// loops through the choices
			for(int i = 0; i < newSuccessors.size(); i++)
			{
				// get the move
				Node thisMove = move(new Node(newSuccessors.get(i), currentState), ply-1, alpha, beta);
				// if the move is better than the previous for Minimax
				if(thisMove.getScore() >= bestChoice.getScore())
				{
					bestChoice = thisMove;
					chosenPos = i; // need to keep track of both the chosen pos and the end, passing the chosen
				}
				// alpha beta pruning. sets alpha here when this score is better.
				if(thisMove.getScore() > beta) alpha = thisMove.getScore();
				if(alpha >= beta) break;
				
			}
			return new Node(newSuccessors.get(chosenPos));
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
