package hw2;
public interface Player {
	
	/**
	 * Prompts players to make a legal move.
	 * @return
	 */
	Board takeTurn(Board currentBoard);
	
	void togglePrints();
	
	int calculateScore(Board currentBoard);
}
