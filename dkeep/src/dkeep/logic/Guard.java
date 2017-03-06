package dkeep.logic;

public class Guard extends Character{
	
	private boolean isAsleep = true;

	public Guard(int x, int y) {
		this.id = 'G';
		this.position = new CellPosition(x,y);
		this.type = Type.GUARD;
	}
	
	public void move(GameMap map, Move move){
		switch (move) {
			case RIGHT:
				position.setX(position.getX() + 1);
				break;
		
			case DOWN:
				position.setY(position.getY() + 1);
				break;
		
			case LEFT:
				position.setX(position.getX() - 1);
				break;
		
			case UP:
				position.setY(position.getY() - 1);
				break;
		
			default:
				break;
		}
	}

	public boolean isAsleep() {
		return isAsleep;
	}

	public void wakeUp() {
		this.isAsleep = false;
	}
	
	public void sleep() {
		this.isAsleep = true;
	}


}
