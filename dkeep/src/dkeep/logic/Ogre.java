package dkeep.logic;

/**
 * @author João Mendes
 * a class to represent an ogre
 */
public class Ogre extends Character {

	/**
	 * if ogre is at key position
	 */
	private boolean atKey = false;
	/**
	 * represents the number of moves the ogre is stun
	 */
	private int stun = 0;
	/**
	 * represents the ogre's club
	 */
	private Club club = null;
	
	/**
	 * creates a new ogre
	 * @param x coordinate x
	 * @param y coordinate y
	 */
	public Ogre(int x, int y) {
		this.id = 'O';
		this.position = new CellPosition(x,y);
		this.type = Type.OGRE;
		this.club = new Club(this);
	}

	/**
	 * creates an ogre at a standard position
	 */
	public Ogre() {
		this.id = 'O';
		this.position = new CellPosition(4,4);
		this.type = Type.OGRE;
		this.club = new Club(this);
	}

	/**
	 * moves the ogre in the specified map
	 * @param map
	 */
	public void move(GameMap map) {
		if(stun > 0){
			stun--;
			return;
		}
		
	}

	/**
	 * checks if ogre is at the key's position
	 * @return true if ogre is at the key's position, false otherwise
	 */
	public boolean isAtKey() {
		return atKey;
	}

	/**
	 * sets if an ogre is at the key position
	 * @param key the game's key
	 */
	public void setAtKey(Lever key) {
		atKey = this.position.equals(key.getPosition());
		
		/*
		 * if(atKey == true){
		 * 		this.setId('$')
		 * }
		 */
	}

	/**
	 * gets the number of remaining move stuns of the ogre
	 * @return stuns
	 */
	public int getStun() {
		return stun;
	}

	/**
	 * sets the number of move stuns
	 * @param stun new stun number
	 */
	public void setStun(int stun) {
		this.stun = stun;
	}

	/**
	 * represents the ogre in a string
	 */
	@Override
	public String toString() {
		String res = "";
		if(getStun() > 0){
			res+= "8";
		}
		else res += id;
		
		return res;
	}

	/**
	 * gets the ogre's club
	 * @return club
	 */
	public Club getClub() {
		return club;
	}

	/**
	 * sets the ogre's club
	 * @param club new club
	 */
	public void setClub(Club club) {
		this.club = club;
	}
	
}
