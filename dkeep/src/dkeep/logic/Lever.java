package dkeep.logic;

public class Lever {
	
	private CellPosition position;
	private char id;
	
	public Lever(int x, int y) {
		this.position = new CellPosition(x,y);
		this.id = 'k';
	}

}
