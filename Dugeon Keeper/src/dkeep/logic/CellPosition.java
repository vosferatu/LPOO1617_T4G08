package dkeep.logic;

public class CellPosition {
	private int x;
	private int y;

	public CellPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public CellPosition(){
		this.x = 1;//or 0
		this.y = 1;//or 0
	}
	
	public CellPosition(CellPosition pos) {
		x = pos.getX();
		y = pos.getY();
	}

	public int getX() {
		return x;		
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof CellPosition))
			return false;

		CellPosition check = (CellPosition) obj;
		
		return (this.x == check.x && this.y == check.y);
	}
	
	public boolean isAt(int x, int y){
		return (this.x == x) && (this.y == y);
	}
	
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
	
}