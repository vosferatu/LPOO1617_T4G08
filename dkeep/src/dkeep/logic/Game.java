package dkeep.logic;

import java.util.ArrayList;

public class Game {

	public GameMap map;
	private Hero hero;
	private Guard guard;
	private Lever lever;
	private ArrayList<Door> doors;
	//private Ogre ogre;
	
	public Hero getHero() {
		return hero;
	}
	
	public void setHero(Hero hero) {
		this.hero = hero;
	}
	
	public Guard getGuard() {
		return guard;
	}
	
	public void setGuard(Guard guard) {
		this.guard = guard;
	}

	public boolean updateGame(Move move) {
		
		return false;
	}
	
}
