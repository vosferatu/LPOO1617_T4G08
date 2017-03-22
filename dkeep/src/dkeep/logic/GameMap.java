package dkeep.logic;

/**
 * @author João Mendes
 *	class to represent the game map
 */
public class GameMap {
	/**
	 * represents the game map in characters
	 */
	public char [][] map = null;
	/**
	 * represents the game's width 
	 */
	private int width;
	/**
	 * represents the game's height
	 */
	private int height;

	/**
	 * creates a new game map according to the level
	 * @param level of the map
	 */
	public GameMap(Level level){
		
		if(level instanceof DungeonLevel){
			map = new char[][]	
				   {{'X','X','X','X','X','X','X','X','X','X'},
		  			{'X',' ',' ',' ','I',' ','X',' ',' ','X'},
		  			{'X','X','X',' ','X','X','X',' ',' ','X'},
		  			{'X',' ','I',' ','I',' ','X',' ',' ','X'},
		  			{'X','X','X',' ','X','X','X',' ',' ','X'},
		  			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
		  			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
		  			{'X','X','X',' ','X','X','X','X',' ','X'},
		  			{'X',' ','I',' ','I',' ','X',' ',' ','X'},
		  			{'X','X','X','X','X','X','X','X','X','X'}};
		}
		
		if(level instanceof KeepLevel){
			map = new char[][]	
				   {{'X','X','X','X','X','X','X','X','X'},
		  			{'I',' ',' ',' ',' ',' ',' ',' ','X'},
		  			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
		  			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
		  			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
		  			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
		  			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
		  			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
		  			{'X','X','X','X','X','X','X','X','X'}}; 
		}		
		
		this.setHeight(map.length);
		this.setWidth(map[0].length);
	}
	

	/**
	 * creates a new game map
	 * @param map2 char matrix
	 */
	public GameMap(char[][] map2) {
		this.map = map2;
		this.height = map2.length;
		this.width = map2[0].length;
	}


	/**
	 * converts the map to a string
	 */
	@Override
	public String toString() {
		String mapa = "\n";

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++){
				mapa += map[i][j] + " ";
			}
			mapa += "\n";
		}

		return mapa;
	}
	
	/**
	 * checks if the her0's move is possible
	 * @param hero hero
	 * @param move direction
	 * @return true if possible to move, false otherwise
	 */
	public boolean heroMovePossible(Hero hero, Direction move) {
		int x = hero.getPosition().getX();
		int y = hero.getPosition().getY();

		switch (move) {
		
			case RIGHT:
				if (map[y][x + 1] != 'I')
				return (map[y][x + 1] != 'X');
			break;
			
			case DOWN:
				if (map[y + 1][x] != 'I')
					return (map[y + 1][x] != 'X');
			break;

			case LEFT:
				if (map[y][x - 1] != 'I')
					return (map[y][x - 1] != 'X');
			break;

		case UP:
			if (map[y - 1][x] != 'I')
				return (map[y - 1][x] != 'X');
			break;
		default:
			break;
		}
		
		if (hero.hasKey())
			return true;
		else
			return false;
	}
	
	/**
	 * gets the map's width
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * sets the map's width
	 * @param width new width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * gets the map's height
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * sets the map's height
	 * @param height new height
	 */
	public void setHeight(int height) {
		this.height = height;
	}


	/**
	 * gets the current map
	 * @return map
	 */
	public char[][] getMap() {
		return map;
	}


	/**
	 * sets the current map
	 * @param map new map
	 */
	public void setMap(char[][] map) {
		this.map = map;
	}
	
	/**
	 * opens map's doors. changes 'I's to 'S's
	 */
	public void openDoors(){
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++){
				if(map[i][j] == 'I')
					map[i][j] = 'S';
			}
		}
	}
	
}
