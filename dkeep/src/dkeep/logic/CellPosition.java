package dkeep.logic;

import java.util.Random;


/**
 * @author João Mendes
 * class represents a position in the map
 *
 */
public class CellPosition {
	
	/**
	 * x position from top left corner
	 */
	private int x;
	
	/**
	 * y position from top left corner
	 */
	private int y;

	/**
	 * creates a new position in specified coordinates
	 * @param x horizontal position
	 * @param y	horizontal position
	 */
	public CellPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * creates a new position at x = 1, y = 1
	 */
	public CellPosition(){
		this.x = 1;//or 0
		this.y = 1;//or 0
	}
	
	/**
	 * creates a "copy" from another cell position
	 * @param pos
	 */
	public CellPosition(CellPosition pos) {
		x = pos.getX();
		y = pos.getY();
	}

	/**
	 * gets x value
	 * @return x value
	 */
	public int getX() {
		return x;		
	}
	
	/**
	 * gets y value
	 * @return y value
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * set x value
	 * @param x new value
	 */
	public void setX(int x){
		this.x = x;
	}
	
	/**
	 * set y value
	 * @param y new value
	 */
	public void setY(int y){
		this.y = y;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 * compares two CellPositions
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof CellPosition))
			return false;

		CellPosition check = (CellPosition) obj;
		
		return (this.x == check.x && this.y == check.y);
	}
	
	/**
	 * checks if a cell position is at given coordinate
	 * @param x value
	 * @param y	value
	 * @return	true if a cell position is at the given coordinate, false otherwise
	 */
	public boolean isAt(int x, int y){
		return (this.x == x) && (this.y == y);
	}
	
	/**
	 * checks if this position is adjacent to the given position
	 * @param obj cell position
	 * @return true if is adjacent, false if isn't
	 */
	public boolean isAdjacent(Object obj){
		if (!(obj instanceof CellPosition))
			return false;

		CellPosition check = (CellPosition) obj;
		
		if ((this.x == check.x-1 && this.y == check.y) ||
				(this.x == check.x+1 && this.y == check.y) ||
				(this.x == check.x && this.y == check.y-1) ||
				(this.x == check.x && this.y == check.y+1) )
			return true;
		else return false;
	}
	
	/**
	 * gets a random (4) adjacent position to the current cell
	 * @param cell
	 * @return
	 */
	public CellPosition getAdjacent(){
		Random rand = new Random();
		int randomN = rand.nextInt(4);
		
		switch (randomN){
		case 0:
			return new CellPosition(this.x-1, this.y);
		case 1:
			return new CellPosition(this.x+1, this.y);
		case 2:
			return new CellPosition(this.x, this.y-1);
		case 3:
			return new CellPosition(this.x, this.y+1);
		default:
			return new CellPosition(this.x, this.y+1);
		}
		
	}
	
}