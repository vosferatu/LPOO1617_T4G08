package dkeep.logic;

public class GameMap {
	public char [][] map;
	private int width;
	private int height;

	public GameMap(int level){
		if(level == 1){
			map = new char[][]	
				   {{'X','X','X','X','X','X','X','X','X','X'},
		  			{'X',' ',' ',' ','I',' ','X',' ','G','X'},
		  			{'X','X','X',' ','X','X','X',' ',' ','X'},
		  			{'X',' ','I',' ','I',' ','X',' ',' ','X'},
		  			{'X','X','X',' ','X','X','X',' ',' ','X'},
		  			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
		  			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
		  			{'X','X','X',' ','X','X','X','X',' ','X'},
		  			{'X','X','X',' ','X','X','X','k',' ','X'},
		  			{'X','X','X','X','X','X','X','X','X','X'}};
		}
		
		if(level == 2){
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public boolean heroMovePossible(Hero hero, Move move) {
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
}
