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
	
	@Test(timeout = 10000)
	public void testHeroIsCapturedByGuard() {
		DungeonLevel level = new DungeonLevel(Personality.DRUNKEN);
		
		level.updateGame(Direction.RIGHT); level.updateGame(Direction.RIGHT);
		level.updateGame(Direction.DOWN); level.updateGame(Direction.DOWN);
		level.updateGame(Direction.DOWN); level.updateGame(Direction.DOWN);
		level.updateGame(Direction.DOWN);
		int x = 0;
		while(!level.isHeroDead()){
			if(level.getGuard().isAsleep()){
				String res = level.printMap() + "";
				boolean found = false;
			    for (int i = 0; i < res.length(); i++) {
			        if (res.charAt(i) == 'g') { //checks guard sleeping
			            found = true;
			        }
			    }
			    
			    assertTrue(found);
			}
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
	
}