package dkeep.logic;

public class DungeonLevel extends Level {
	
	private Guard guard = null;

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

	@Override
	public boolean updateGame(Direction move) {// returns true if passed
		boolean done = false;				// level, false otherwise

		// move hero
		if (!map.heroMovePossible(hero, move))// prevent guard from moving
			return false; // if hero moves to wall/closed door

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

	@Override
	public boolean isHeroDead() {
		return hero.isDead();
	}

	public Guard getGuard() {
		return guard;
	}

	public void setGuard(Guard guard) {
		this.guard = guard;
	}

	@Override
	public String printMap() {
		String res = "\n";
		
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
			res += "\n";
		}
		return res;
	}

}
