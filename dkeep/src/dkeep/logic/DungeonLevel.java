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
	 * creates a new dungeon level using a new map
	 * @param gameMap new map
	 */
	public DungeonLevel(GameMap gameMap) {
		this.setMap(gameMap);
		//no need to check if more than 1 hero/lever/guard. user doesn't use this
		for (int i = 0; i < gameMap.getMap().length; i++) {
			for (int j = 0; j < gameMap.getMap()[i].length; j++){
				if(gameMap.getMap()[i][j] == 'G'){
					setGuard(new Guard());
					guard.setPosition(new CellPosition(j,i));
					guard.setPersonality(Personality.SIMPLE);
				}
				if(gameMap.getMap()[i][j] == 'H'){
					setHero(new Hero(j,i));
					hero.setArmed(false);
					hero.setDead(false);
					hero.setHasKey(false);
				}
				if(gameMap.getMap()[i][j] == 'k'){
					setLever(new Lever(j,i));
				}
				if(gameMap.getMap()[i][j] == 'I'){
					doors.add(new Door(new CellPosition(j, i)));
				}
			}
		}
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
			if (hero.atLever(lever)) {
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
	public char[][] printMap() {
		char[][] res = new char[map.getMap().length][map.getMap()[0].length];
		
		for (int i = 0; i < map.getMap().length; i++) {
			for(int j = 0; j < map.getMap()[i].length; j++){
				boolean noChar = true;
				
				if(hero.isAt(j, i) || (hero.isAt(j, i) && lever.getPosition().isAt(j, i))){
					res[i][j] = hero.getId(); noChar = false;
				}
				
				if(lever.getPosition().isAt(j, i) && !hero.isAt(j, i)){
					res[i][j] = lever.getId(); noChar = false;
				}
				
				if(guard.isAt(j, i)){
					res[i][j] = guard.getId(); noChar = false;
				}
				
				if(noChar)
					res[i][j] = map.getMap()[i][j];
				
			}
		}
		return res;
	}

}
