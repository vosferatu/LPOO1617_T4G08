package dkeep.logic;

/**
 * @author João Mendes
 * class represents a character form the game
 */
public abstract class Character {
	
	/**
	 * position of the character
	 */
	protected CellPosition position = new CellPosition();
	/**
	 * representation
	 */
	protected char id;
	
	/**
	 * checks if a character is adjacent to other character
	 * @param other character to compare to
	 * @return returns true if a character is adjacent to other character, false otherwise
	 */
	public boolean isAdjacent(Character other){
		return this.position.isAdjacent(other.position);
	}
	
	/**
	 * checks if a character is at a certain position
	 * @param esta a cell position
	 * @return true if a character is at a certain position, false otherwise
	 */
	public boolean isAt(CellPosition esta) {
		return (this.position.equals(esta));
	}
	
	/**
	 * checks if a character is at a certain coordinate
	 * @param x x value
	 * @param y y value
	 * @return true if a character is at a coordinate, false otherwise
	 */
	public boolean isAt(int x, int y) {
		return (x == getPosition().getX()) && (y == getPosition().getY());
	}

	/**
	 * gets current position
	 * @return position
	 */
	public CellPosition getPosition() {
		return position;
	}

	/**
	 * sets current position
	 * @param position new position
	 */
	public void setPosition(CellPosition position) {
		this.position = position;
	}
	
	/**
	 * gets current id
	 * @return id
	 */
	public char getId(){
		return id;
	}

	/**
	 * sets the current id
	 * @param id new id
	 */
	public void setId(char id){
		this.id = id;
	}
	
}
