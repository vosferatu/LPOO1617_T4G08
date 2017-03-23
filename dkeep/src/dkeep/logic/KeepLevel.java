package dkeep.logic;

import java.util.ArrayList;

/**
 * @author João Mendes
 * class to represent the ogre level
 */
public class KeepLevel extends Level {

	/**
	 * stores the ogres
	 */
	private ArrayList<Ogre> ogres = new ArrayList<Ogre>();
	
	/**
	 * creates a new keep level
	 * @param ogreNum number of ogres to be added
	 */
	public KeepLevel(int ogreNum) {
		map = new GameMap(this);
		hero = new Hero(1, 7);
		hero.setArmed(true);
		hero.setDead(false);
		hero.setHasKey(false);
		lever = new Lever(7, 1);
		doors.clear();
		doors.add(new Door(new CellPosition(0, 1)));
		for(int i = 0; i < ogreNum && i < 5; i++){
			ogres.add(new Ogre());
		}
	}

	/**
	 * @see dkeep.logic.GameLogic#updateGame(dkeep.logic.Direction)
	 */
	@Override
	public boolean updateGame(Direction move) {// returns true if passed level,
		boolean done = false;						// false otherwise
		
		// move hero
		if (!map.heroMovePossible(hero, move)){// prevent ogre from moving
			//check if hero can open door to cause extra movement
			for (Door door : doors) {
				if((!door.isOpen()) && hero.movesToDoor(move, door) && hero.hasKey()){
					door.openDoor();
					map.openDoor(door);
					return false;
				}
			}
			return false; // if hero moves to wall/closed door
		}
			

		hero.move(map, move);

		//move ogres and respective clubs
		for (Ogre ogre : ogres) {
			ogre.move(this.getMap());
		}
		
		//check if hero is at key position if he hsn't it already
		if (!hero.hasKey()) {
			if (hero.atLever(lever)) {
				lever = null;
				hero.setHasKey(true);
			}
		}
		
		//first stun the guards
		for (Ogre ogre : ogres) {
			if (hero.isAdjacent(ogre) && (!ogre.isStunned())){
				ogre.setStun(2);
			}
		}
		
		//check if hero is going to die be ogre or club
		for (Ogre ogre : ogres) {
			if (hero.isAdjacent(ogre) && (!ogre.isStunned())) {
				hero.setDead(true);
				done = false;
			}
			
			if(hero.getPosition().isAdjacent(ogre.getClub().getPosition())){
				hero.setDead(true);
				done = false;
			}
		}		
		
		// checks if hero is going through door
		for (Door door : doors) {
			if (hero.getPosition().equals(door.getPosition()) && door.isOpen()) {
				done = true;
				break;
			}
		}
		
		return done;
	}

	/**
	 * @see dkeep.logic.GameLogic#isHeroDead()
	 */
	@Override
	public boolean isHeroDead() {
		return this.hero.isDead();
	}

	/**
	 * gets the ogres of the level
	 * @return ogres
	 */
	public ArrayList<Ogre> getOgres() {
		return ogres;
	}

	/**
	 * sets the ogres
	 * @param ogres new ogres
	 */
	public void setOgres(ArrayList<Ogre> ogres) {
		this.ogres = ogres;
	}

	/**
	 * @see dkeep.logic.GameLogic#printMap()
	 */
	@Override
	public String printMap() {
		String res = "";

		for (int i = 0; i < map.getMap().length; i++) {
			for (int j = 0; j < map.getMap()[i].length; j++) {
				boolean noChar = true;

				if (hero.isAt(j, i)) {
					res += hero;
					noChar = false;
				} else {
					for (Ogre x : ogres) {
						if (x.getClub().getPosition().isAt(j, i)) {
							if (lever != null && lever.getPosition().isAt(j, i)) {
								res += "$";
								noChar = false;
								break;
							} else {
								res += x.getClub();
								noChar = false;
								break;
							}
						} else if (x.isAt(j, i)) {
							if (lever != null && lever.getPosition().isAt(j, i)) {
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
					if (lever != null && lever.getPosition().isAt(j, i)) {
						res += lever;
					} else {
						for (Door door : doors) {
							if(door.getPosition().isAt(j,i)){
								res += door;
								noChar = false;
							}
						}
						if(noChar)
							res += map.getMap()[i][j];
					}
				}
				res += " ";
			}
			if(i != (map.getMap().length-1))
				res += "\n";

		}
		return res;
	}

}
