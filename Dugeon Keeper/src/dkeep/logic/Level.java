package dkeep.logic;

import java.util.ArrayList;

public abstract class Level implements GameLogic {
	
	protected GameMap map = null;
	protected Hero hero = null;
	protected Lever lever = null;
	protected ArrayList<Door> doors = new ArrayList<Door>();
	
	public GameMap getMap() {
		return map;
	}

	public void setMap(GameMap map) {
		this.map = map;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
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
	
}
