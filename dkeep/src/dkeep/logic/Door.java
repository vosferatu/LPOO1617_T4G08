package dkeep.logic;

/**
 * @author João Mendes
 *	class to represent a door
 */
public class Door {

	/**
	 * door's position
	 */
	private CellPosition position;
	/**
	 * door's representation
	 */
	private char id;
	/**
	 * if door is open
	 */
	private boolean isOpen;
	
	/**
	 * creates a new Door
	 * @param position of the new door
	 */
	public Door(CellPosition position) {
		this.position = position;
		this.id = 'I';
		this.isOpen = false;
	}
	
	/**
	 * opens the door
	 */
	public void openDoor(){
		this.id = 'S';
		this.isOpen = true;
	}

	/**
	 * gets the door's position
	 * @return position
	 */
	public CellPosition getPosition() {
		return position;
	}
	
	/**
	 * sets the current door position using coordinates
	 * @param x x value
	 * @param y y value
	 */
	public void setPosition(int x, int y) {
		this.position = new CellPosition(x,y);
	}

	/**
	 * gets the current id
	 * @return id
	 */
	public char getId() {
		if(isOpen())
			return 'S';
		else return id;
	}

	/**
	 * checks if a door is open
	 * @return true if door is open, false otherwise
	 */
	public boolean isOpen() {
		return isOpen;
	}
	
}
