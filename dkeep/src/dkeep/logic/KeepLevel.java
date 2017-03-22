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
		if (!map.heroMovePossible(hero, move))// prevent guard from moving
			return false; // if hero moves to wall/closed door

		hero.move(map, move);

		//move ogres and respective clubs
		for (Ogre ogre : ogres) {
			ogre.move(this.getMap());
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
			if(i != (map.getMap().length-1))
				res += "\n";

		}
		return res;
	}

}
