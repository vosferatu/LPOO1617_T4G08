package dkeep.logic;

/**
 * @author João Mendes
 *	interface for the game logic
 */
public interface GameLogic {

	/**
	 * issues hero's move and updates game elements
	 * @param move direction to move hero
	 * @return true if won level or was defeated, otherwise is still playing
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
	String printMap();
	
}
