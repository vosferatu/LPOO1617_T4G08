package dkeep.logic;

import java.util.ArrayList;

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
				this.state = State.FROM1TO2;
			} else {
				if (currentLevel.isHeroDead())
					state = State.DEFEAT;
			}
			break;

		case FROM1TO2:
			System.out.println(this); // prints hero getting on stairs
			this.setCurrentLevel(new KeepLevel(ogreCount)); //creates ogre map
			state = State.LEVEL2;
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

		for (int i = 0; i < map.getMap().length; i++) {
			for (int j = 0; j < map.getMap()[i].length; j++) {
				boolean noChar = true;

				if (hero.isAt(j, i)) {
					res += hero;
					noChar = false;
				} else if (guard.isAt(j, i)) {
					res += guard;
					noChar = false;
				} else {
					for (Ogre x : ogres) {
						if (x.getClub().getPosition().isAt(j, i)) {
							if (lever.getPosition().isAt(j, i)) {
								res += "$";
								noChar = false;
								break;
							} else {
								res += x.getClub();
								noChar = false;
								break;
							}
						} else if (x.isAt(j, i)) {
							if (lever.getPosition().isAt(j, i)) {
								res += "$";
								noChar = false;
								break;
							} else {
								res += x;
								noChar = false;
								break;
							}
						}
					}
				}
				if (noChar) {
					if (lever.getPosition().isAt(j, i)) {
						res += lever;
					} else {
						res += map.getMap()[i][j];
					}
				}
				res += " ";
			}
			res += "\n";

		}
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
