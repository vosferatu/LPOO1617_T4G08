package dkeep.logic;

/**
 * @author João Mendes
 *	class to represent a hero
 */
public class Hero extends Character {
	
	/**
	 * if hero as key
	 */
	private boolean	hasKey = false;
	/**
	 * if hero is armed
	 */
	private boolean armed = false;
	/**
	 * if hero is dead
	 */
	private boolean dead = false;
	
	/**
	 * creates a new hero
	 * @param x position value
	 * @param y position value
	 */
	public Hero(int x, int y){
		this.id = 'H';
		this.position = new CellPosition(x,y);
	}
	
	/**
	 * creates a new hero at standard position
	 */
	public Hero(){
		this.id = 'H';
		this.position = new CellPosition(1,1);
	}
	
	/**
	 * issues a hero move in a certain direction
	 * @param map game map
	 * @param move direction
	 */
	public void move(GameMap map, Direction move){
		
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

	/**
	 * checks if hero as the key
	 * @return true if hero has the key, false otherwise
	 */
	public boolean hasKey() {
		return hasKey;
	}

	/**
	 * sets if hero has key
	 * @param hasKey if hero has key true, false otherwise
	 */
	public void setHasKey(boolean hasKey) {
		this.hasKey = hasKey;
	}

	/**
	 * checks if hero is armed
	 * @return true if hero is armed, false otherwise
	 */
	public boolean isArmed() {
		return armed;
	}

	/**
	 * sets hero armed
	 * @param armed true if armed, false otherwise
	 */
	public void setArmed(boolean armed) {
		this.armed = armed;
	}

	/**
	 * checks if hero is dead
	 * @return true if hero is dead, false otherwise
	 */
	public boolean isDead() {
		return dead;
	}

	/**
	 * sets if hero is dead
	 * @param dead true if hero is dead, false otherwise
	 */
	public void setDead(boolean dead) {
		this.dead = dead;
	}

	/**
	 * checks if hero is at lever position
	 * @param lever lever
	 * @return true if hero is at level, false if isn't
	 */
	public boolean atLever(Lever lever) {
		if(this.position.equals(lever.getPosition())){
			this.hasKey = true;
			return true;
		}
		return false;
	}

	/**
	 * represents the hero in a string
	 */
	@Override
	public String toString() {
		if(hasKey() && isArmed())
			return "K";
		
		if(!isArmed())
			return "" + id;
		else return "A";
	}

	/**
	 * checks if a hero is trying to open the door with a key
	 * @param move direction of the movement
	 * @param door to be open
	 * @return true if hero is trying to open door, false otherwise
	 */
	public boolean movesToDoor(Direction move, Door door) {
		CellPosition hero = new CellPosition(this.position);
		hero.addMove(move);

		if(hero.equals(door.getPosition()))
			return true;
		else return false;
	}
	
}
