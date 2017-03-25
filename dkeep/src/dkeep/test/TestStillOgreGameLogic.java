package dkeep.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dkeep.logic.CellPosition;
import dkeep.logic.Direction;
import dkeep.logic.Door;
import dkeep.logic.GameMap;
import dkeep.logic.KeepLevel;
import dkeep.logic.Ogre;

public class TestStillOgreGameLogic {

		char [][] map = new char [][] 
				{{'X','X','X','X','X'},
				{'X','H',' ','O','X'},
				{'I',' ',' ',' ','X'},
				{'X','k',' ',' ','X'},
				{'X','X','X','X','X'}
	};
	
	@Test
	public void testMoveHeroIntoFreeCell() {
		GameMap gameMap = new GameMap(map);
		KeepLevel game = new KeepLevel(gameMap);
		for (Ogre ogre : game.getOgres()) {
			ogre.setClub(null);
		}
		
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.updateGame(Direction.DOWN);
		assertEquals(new CellPosition(1,2), game.getHeroPosition());
	}
	
	@Test
	public void testMoveHeroIntoWall() {
		GameMap gameMap = new GameMap(map);
		KeepLevel game = new KeepLevel(gameMap);
		for (Ogre ogre : game.getOgres()) {
			ogre.setClub(null);
		}
		
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.updateGame(Direction.UP);
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
	}
	
	@Test
	public void testHeroIsCapturedByOgre() {
		GameMap gameMap = new GameMap(map);
		KeepLevel game = new KeepLevel(gameMap);
		game.getHero().setArmed(false);//takes weapon from hero
		
		assertFalse(game.getHero().isArmed());
		
		for (Ogre ogre : game.getOgres()) {
			ogre.setClub(null);
		}
		
		assertFalse(game.isHeroDead());
		game.updateGame(Direction.RIGHT);
		assertTrue(game.isHeroDead());
	}	
	
	@Test
	public void testMoveHeroIntoClosedDoor() {
		GameMap gameMap = new GameMap(map);
		KeepLevel game = new KeepLevel(gameMap);
		for (Ogre ogre : game.getOgres()) {
			ogre.setClub(null);
		}
		
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.updateGame(Direction.DOWN);
		assertEquals(new CellPosition(1,2), game.getHeroPosition());
		game.updateGame(Direction.LEFT);
		assertEquals(new CellPosition(1,2), game.getHeroPosition());
		assertFalse(game.isHeroDead());
	}
	
	@Test
	public void testMoveHeroIntoKey() {
		GameMap gameMap = new GameMap(map);
		KeepLevel game = new KeepLevel(gameMap);
		for (Ogre ogre : game.getOgres()) {
			ogre.setClub(null);
		}
		
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.updateGame(Direction.DOWN);
		assertEquals(new CellPosition(1,2), game.getHeroPosition());
		game.updateGame(Direction.DOWN);
		assertEquals(new CellPosition(1,3), game.getHeroPosition());
		assertEquals('I', map[2][0]);//lever doesn't open doors
		assertFalse(game.isHeroDead());
		assertTrue(game.getHero().hasKey());
		
		String res = game.printMap();
		boolean found = false;
	    for (int i = 0; i < res.length(); i++) {
	        if (res.charAt(i) == 'K') { //checks is hero has key
	            found = true;
	        }
	    }
	    
	    assertTrue(found);
		
	}
	
	@Test
	public void testMoveHeroIntoNextLevel() {
		GameMap gameMap = new GameMap(map);
		KeepLevel game = new KeepLevel(gameMap);
		for (Ogre ogre : game.getOgres()) {
			ogre.setClub(null);
		}
		
		assertEquals(new CellPosition(1,1), game.getHeroPosition());
		game.updateGame(Direction.DOWN);
		assertEquals(new CellPosition(1,2), game.getHeroPosition());
		game.updateGame(Direction.DOWN);
		assertEquals(new CellPosition(1,3), game.getHeroPosition());
		assertEquals('I', map[2][0]);
		game.updateGame(Direction.LEFT);
		assertEquals(new CellPosition(1,3), game.getHeroPosition()); //move against wall
		assertFalse(game.isHeroDead());
		game.updateGame(Direction.UP);
		assertTrue(game.getHero().isAt(1, 2));
		game.updateGame(Direction.LEFT);
		assertEquals(new CellPosition(1,2), game.getHeroPosition()); //spent extra movement opening door
		assertEquals('S', map[2][0]); //opened door
		
		for (Door door : game.getDoors()) {
			assertTrue(door.isOpen());
		}
		
		game.updateGame(Direction.LEFT);
		assertTrue(game.getHero().isAt(new CellPosition(0,2))); //passed level
		assertFalse(game.isHeroDead());
	}

	@Test
	public void testHeroStunningOgre() {
		GameMap gameMap = new GameMap(map);
		KeepLevel game = new KeepLevel(gameMap);
		game.getHero().setArmed(true);//takes weapon from hero
		
		assertTrue(game.getHero().isArmed());
		
		String res1 = game.printMap() + "";
		boolean found1 = false;
	    for (int i = 0; i < res1.length(); i++) {
	        if (res1.charAt(i) == 'A') { //checks if hero is armed
	            found1 = true;
	        }
	    }
	    
	    assertTrue(found1);
		
		for (Ogre ogre : game.getOgres()) {
			ogre.setClub(null);
		}
		
		assertFalse(game.isHeroDead());
		game.updateGame(Direction.RIGHT);
		for (Ogre ogre : game.getOgres()) {
			if(ogre.isAdjacent(game.getHero()))
				assertTrue(ogre.isStunned());
		}
		assertFalse(game.isHeroDead());
		
		String res = game.printMap();
		boolean found = false;
	    for (int i = 0; i < res.length(); i++) {
	        if (res.charAt(i) == '8') { //checks is ogre is stunned
	            found = true;
	        }
	    }
	    
	    assertTrue(found);
		
	}	
}
