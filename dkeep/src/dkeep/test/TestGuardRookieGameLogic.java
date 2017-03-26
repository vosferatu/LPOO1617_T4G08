package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.CellPosition;
import dkeep.logic.Direction;
import dkeep.logic.Game;
import dkeep.logic.Personality;
import dkeep.logic.State;


public class TestGuardRookieGameLogic {
	
	@Test
	public void testMoveHeroIntoFreeCell() {
		Game game = new Game(Personality.ROOKIE);
		game.setOgreCount(1);
		
		assertEquals(1, game.getOgreCount());
		
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.updateGame(Direction.RIGHT);
		assertEquals(new CellPosition(2,1), game.getHeroPosition());
	}

	@Test
	public void testMoveHeroIntoWall() {
		Game game = new Game(Personality.ROOKIE);
		game.setOgreCount(1);
		
		assertEquals(1, game.getOgreCount());
		
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.updateGame(Direction.UP);
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
	}
	
	@Test
	public void testHeroIsCapturedByGuard() {
		Game game = new Game(Personality.ROOKIE);
		game.setOgreCount(1);
		
		assertEquals(1, game.getOgreCount());
		
		assertFalse(game.isGameOver());
		game.updateGame(Direction.RIGHT); game.updateGame(Direction.RIGHT);
		game.updateGame(Direction.DOWN); game.updateGame(Direction.DOWN);
		game.updateGame(Direction.DOWN); game.updateGame(Direction.DOWN);
		game.updateGame(Direction.DOWN); game.updateGame(Direction.DOWN);
		game.updateGame(Direction.UP);
		
		assertTrue(game.isGameOver());
		assertEquals(State.DEFEAT, game.getState());
	}	
	
	@Test
	public void testMoveHeroIntoClosedDoor() {
		Game game = new Game(Personality.ROOKIE);
		game.setOgreCount(1);
		
		assertEquals(1, game.getOgreCount());
		
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.updateGame(Direction.RIGHT);
		assertEquals(new CellPosition(2,1), game.getHeroPosition());
		game.updateGame(Direction.RIGHT);
		assertEquals(new CellPosition(3,1), game.getHeroPosition());
		game.updateGame(Direction.RIGHT);
		assertEquals(new CellPosition(3,1), game.getHeroPosition());
	}
	
	@Test
	public void testMoveHeroIntoNextLevel() {
		Game game = new Game(Personality.ROOKIE);
		game.setOgreCount(1);
		
		assertEquals(1, game.getOgreCount());
		
		
		game.updateGame(Direction.RIGHT); game.updateGame(Direction.RIGHT);
		game.updateGame(Direction.DOWN); game.updateGame(Direction.DOWN);
		game.updateGame(Direction.DOWN); game.updateGame(Direction.DOWN);
		game.updateGame(Direction.DOWN); game.updateGame(Direction.DOWN);
		game.updateGame(Direction.DOWN); game.updateGame(Direction.UP);
		game.updateGame(Direction.UP); game.updateGame(Direction.RIGHT);
		game.updateGame(Direction.RIGHT); game.updateGame(Direction.RIGHT);
		game.updateGame(Direction.RIGHT); game.updateGame(Direction.RIGHT);
		game.updateGame(Direction.DOWN); game.updateGame(Direction.DOWN);
		game.updateGame(Direction.LEFT); 
		
		
		String res = game.toString() + "";
		boolean found = false;
	    for (int i = 0; i < res.length(); i++) {
	        if (res.charAt(i) == 'k') { //checks if key isn't visible and hero has it
	            found = true;
	        }
	    }

	    assertFalse(found); //didn't find 'k' because hero is on top of it
		
		game.updateGame(Direction.RIGHT); 
		game.updateGame(Direction.UP); game.updateGame(Direction.UP); 
		game.updateGame(Direction.LEFT); game.updateGame(Direction.LEFT); 
		game.updateGame(Direction.LEFT); game.updateGame(Direction.LEFT); 
		game.updateGame(Direction.LEFT); game.updateGame(Direction.LEFT); 
		game.updateGame(Direction.LEFT); game.updateGame(Direction.LEFT);
		
		assertEquals(new CellPosition(1,7), game.getHeroPosition()); //keep level position
		assertFalse(game.isGameOver());
		assertEquals(State.LEVEL2, game.getState());
		game.updateGame(Direction.RIGHT); game.updateGame(Direction.RIGHT); 
		assertFalse(game.isGameOver());
		
	}
	
	@Test
	public void testMoveHeroIntoOpenDoor() {
		Game game = new Game(Personality.ROOKIE);
		game.setOgreCount(1);
		
		assertEquals(1, game.getOgreCount());
		
		game.updateGame(Direction.RIGHT); game.updateGame(Direction.RIGHT);
		game.updateGame(Direction.DOWN); game.updateGame(Direction.DOWN);
		game.updateGame(Direction.DOWN); game.updateGame(Direction.DOWN);
		game.updateGame(Direction.DOWN); game.updateGame(Direction.DOWN);
		game.updateGame(Direction.DOWN); game.updateGame(Direction.UP);
		game.updateGame(Direction.UP); game.updateGame(Direction.RIGHT);
		game.updateGame(Direction.RIGHT); game.updateGame(Direction.RIGHT);
		game.updateGame(Direction.RIGHT); game.updateGame(Direction.RIGHT);
		game.updateGame(Direction.DOWN); game.updateGame(Direction.DOWN);
		game.updateGame(Direction.LEFT); game.updateGame(Direction.RIGHT); 
		game.updateGame(Direction.UP); game.updateGame(Direction.UP); 
		game.updateGame(Direction.LEFT); game.updateGame(Direction.LEFT); 
		game.updateGame(Direction.LEFT); game.updateGame(Direction.LEFT); 
		game.updateGame(Direction.LEFT); game.updateGame(Direction.DOWN); 
		game.updateGame(Direction.DOWN); game.updateGame(Direction.RIGHT);
		
		assertEquals(new CellPosition(4,8), game.getHeroPosition()); //open door position
		game.updateGame(Direction.RIGHT);
		assertEquals(new CellPosition(5,8), game.getHeroPosition()); //open cell after door
		assertFalse(game.isGameOver());
		assertEquals(State.LEVEL1, game.getState());
	}
	
}