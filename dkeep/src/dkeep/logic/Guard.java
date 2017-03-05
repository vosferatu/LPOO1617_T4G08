package dkeep.logic;

public class Guard extends Character{

	public Guard(int x, int y) {
		this.id = 'G';
		this.position = new CellPosition(x,y);
		this.type = Type.GUARD;
	}
	
	public void move(GameMap map, Move move){

	}


}
