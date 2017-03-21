package dkeep.logic;

public class Game {
	
	private int ogreCount = 0;
	private State state = State.LEVEL1;
	private GameLogic currentLevel;

	public Game(Personality person) {
		this.setCurrentLevel(new DungeonLevel(person));
	}

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

	@Override
	public String toString() {
		String res = "\n";
		res += currentLevel.printMap();
		return res;
	}

	// --------------------------------------------------------------------------

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public int getOgreCount() {
		return ogreCount;
	}

	public void setOgreCount(int ogreCount) {
		this.ogreCount = ogreCount;
	}

	public GameLogic getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(GameLogic currentLevel) {
		this.currentLevel = currentLevel;
	}

}
