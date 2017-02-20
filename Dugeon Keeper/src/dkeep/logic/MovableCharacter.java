package dkeep.logic;

public class MovableCharacter extends Character {

	public MovableCharacter() {
		super();
	}

	public enum Direction {
		LEFT, RIGHT, UP, DOWN
	}

	public void moveLeft() {
		this.setx(this.getx() - 1);
	}

	public void moveRight() {
		this.setx(this.getx() + 1);
	}

	public void moveUp() {
		this.sety(this.gety() - 1);
	}

	public void moveDown() {
		this.sety(this.gety() + 1);
	}

	public boolean checkMovement(MovableCharacter c, Direction d) {
		switch (d){
		case LEFT:
		if(map[c.getx()-1][c.gety()] instanceof Wall || map[c.getx()-1][c.gety()] instanceof Door){
			return false;
			break;
		} else {
			return true;
			}
			
		case RIGHT:
			if(map[c.getx()+1][c.gety()] instanceof Wall || map[c.getx()+1][c.gety()] instanceof Door){
				return false;
			} else {
				return true;
			}
				
		case UP:
			if(map[c.getx()][c.gety()-1] instanceof Wall || map[c.getx()][c.gety()-1] instanceof Door){
				return false;
			} else {
				return true;
			}
				
		case DOWN:
			if(map[c.getx()][c.gety()+1] instanceof Wall || map[c.getx()][c.gety()+1] instanceof Door){
				return false;
			}else {
				return true;
			}
				}
	}
	
	
	
}
