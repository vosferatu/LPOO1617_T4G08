package dkeep.logic;

import dkeep.logic.Character.Type;

public class Ogre extends Character {

	private boolean atKey = false;
	
	public Ogre(int x, int y) {
		this.id = 'O';
		this.position = new CellPosition(x,y);
		this.type = Type.OGRE;
	}

	@Override
	public void move(GameMap map, Move move) {
		// TODO Auto-generated method stub
		
	}

	public boolean isAtKey() {
		return atKey;
	}

	public void setAtKey(Lever key) {
		atKey = this.position.equals(key.getPosition());
		
		/*
		 * if(atKey == true){
		 * 		this.setId('$')
		 * }
		 */
	}

}
