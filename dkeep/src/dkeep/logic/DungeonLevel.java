package dkeep.logic;

/**
 * @author João Mendes
 *	represents the guard level
 */
public class DungeonLevel extends Level {
	
	/**
	 * represents the guard
	 */
	private Guard guard = null;

	/**
	 * creates a new dungeon level 
	 * @param personality guard's personality
	 */
	public DungeonLevel(Personality personality) {
		map = new GameMap(this);
		hero = new Hero();
		setGuard(new Guard());
		guard.setPersonality(personality);
		lever = new Lever(7, 8);
		doors.clear();
		doors.add(new Door(new CellPosition(0, 5)));
		doors.add(new Door(new CellPosition(0, 6)));
	}

	/**
	 * @see dkeep.logic.GameLogic#updateGame(dkeep.logic.Direction)
	 */
	@Override
	public boolean updateGame(Direction move) {// returns true if passed level,
		boolean done = false;				// false otherwise

		// move hero
		if (!map.heroMovePossible(hero, move))// prevent guard from moving
			return false; // if hero moves to wall/closed door

		if(heroOverlapingGuard(move))//prevent hero from moving into guard
			return false;
		
		hero.move(map, move);

		// move guard
		guard.nextMove();

		// checks if hero is at lever position if he hasn't the key already
		if (!hero.hasKey()) {
			if (hero.turnLever(lever)) {
				map.openDoors();
				for (Door door : doors) {
					door.openDoor();
				}
			}
		}

		// checks if hero is going to die
		if (hero.isAdjacent(guard) && (!guard.isAsleep())) {
			hero.setDead(true);
			return false;
		}

		// checks if hero is at a stair
		for (Door door : doors) {
			if (hero.getPosition().equals(door.getPosition()) && door.isOpen()) {
				done = true;
				break;
			}
		}

		return done;

	}

	/**
	 * @see dkeep.logic.GameLogic#isHeroDead()
	 */
	@Override
	public boolean isHeroDead() {
		return hero.isDead();
	}

	/**
	 * gets the guard
	 * @return guard
	 */
	public Guard getGuard() {
		return guard;
	}

	/**
	 * sets the current guard
	 * @param guard new guard
	 */
	public void setGuard(Guard guard) {
		this.guard = guard;
	}

	/**
	 * checks if hero is moving into guard
	 * @param move direction of the move
	 * @return true if hero is moving into guard, false otherwise
	 */
	public boolean heroOverlapingGuard(Direction move){
		boolean res = false;
		
		CellPosition nova = new CellPosition(hero.getPosition());
		nova.addMove(move);
		
		if(nova.equals(guard.getPosition()))
			res = true;
		else res = false;
		
		return res;
	}
	
	
	/**
	 * @see dkeep.logic.GameLogic#printMap()
	 */
	@Override
	public String printMap() {
		String res = "";
		
		for (int i = 0; i < map.getMap().length; i++) {
			for(int j = 0; j < map.getMap()[i].length; j++){
				boolean noChar = true;
				
				if(hero.isAt(j, i) || (hero.isAt(j, i) && lever.getPosition().isAt(j, i))){
					res += hero; noChar = false;
				}
				
				if(lever.getPosition().isAt(j, i) && !hero.isAt(j, i)){
					res += lever; noChar = false;
				}
				
				if(guard.isAt(j, i)){
					res += guard; noChar = false;
				}
				
				if(noChar)
					res += map.getMap()[i][j];
				
				res += " ";
			}
			if(i != (map.getMap().length-1))
				res += "\n";
		}
		return res;
	}

}
