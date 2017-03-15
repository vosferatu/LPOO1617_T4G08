package dkeep.logic;

import java.util.ArrayList;

public class Game {

	public GameMap map = null;
	private Hero hero = null;
	private Guard guard = null;
	private Lever lever = null;
	private ArrayList<Door> doors = new ArrayList<Door>();
	private ArrayList<Ogre> ogres = new ArrayList<Ogre>();
	private State state = State.LEVEL1;

	public Game() {
		map = new GameMap(this.state);
		hero = new Hero();
		guard = new Guard();
		lever = new Lever(7, 8);
		doors.add(new Door(new CellPosition(0, 5)));
		doors.add(new Door(new CellPosition(0, 6)));
	}

	public State updateGame(Direction move) {

		switch (state) {
		case LEVEL1:
			if (dungeonLevel(move)) {// passed dungeon level
				this.state = State.FROM1TO2;
			} else {
				if (this.hero.isDead())
					state = State.DEFEAT;
			}
			break;

		case FROM1TO2:
			System.out.println(this); // prints hero getting on stairs

			hero = new Hero(1, 7);
			guard = null;
			lever = new Lever(7, 1);
			doors.clear();
			doors.add(new Door(new CellPosition(0, 1)));
			map = new GameMap(this.state);

			state = State.LEVEL2;
			break;

		case LEVEL2:
			if (keepLevel(move)) {

				state = State.WON;
			} else {
				if (this.hero.isDead())
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

	private boolean dungeonLevel(Direction move) {// returns true if passed
													// level, false otherwise
		boolean done = false;

		// move hero
		if (!map.heroMovePossible(hero, move))// prevent guard from moving
			return false; // if hero moves to wall/closed door

		hero.move(map, move);

		// move guard
		guard.nextMove();

		// checks if hero is at lever position if he hasn't the key already
		if (!hero.hasKey()) {
			if (hero.turnLever(lever)) {
				map.openDoors();
				for (Door door : doors) {
					door.openDoor();
				}
			}
		}

		// checks if hero is going to die
		if (hero.isAdjacent(guard)) {
			hero.setDead(true);
		}

		// checks if hero is at a stair
		for (Door door : doors) {
			if (hero.getPosition().equals(door.getPosition()) && door.isOpen()) {
				done = true;
				break;
			}
		}

		return done;
	}

	private boolean keepLevel(Direction move) {// returns true if passed level,
												// false otherwise
		boolean done = false;

		// move hero
		if (!map.heroMovePossible(hero, move))// prevent guard from moving
			return false; // if hero moves to wall/closed door

		hero.move(map, move);

		return true;
	}

	// -------------------------PRINT--------------------------------------------

	@Override
	public String toString() {
		String res = "\n";

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

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public Guard getGuard() {
		return guard;
	}

	public void setGuard(Personality type) {
		this.guard.setPersonality(type);
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Lever getLever() {
		return lever;
	}

	public void setLever(Lever lever) {
		this.lever = lever;
	}

	public ArrayList<Door> getDoors() {
		return doors;
	}

	public void setDoors(ArrayList<Door> doors) {
		this.doors = doors;
	}

	public ArrayList<Ogre> getOgres() {
		return ogres;
	}

	public void setOgres(int ogreCount) {
		int x = 0;
		while (x != ogreCount) {
			this.ogres.add(new Ogre());
			x++;
		}
	}

}
