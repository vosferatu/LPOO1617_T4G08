package dkeep.logic;

import java.util.ArrayList;

public class KeepLevel extends Level {

	private ArrayList<Ogre> ogres = new ArrayList<Ogre>();
	
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

	@Override
	public boolean updateGame(Direction move) {// returns true if passed level,
		boolean done = false;						// false otherwise

		// move hero
		if (!map.heroMovePossible(hero, move))// prevent guard from moving
			return false; // if hero moves to wall/closed door

		hero.move(map, move);

		//move ogres and respective clubs
		for (Ogre ogre : ogres) {
			ogre.nextMove();
		}
		
		
		
		return done;
	}

	@Override
	public boolean isHeroDead() {
		return this.hero.isDead();
	}

	public ArrayList<Ogre> getOgres() {
		return ogres;
	}

	public void setOgres(ArrayList<Ogre> ogres) {
		this.ogres = ogres;
	}

	@Override
	public String printMap() {
		String res = "\n";

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
			res += "\n";

		}
		return res;
	}

}
