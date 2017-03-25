package dkeep.logic;

/**
 * @author João Mendes
 *	represents the game state
 */
public enum State {
		/**
		 * game is on dungeon level
		 */
		LEVEL1, 
		/**
		 * game is on keep level 
		 */
		LEVEL2, 
		/**
		 * user lost
		 */
		DEFEAT, 
		/**
		 * user won
		 */
		WON
}
