package dkeep.logic;

import java.util.ArrayList;

public class Game {

	public GameMap map;
	private Hero hero;
	private Guard guard;
	private Lever lever;
	private ArrayList<Door> doors;
	private ArrayList<Ogre> ogres;
	private State state = State.LEVEL1;
	
	public Game(){
		map = new GameMap(this.state);
		hero = new Hero();
		guard = new Guard();
		lever = new Lever(7,8);
		doors.add(new Door(new CellPosition(0,5),true));
		doors.add(new Door(new CellPosition(0,6),true));
		doors.add(new Door(new CellPosition(2,3),false));
		doors.add(new Door(new CellPosition(2,8),false));
		doors.add(new Door(new CellPosition(4,3),false));
		doors.add(new Door(new CellPosition(4,8),false));
		doors.add(new Door(new CellPosition(4,1),false));
	}
	


	public State updateGame(Move move) {
		
		return State.DEFEAT;
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

	public void setOgres(ArrayList<Ogre> ogres) {
		this.ogres = ogres;
	}
	
}
