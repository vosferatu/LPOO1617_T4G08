package dkeep.logic;

import java.util.ArrayList;

/**
 * @author João Mendes
 * 
 * class represents the common members of a game level
 */
public abstract class Level implements GameLogic {
	
	/**
	 * represents the level's map
	 */
	protected GameMap map = null;
	/**
	 * represents the level's hero
	 */
	protected Hero hero = null;
	/**
	 * represent's the level's lever/key
	 */
	protected Lever lever = null;
	/**
	 * represent's the level's exit doors
	 */
	protected ArrayList<Door> doors = new ArrayList<Door>();
	
	/**
	 * gets the current map
	 * @return map
	 */
	public GameMap getMap() {
		return map;
	}

	/**
	 * sets the current map
	 * @param map new map
	 */
	public void setMap(GameMap map) {
		this.map = map;
	}

	/**
	 * gets the hero
	 * @return hero
	 */
	public Hero getHero() {
		return hero;
	}

	/**
	 * sets the current hero
	 * @param hero new hero
	 */
	public void setHero(Hero hero) {
		this.hero = hero;
	}

	/**
	 * gets the current lever
	 * @return lever
	 */
	public Lever getLever() {
		return lever;
	}

	/**
	 * sets the lever
	 * @param lever new lever
	 */
	public void setLever(Lever lever) {
		this.lever = lever;
	}

	/**
	 * gets the exit doors
	 * @return exit doors
	 */
	public ArrayList<Door> getDoors() {
		return doors;
	}

	/**
	 * sets the exit doors
	 * @param doors new doors
	 */
	public void setDoors(ArrayList<Door> doors) {
		this.doors = doors;
	}
	
	@Override
	public CellPosition getHeroPosition() {
		return hero.getPosition();
	}
	
}
