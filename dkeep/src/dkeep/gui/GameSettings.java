package dkeep.gui;

import dkeep.logic.GameMap;
import dkeep.logic.Personality;

public class GameSettings {

	private GameMap map;
	private Personality personality = Personality.SIMPLE;
	private int ogreNum = 1;
	
	public GameSettings() {
		this.map = null;
		this.personality = Personality.SIMPLE;
		this.ogreNum = 1;
	}

	public int getOgreNum() {
		return ogreNum;
	}

	public void setOgreNum(int ogreNum) {
		this.ogreNum = ogreNum;
	}

	public Personality getPersonality() {
		return personality;
	}

	public void setPersonality(Personality personality) {
		this.personality = personality;
	}

	public GameMap getMap() {
		return map;
	}

	public void setMap(GameMap map) {
		this.map = map;
	}

	public void setMap(char[][] map) {
		this.map = new GameMap(map);
	}
}
