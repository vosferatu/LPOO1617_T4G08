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
		// TODO Auto-generated method stub
		return null;
	}

}
