package dkeep.logic;

import java.util.Random;

public class Guard extends Character{

	private boolean asleep;
	private CellPosition[] moves;
	private int moveNum = 0;
	private Personality personality = Personality.SIMPLE;
	private Direction direction = Direction.RIGHT;//direction in vector of positions
	private int lastCheck = 0;

	public Guard() {
		this.id = 'G';
		this.position = new CellPosition(8,1);
		this.type = Type.GUARD;

		moves = new CellPosition[] {new CellPosition(7,1), new CellPosition(7,2),
				new CellPosition(7,3), (new CellPosition(7,4)), new CellPosition(7,5), 
				new CellPosition(6,5), new CellPosition(5,5), new CellPosition(4,5),
				new CellPosition(3,5), new CellPosition(2,5), new CellPosition(1,5),
				new CellPosition(1,6), new CellPosition(2,6), new CellPosition(3,6),
				new CellPosition(4,6), new CellPosition(5,6), new CellPosition(6,6),
				new CellPosition(7,6), new CellPosition(8,6), new CellPosition(8,5),
				new CellPosition(8,4), new CellPosition(8,3), new CellPosition(8,2),
				new CellPosition(8,1)};
	}

	public CellPosition[] getMoves() {
		return moves;
	}

	public void setMoves(CellPosition[] moves) {
		this.moves = moves;
	}

	public int getMoveNum() {
		return moveNum;
	}

	public void setMoveNum(int moveNum) {
		this.moveNum = moveNum;
	}

	public void nextMove(){
		Random rand = new Random();
		
		if(this.personality == Personality.SIMPLE)
			return;


		if(this.personality == Personality.ROOKIE){
			if(moveNum == 24)
				moveNum = 0;
			this.setPosition(this.moves[moveNum]);
			moveNum++;
		}


		if(this.personality == Personality.DRUNKEN){
			int randomN = rand.nextInt(3);

			if(isAsleep() == true){
				
				if(randomN == 0){
					this.wakeUp(); //wakes up 1/3 of the time
					
					randomN = rand.nextInt(2);
					if(randomN == 0){//changes direction 1/2 of the time
						this.changeDirection();
					}
				}

				return;	//doesn't move
			}
			else{ //is awake

				randomN = rand.nextInt(5);//goes asleep 1/5 of the time
				if(randomN == 2){
					this.setAsleep(true);
					return; //doesn't move
				}
			}


			//moves
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


		if(this.personality == Personality.SUSPICIOUS){
			int randomN = rand.nextInt(2);
			if(lastCheck > 5 && randomN == 0 ){
				lastCheck = 0;
				this.changeDirection();

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

	}

	public Personality getPersonality() {
		return personality;
	}

	public void setPersonality(Personality personality) {
		this.personality = personality;
	}

	@Override
	public String toString() {
		String res = "" + id;
		if(isAsleep()){
			return res.toLowerCase();
		}

		return res;
	}

	public boolean getAsleep() {
		return asleep;
	}

	public boolean isAsleep(){
		return this.asleep;
	}

	public void setAsleep(boolean asleep) {
		this.asleep = asleep;
	}

	public void wakeUp(){
		this.asleep = true;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public void changeDirection(){
		if(direction == Direction.RIGHT)
			setDirection(Direction.LEFT);
		else setDirection(Direction.RIGHT);	
	}

	public int getLastCheck() {
		return lastCheck;
	}

	public void setLastCheck(int lastCheck) {
		this.lastCheck = lastCheck;
	}

}
