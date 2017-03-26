package dkeep.logic;

/**
 * @author João Mendes
 *class represents the ogre club
 */
public class Club {

	/**
	 * club's position
	 */
	private CellPosition position;
	/**
	 * club's representation
	 */
	private char id;
	
	/**
	 * create a club for a specific ogre
	 * @param ogre ogre who will own the club
	 */
	public Club(Ogre ogre) {
		this.setPosition(new CellPosition(ogre.getPosition().getX()+1, ogre.getPosition().getY()));
		this.setId('*');
	}

	/**
	 * gets the current position
	 * @return position
	 */
	public CellPosition getPosition() {
		return position;
	}

	/**
	 * sets the current position
	 * @param position new position
	 */
	public void setPosition(CellPosition position) {
		this.position = position;
	}

	/**
	 * sets current id
	 * @param id new id
	 */
	public void setId(char id) {
		this.id = id;
	}

	/**
	 * gets current id
	 * @return id
	 */
	public char getId(){
		return id;
	}

}
