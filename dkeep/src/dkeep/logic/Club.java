package dkeep.logic;

public class Club {

	private CellPosition position;
	private char id;
	
	public Club(Ogre ogre) {
		this.setPosition(new CellPosition(ogre.getPosition().getX()+1, ogre.getPosition().getY()));
		this.setId('*');
	}

	public CellPosition getPosition() {
		return position;
	}

	public void setPosition(CellPosition position) {
		this.position = position;
	}

	public char getId() {
		return id;
	}

	public void setId(char id) {
		this.id = id;
	}

}
