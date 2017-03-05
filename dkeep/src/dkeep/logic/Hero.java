package dkeep.logic;

public class Hero extends Character {
	
	private boolean	hasKey = false;
	private boolean armed = false;
	private boolean dead = false;
	
	public Hero(int x, int y){
		this.id = 'H';
		this.position = new CellPosition(x,y);
		this.type = Type.HERO;
	}
	
	public Hero(){
		this.id = 'H';
		this.position = new CellPosition(1,1);
		this.type = Type.HERO;
	}
	
	public void move(GameMap map, Move move){
		
		if (map.heroMovePossible(this, move)) {
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
	}

	public boolean hasKey() {
		return hasKey;
	}

	public void setHasKey(boolean hasKey) {
		this.hasKey = hasKey;
	}

	public boolean isArmed() {
		return armed;
	}

	public void setArmed(boolean armed) {
		this.armed = armed;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}
	
}
