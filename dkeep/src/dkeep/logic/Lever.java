package dkeep.logic;

/**
 * @author João Mendes
 * class to represent the game's lever/key
 */
public class Lever {
	
	/**
	 * lever's position
	 */
	private CellPosition position;
	/**
	 * lever's representation
	 */
	private char id;
	
	/**
	 * creates a new lever
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public Lever(int x, int y) {
		this.setPosition(new CellPosition(x,y));
		id = 'k';
	}

	/**
	 * gets the lever's position
	 * @return position
	 */
	public CellPosition getPosition() {
		return position;
	}

	/**
	 * sets the lever position
	 * @param position new position
	 */
	public void setPosition(CellPosition position) {
		this.position = position;
	}

	/**
	 * gets the lever id
	 * @return id
	 */
	public char getId() {
		return id;
	}
	
}
