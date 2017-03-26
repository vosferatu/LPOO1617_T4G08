package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.CellPosition;
import dkeep.logic.Direction;
import dkeep.logic.DungeonLevel;
import dkeep.logic.Game;
import dkeep.logic.Personality;


public class TestGuardDrunkenGameLogic {
	
	@Test
	public void testMoveHeroIntoFreeCell() {
		Game game = new Game(Personality.DRUNKEN);
		game.setOgreCount(1);
		
		assertEquals(1, game.getOgreCount());
		
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.updateGame(Direction.RIGHT);
		assertEquals(new CellPosition(2,1), game.getHeroPosition());
	}

	@Test
	public void testMoveHeroIntoWall() {
		Game game = new Game(Personality.DRUNKEN);
		game.setOgreCount(1);
		
		assertEquals(1, game.getOgreCount());
		
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.updateGame(Direction.UP);
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
	}
	
	@Test(timeout = 100000)
	public void testHeroIsCapturedByGuard() {
		DungeonLevel level = new DungeonLevel(Personality.DRUNKEN);
		
		level.updateGame(Direction.RIGHT); level.updateGame(Direction.RIGHT);
		level.updateGame(Direction.DOWN); level.updateGame(Direction.DOWN);
		level.updateGame(Direction.DOWN); level.updateGame(Direction.DOWN);
		level.updateGame(Direction.DOWN);
		int x = 0;
		while(!level.isHeroDead()){
			if(x == 0){
				level.updateGame(Direction.UP);
				x++;
			}
			else {
				level.updateGame(Direction.DOWN);
				x--;
			}
			if(level.getHero().isAdjacent(level.getGuard()) && (!level.getGuard().isAsleep())){
				assertTrue(level.isHeroDead());
				break;
			} 
			
		}
		
		assertTrue(level.isHeroDead());
	}	
	
	@Test
	public void testMoveHeroIntoClosedDoor() {
		Game game = new Game(Personality.DRUNKEN);
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
	
	@Test(timeout=100000)
	public void testGuardGoesToSleep() {
		DungeonLevel level = new DungeonLevel(Personality.DRUNKEN);
		
		level.updateGame(Direction.RIGHT); level.updateGame(Direction.RIGHT);
		level.updateGame(Direction.DOWN); level.updateGame(Direction.DOWN);
		int x = 0;
		while(!level.getGuard().isAsleep()){
			
			if(x == 0){
				level.updateGame(Direction.UP);
				x++;
			}
			else {
				level.updateGame(Direction.DOWN);
				x--;
			}
			
		}
		
		if(level.getGuard().isAsleep()){
			char[][] res = level.printMap();
			boolean found = false;
		    for (int i = 0; i < res.length; i++) {
		    	for (int j = 0; j < res[0].length; j++) {
			        if (res[i][j] == 'g') { //checks ogre is stunned
			            found = true;
			        }
				}
		    }
		    assertTrue(found);
		}
		
		assertFalse(level.isHeroDead());
	}	
}