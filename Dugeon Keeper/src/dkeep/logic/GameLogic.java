package dkeep.logic;

public interface GameLogic {

	boolean updateGame(Direction move);
	
	boolean isHeroDead();
	
	String printMap();
	
}
