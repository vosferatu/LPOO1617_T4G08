package dkeep.logic;

public class Door {

	private CellPosition position;
	private char id;
	private boolean isOpen;
	private boolean isExit;
	
	public Door(CellPosition position, boolean isExit) {
		this.position = position;
		this.id = 'I';
		this.isOpen = false;
		this.isExit = isExit;
	}
	
	public void openDoor(){
		this.id = 'S';
		this.isOpen = true;
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

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public boolean isExit() {
		return isExit;
	}

	public void setExit(boolean isExit) {
		this.isExit = isExit;
	}

}
