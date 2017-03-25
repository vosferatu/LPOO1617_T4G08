package dkeep.logic;

/**
 * @author João Mendes
 *	interface for the game logic
 */
public interface GameLogic {

	/**
	 * issues hero's move and updates game elements
	 * @param move direction to move hero
	 * @return true if won level, otherwise is still playing or was defeated
	 */
	boolean updateGame(Direction move);
	
	/**
	 * checks if hero's level is dead
	 * @return true if is dead, otherwise isn't
	 */
	boolean isHeroDead();
	
	/**
	 * prints a map in a string
	 * @return string representing a map
	 */
	char[][] printMap();
	
	/**
	 * gets the level's hero position
	 * @return hero's position
	 */
	CellPosition getHeroPosition();
	
}
