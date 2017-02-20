package dkeep.logic;

public class Game {
	
	private boolean gameStatus = true;
	
	public static void main(String[] args) {}

	public void startGame(){
	
	Character map1[][] = new Character[10][10];
	
	while (gameStatus) {
		Character h1 = new Hero(1,1);
		Character g1 = new Guard(8,1);
		Character d1 = new Door(4,1);
		Character d2 = new Door(4,3);
		Character d3 = new Door(2,3);
		Character d4 = new Door(0,5);
		Character d5 = new Door(0,6);
		Character d6 = new Door(2,8);
		Character d7 = new Door(4,8);
		Character k1 = new Key(7,8);
		
		
		printmap();
	}
	}
}
