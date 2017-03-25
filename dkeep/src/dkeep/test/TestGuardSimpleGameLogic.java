package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.CellPosition;
import dkeep.logic.Direction;
import dkeep.logic.Game;
import dkeep.logic.GameMap;
import dkeep.logic.State;


public class TestGuardSimpleGameLogic {

	char [][] map = new char [][] 
					{{'X','X','X','X','X'},
					{'X','H',' ','G','X'},
					{'I',' ',' ',' ','X'},
					{'I','k',' ',' ','X'},
					{'X','X','X','X','X'}
	};
	
	@Test
	public void testMoveHeroIntoFreeCell() {
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap);
		game.setOgreCount(1);
		
		assertEquals(1, game.getOgreCount());
		
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.updateGame(Direction.DOWN);
		assertEquals(new CellPosition(1,2), game.getHeroPosition());
	}

	@Test
	public void testMoveHeroIntoWall() {
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap);
		game.setOgreCount(1);
		
		assertEquals(1, game.getOgreCount());
		
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.updateGame(Direction.UP);
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
	}
	
	@Test
	public void testHeroIsCapturedByGuard() {
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap);
		game.setOgreCount(1);
		
		assertEquals(1, game.getOgreCount());
		
		assertFalse(game.isGameOver());
		game.updateGame(Direction.RIGHT);
		assertTrue(game.isGameOver());
		assertEquals(State.DEFEAT, game.getState());
	}	
	
	@Test
	public void testHeroMoveDiagonal() {
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap);
		game.setOgreCount(1);
		
		assertEquals(1, game.getOgreCount());
		
		assertFalse(game.isGameOver());
		game.updateGame(Direction.DOWN);
		assertFalse(game.isGameOver());
		game.updateGame(Direction.RIGHT);
		assertFalse(game.isGameOver());		
		assertEquals(State.LEVEL1, game.getState());
		assertEquals(new CellPosition(2,2), game.getHeroPosition());
	}	
	
	@Test
	public void testMoveHeroIntoClosedDoor() {
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap);
		game.setOgreCount(1);
		
		assertEquals(1, game.getOgreCount());
		
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.updateGame(Direction.DOWN);
		assertEquals(new CellPosition(1,2), game.getHeroPosition());
		game.updateGame(Direction.LEFT);
		assertEquals(new CellPosition(1,2), game.getHeroPosition());
	}
	
	@Test
	public void testMoveHeroIntoLever() {
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap);
		game.setOgreCount(1);
		
		assertEquals(1, game.getOgreCount());
		
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.updateGame(Direction.DOWN);
		assertEquals(new CellPosition(1,2), game.getHeroPosition());
		game.updateGame(Direction.DOWN);
		assertEquals(new CellPosition(1,3), game.getHeroPosition());
		assertEquals('S', map[2][0]);
		assertEquals('S', map[3][0]);
	}
	
	@Test
	public void testMoveHeroIntoNextLevel() {
		GameMap gameMap = new GameMap(map);
		Game game = new Game(gameMap);
		game.setOgreCount(1);
		
		assertEquals(1, game.getOgreCount());
		
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.updateGame(Direction.DOWN);
		assertEquals(new CellPosition(1,2), game.getHeroPosition());
		game.updateGame(Direction.DOWN);
		assertEquals(new CellPosition(1,3), game.getHeroPosition());
		assertEquals('S', map[2][0]);
		assertEquals('S', map[3][0]);
		game.updateGame(Direction.LEFT);
		assertEquals(new CellPosition(1,7), game.getHeroPosition()); //keep level position
		assertFalse(game.isGameOver());
		assertEquals(State.LEVEL2, game.getState());
	}
	
}
