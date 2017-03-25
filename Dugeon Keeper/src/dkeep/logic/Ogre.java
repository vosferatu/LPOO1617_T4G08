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
		this.club = new Club(this);
	}

	/**
	 * creates an ogre at a standard position
	 */
	public Ogre() {
		this.id = 'O';
		this.position = new CellPosition(4,4);
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
		
		if(club == null) //for unit testing
			return;
		
		CellPosition update = new CellPosition(); //ogre position
		update.setPosition(position.getAdjacent()); //adjacent position to move into
		boolean done = false;
		
		while(!done){
			if(map.getMap()[update.getY()][update.getX()] != 'X' && 
					map.getMap()[update.getY()][update.getX()] != 'I' ){
				done = true;
			}
			else {
				update.setPosition(position.getAdjacent());
			}
		}
		
		this.setPosition(update);
		
		if(club == null)
			return;
		
		CellPosition nova = new CellPosition(); //club position
		nova.setPosition(position.getAdjacent()); //adjacent position to move into
		boolean finish = false;
		
		while(!finish){
			if(map.getMap()[nova.getY()][nova.getX()] != 'X' && 
					map.getMap()[nova.getY()][nova.getX()] != 'I' ){
				finish = true;
			}
			else {
				nova.setPosition(position.getAdjacent());
			}
		}
		
		this.club.setPosition(nova);
		
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

	/**
	 * checks if an ogre is stunned
	 * @return true if ogre is stunned, false otherwise
	 */
	public boolean isStunned() {
		if(getStun() > 0)
			return true;
		return false;
	}
	
}
