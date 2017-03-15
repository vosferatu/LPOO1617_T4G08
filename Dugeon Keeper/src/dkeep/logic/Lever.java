package dkeep.logic;

public class Lever {
	
	private CellPosition position;
	private char id;
	private boolean wasHit = false;
	
	public Lever(int x, int y) {
		this.setPosition(new CellPosition(x,y));
		this.setId('k');
	}

	public CellPosition getPosition() {
		return position;
	}

	public void setPosition(CellPosition position) {
		this.position = position;
	}
	
	public void setPosition(int x, int y) {
		this.position = new CellPosition(x,y);
	}

	public char getId() {
		return id;
	}

	public void setId(char id) {
		this.id = id;
	}

	public boolean wasHit() {
		return wasHit;
	}

	public void setHit(boolean hit) {
		this.wasHit = hit;
	}

	@Override
	public String toString() {
		return "" + id;
	}
	
	

}
