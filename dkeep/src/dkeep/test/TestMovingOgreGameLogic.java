package dkeep.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dkeep.logic.CellPosition;
import dkeep.logic.Direction;
import dkeep.logic.KeepLevel;
import dkeep.logic.Ogre;

public class TestMovingOgreGameLogic {
	
	@Test
	public void testMoveHeroIntoFreeCell() {
		KeepLevel game = new KeepLevel(1);
		for (Ogre ogre : game.getOgres()) {
			ogre.setClub(null);
		}
		
		assertEquals(new CellPosition(1,7), game.getHeroPosition());
		game.updateGame(Direction.UP);
		assertEquals(new CellPosition(1,6), game.getHeroPosition());
	}
	
	@Test
	public void testMoveHeroIntoWall() {
		KeepLevel game = new KeepLevel(1);
		for (Ogre ogre : game.getOgres()) {
			ogre.setClub(null);
		}
		
		assertEquals(new CellPosition(1,7), game.getHeroPosition());
		game.updateGame(Direction.LEFT);
		assertEquals(new CellPosition(1,7), game.getHeroPosition());
	}
	
	@Test (timeout = 10000)
	public void testHeroIsCapturedByOgre() {
		KeepLevel level = new KeepLevel(5);
		level.getHero().setArmed(false);//takes weapon from hero for easier testing
		
		assertFalse(level.getHero().isArmed());
		
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
			for (Ogre ogre : level.getOgres()) {
				if(level.getHero().isAdjacent(ogre) ||
						level.getHero().getPosition().isAdjacent(ogre.getClub().getPosition())){
					assertTrue(level.isHeroDead());
					break;
				}
			}

		}
		
		assertTrue(level.isHeroDead());
	}	
	

	@Test(timeout = 10000)
	public void testOgreHittingKey() {
		KeepLevel game = new KeepLevel(5);
		game.getHero().setArmed(false);//takes weapon from hero
		
		assertFalse(game.getHero().isArmed());
		
		assertFalse(game.isHeroDead());
		
		int x = game.getLever().getPosition().getX();
		int y = game.getLever().getPosition().getY();
		
		boolean cool = false;
		while(!cool){
			for (Ogre ogre : game.getOgres()) {
				if(ogre.isAt(x,y) ||
						ogre.getClub().getPosition().isAt(x, y)){
					cool = true;
					break;
				}
			}
			if(!cool){
				for (Ogre ogre : game.getOgres()) {
					ogre.move(game.getMap());
				}
			}
		}
		
		String res = game.printMap() + "";
		boolean found = false;
	    for (int i = 0; i < res.length(); i++) {
	        if (res.charAt(i) == '$') { //checks is ogre is stunned
	            found = true;
	        }
	    }
	    
	    assertTrue(found);
		
	}	
}
