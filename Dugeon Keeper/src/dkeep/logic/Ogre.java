package dkeep.logic;

public class Ogre extends Character {

	private boolean atKey = false;
	private int stun = 0;
	private Club club = null;
	
	public Ogre(int x, int y) {
		this.id = 'O';
		this.position = new CellPosition(x,y);
		this.type = Type.OGRE;
		this.club = new Club(this);
	}

	public Ogre() {
		this.id = 'O';
		this.position = new CellPosition(4,4);
		this.type = Type.OGRE;
		this.club = new Club(this);
	}

	public void move(GameMap map) {
		if(stun > 0){
			stun--;
			return;
		}
		
	}

	public boolean isAtKey() {
		return atKey;
	}

	public void setAtKey(Lever key) {
		atKey = this.position.equals(key.getPosition());
		
		/*
		 * if(atKey == true){
		 * 		this.setId('$')
		 * }
		 */
	}

	public int getStun() {
		return stun;
	}

	public void setStun(int stun) {
		this.stun = stun;
	}

	@Override
	public String toString() {
		String res = "";
		if(getStun() > 0){
			res+= "8";
		}
		else res += id;
		
		return res;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}
	
}
