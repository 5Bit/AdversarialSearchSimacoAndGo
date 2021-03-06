package hw2;




public class Node {
	
	private Board board;
	public Node parentNode = null;
	private int score; // cost to get here
	private int playerNumber;
	private int beta;
	private int myPly;
	
	
	/**
	 * Root node constructor
	 */
	Node(Board brd)
	{
		parentNode = null;
		this.board = brd;
	}
	
	Node(Board brd, int playerNum, int ply)
	{
		parentNode = null;
		this.board = brd;
		playerNumber = playerNum;
		myPly = ply;
		score = 0;
		beta = Integer.MAX_VALUE;
	}
	
	Node(Board brd, Node n)
	{
		myPly = n.getPly() + 1;
		parentNode = n;
		this.board = brd;
		if(n.playerNumber == 1) this.playerNumber = 2;
		else this.playerNumber = 1;
		score = calculateScore();
		beta = n.beta;
	}
	
	private int calculateScore()
	{
		return board.calculateScore(playerNumber);
	}
	
	public int getScore()
	{
		return score;
	}
	
	
	
	public int getPly()
	{
		return myPly;
	}
	
	public int getPlayerNum()
	{
		return playerNumber;
	}
	
	/**
	 * Returns a deep copy of the this node's board
	 * @return
	 */
	Board getBoard(){
		Board retBrd = new Board(board);
		return retBrd;
	}
	
}
