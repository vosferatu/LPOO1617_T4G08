package dkeep.logic;

/**
 * @author João Mendes
 * 
 * class to represent the game
 */
public class Game {
	
	/**
	 * represents the number of ogres
	 */
	private int ogreCount = 0;
	/**
	 * represents the current game state
	 */
	private State state = State.LEVEL1;
	/**
	 * represents the game's current level
	 */
	private GameLogic currentLevel;

	/**
	 * creates a new game
	 * @param person guard's personality
	 */
	public Game(Personality person) {
		this.setCurrentLevel(new DungeonLevel(person));
	}

	/**
	 * updates the game elements
	 * @param move direction to move hero
	 * @return the updated game state
	 */
	public State updateGame(Direction move) {

		switch (state) {
		case LEVEL1:
			if (currentLevel.updateGame(move)) {// passed dungeon level
				this.setCurrentLevel(new KeepLevel(ogreCount)); //creates ogre map
				this.state = State.LEVEL2;
			} else {
				if (currentLevel.isHeroDead())
					state = State.DEFEAT;
			}
			break;

		case LEVEL2:
			if (currentLevel.updateGame(move)) {

				state = State.WON;
			} else {
				if (currentLevel.isHeroDead())
					state = State.DEFEAT;
			}
			break;

		case WON:
			state = State.WON;
			break;

		case DEFEAT:
			state = State.DEFEAT;
			break;

		default:
			break;
		}

		return state;
	}

	// -------------------------PRINT--------------------------------------------

	/**
	 * prints the game map
	 */
	@Override
	public String toString() {
		String res = "\n";
		res += currentLevel.printMap();
		return res;
	}

	// --------------------------------------------------------------------------

	/**
	 * gets the game state
	 * @return state
	 */
	public State getState() {
		return state;
	}

	/**
	 * sets the current game state
	 * @param state new state
	 */
	public void setState(State state) {
		this.state = state;
	}
	
	/**
	 * gets the number of ogres
	 * @return ogres number
	 */
	public int getOgreCount() {
		return ogreCount;
	}

	/**
	 * sets the number of ogres
	 * @param ogreCount new ogre number
	 */
	public void setOgreCount(int ogreCount) {
		this.ogreCount = ogreCount;
	}

	/**
	 * gets the current level
	 * @return current level
	 */
	public GameLogic getCurrentLevel() {
		return currentLevel;
	}

	/**
	 * sets the current level
	 * @param currentLevel new current level
	 */
	public void setCurrentLevel(GameLogic currentLevel) {
		this.currentLevel = currentLevel;
	}

}
