package dkeep.logic;

public abstract class Character {
	
	protected CellPosition position = new CellPosition();
	protected char id;
	protected Type type;
	
	public enum Type {
		HERO, OGRE, GUARD
	}
	
	public boolean isAt(CellPosition esta) {
		return (this.equals(esta));
	}
	
	public boolean isAt(int x, int y) {
		return (x == getPosition().getX()) && (y == getPosition().getY());
	}

	public CellPosition getPosition() {
		return position;
	}

	public void setPosition(CellPosition position) {
		this.position = position;
	}
	
	public char getId(){
		return id;
	}

	public void setId(char id){
		this.id = id;
	}
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}
