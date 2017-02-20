package dkeep.logic;

public class Door extends StaticCharacter {
	private boolean openStatus = false;
	
	public Door(int x, int y) {
		this.setx(x);
		this.sety(y);
		this.setid('I');
	}
	
	public boolean getStatus(){
		return openStatus;
	}
	
	public void openDoor(Door m) {
		m.openStatus = true;
		m.setid('S');
		
	}
}
