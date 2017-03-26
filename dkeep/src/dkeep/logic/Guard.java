package dkeep.logic;

import java.util.Random;

/**
 * @author João Mendes
 * class to represent a guard
 */
public class Guard extends Character{

	/**
	 * true if guard is asleep
	 */
	private boolean asleep;
	/**
	 * guards stored positions to move
	 */
	private CellPosition[] moves;
	/**
	 * number of the move the guard is in
	 */
	private int moveNum = 0;
	/**
	 * personality of the guard
	 */
	private Personality personality = Personality.SIMPLE;
	/**
	 * direction of the moves (in array) the guard is going
	 */
	private Direction direction = Direction.RIGHT;//direction in array of positions
	/**
	 * how many moves ago guard did last check
	 */
	private int lastCheck = 0;

	/**
	 * creates a new guard
	 */
	public Guard() {
		this.id = 'G';
		this.position = new CellPosition(8,1);

		CellPosition [] one  = new CellPosition[] {new CellPosition(7,1), new CellPosition(7,2),
				new CellPosition(7,3), (new CellPosition(7,4)), new CellPosition(7,5), 
				new CellPosition(6,5), new CellPosition(5,5), new CellPosition(4,5),
				new CellPosition(3,5), new CellPosition(2,5), new CellPosition(1,5),
				new CellPosition(1,6), new CellPosition(2,6), new CellPosition(3,6),
				new CellPosition(4,6), new CellPosition(5,6), new CellPosition(6,6),
				new CellPosition(7,6), new CellPosition(8,6), new CellPosition(8,5),
				new CellPosition(8,4), new CellPosition(8,3), new CellPosition(8,2),
				new CellPosition(8,1)};
		this.setMoves(one);
	}

	/**
	 * gets the guards move positons
	 * @return array of positions
	 */
	public CellPosition[] getMoves() {
		return moves;
	}

	/**
	 * sets the guards move positions
	 * @param moves new array of moves
	 */
	public void setMoves(CellPosition[] moves) {
		this.moves = moves;
	}

	/**
	 * updates the guard's position, influenced by personality
	 */
	public void nextMove(){
		
		if(this.personality == Personality.SIMPLE)
			return;


		if(this.personality == Personality.ROOKIE){
			if(moveNum == 24)
				moveNum = 0;
			this.setPosition(this.moves[moveNum]);
			moveNum++;
		}


		if(this.personality == Personality.DRUNKEN){
			this.moveDrinking();
		} 


		if(this.personality == Personality.SUSPICIOUS){
			this.moveSuspiciously();
		}

	}

	/**
	 * updates guard's move with drunken personality
	 */
	public void moveDrinking(){
		Random rand = new Random(System.currentTimeMillis());
		int randomN = 0;
		
		if(isAsleep() == true){
			randomN = rand.nextInt(2);
			if(randomN == 0){
				this.wakeUp(); //wakes up 1/2 of the time
				
				randomN = rand.nextInt(2);
				if(randomN == 0){//changes direction 1/2 of the time
					this.changeDirection();
					if(direction == Direction.LEFT){
						moveNum--;
					} else {
						moveNum++;
					}
				}
			}

			return;	//doesn't move
		}
		else{ //is awake

			randomN = rand.nextInt(3);//goes asleep 1/4 of the time
			if(randomN == 2){
				this.setAsleep(true);
				return; //doesn't move
			}
		}

		if(moveNum < 0)
			moveNum = 23;

		if(moveNum > 23)
			moveNum = 0;
		
		//moves
		this.setPosition(this.moves[moveNum]);
		
		if(direction == Direction.LEFT){
			moveNum--;
		} else {
			moveNum++;
		}
	}
	
	/**
	 * updates guard's move with suspicious personality
	 */
	private void moveSuspiciously() {
		Random rand = new Random(System.currentTimeMillis());
		int randomN = 0;
		
		if(lastCheck > 5){
			randomN = rand.nextInt(3);
			if(randomN == 0){
				lastCheck = 0;
				this.changeDirection();					
			}

		} else {
			lastCheck++;
		}

		this.setPosition(this.moves[moveNum]);
		if(direction == Direction.LEFT){
			moveNum--;
		} else {
			moveNum++;
		}

		if(moveNum < 0)
			moveNum = 23;

		if(moveNum > 23)
			moveNum = 0;
		
	}

	/**
	 * gets the current personality
	 * @return personality
	 */
	public Personality getPersonality() {
		return personality;
	}

	/**
	 * sets the guard's personality
	 * @param personality new personality
	 */
	public void setPersonality(Personality personality) {
		this.personality = personality;
	}

	@Override
	public char getId(){
		if(isAsleep())
			return 'g';
		else return id;
	}
	/**
	 * checks if guard is asleep
	 * @return true if asleep, false otherwise
	 */
	public boolean isAsleep(){
		return this.asleep;
	}

	/**
	 * sets the guard asleep
	 * @param asleep new asleep
	 */
	public void setAsleep(boolean asleep) {
		this.asleep = asleep;
	}

	/**
	 * asleep turn false
	 */
	public void wakeUp(){
		this.asleep = false;
	}

	/**
	 * gets the current direction of the moves
	 * @return direction of moves
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * sets the direction of the moves
	 * @param direction of moves
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	/**
	 * changes the direction of the moves to opposite
	 */
	public void changeDirection(){
		if(direction == Direction.RIGHT)
			setDirection(Direction.LEFT);
		else setDirection(Direction.RIGHT);	
	}

	/**
	 * gets the number of moves ago last check was made
	 * @return last check moves
	 */
	public int getLastCheck() {
		return lastCheck;
	}

	/**
	 * sets the last moves check
	 * @param lastCheck new last moves check
	 */
	public void setLastCheck(int lastCheck) {
		this.lastCheck = lastCheck;
	}

}
