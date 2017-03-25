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
	 * if lever was hit by oger or club
	 */
	private boolean wasHit = false;
	
	/**
	 * creates a new lever
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public Lever(int x, int y) {
		this.setPosition(new CellPosition(x,y));
		this.setId('k');
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
	 * sets the lever position
	 * @param x coordinate x
	 * @param y coordinate y
	 */
	public void setPosition(int x, int y) {
		this.position = new CellPosition(x,y);
	}

	/**
	 * gets the lever id
	 * @return id
	 */
	public char getId() {
		return id;
	}

	/** sets the lever id
	 * @param id new id
	 */
	public void setId(char id) {
		this.id = id;
	}

	/**
	 * checks if the lever was hit
	 * @return true if lever was hit, false otherwise
	 */
	public boolean wasHit() {
		return wasHit;
	}

	/**
	 * sets if the lever was it
	 * @param hit true if lever was hit, false otherwise
	 */
	public void setHit(boolean hit) {
		this.wasHit = hit;
	}

	/**
	 * prints the lever
	 */
	@Override
	public String toString() {
		return "" + id;
	}	

}
