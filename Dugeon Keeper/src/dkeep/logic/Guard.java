package dkeep.logic;

public class Guard extends Character{
	
	private int asleep = 0;
	private CellPosition[] moves;
	private int moveNum = 0;
	private Personality personality = Personality.SIMPLE;
	private Direction direction = Direction.RIGHT;//direction in vector of positions
	
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
		if(this.personality == Personality.SIMPLE)
			return;
		
		if(this.personality == Personality.ROOKIE){
			if(moveNum == 24)
				moveNum = 0;
			this.setPosition(this.moves[moveNum]);
			moveNum++;
		}
		
		if(this.personality == Personality.DRUNKEN){
			if(asleep > 0){
				asleep--;
				return;
			}
			else{
				
			}
			

			
			this.setPosition(this.moves[moveNum]);
			if(direction == Direction.LEFT){
				
			} else {
				moveNum++;
			}
		
			if(moveNum > 23 || moveNum < 0)
				moveNum = 0;
		
		
		}
		
		if(this.personality == Personality.SUSPICIOUS){
			if(moveNum == 24)
				moveNum = 0;
			this.setPosition(this.moves[moveNum]);
			moveNum++;
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

	public int getAsleep() {
		return asleep;
	}
	
	public boolean isAsleep(){
		return (this.asleep > 0);
	}

	public void setAsleep(int asleep) {
		this.asleep = asleep;
	}
	
	public void wakeUp(){
		this.asleep = 0;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
}
