package hw2;
public interface Player {
	
	/**
	 * Prompts players to make a legal move.
	 * @return
	 */
	Board takeTurn(Board currentBoard);
	
	/**
	 * Used to toggle prints. Used for debugging.
	 */
	void togglePrints();
	
	/**
	 * Used to calculate the player's points.
	 * @param currentBoard
	 * @return
	 */
	int calculateScore(Board currentBoard);
}
