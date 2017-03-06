package dkeep.logic;

import java.util.ArrayList;

public class Game {

	public GameMap map;
	private Hero hero;
	private Guard guard;
	private Lever lever;
	private ArrayList<Door> doors;
	//private Ogre ogre;
	private State state = State.LEVEL1;
	
	public enum State {
		LEVEL1, TRANSITION, LEVEL2, DEFEAT, WON
	}
	
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

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
}
