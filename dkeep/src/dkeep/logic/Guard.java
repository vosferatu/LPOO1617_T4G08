package dkeep.logic;

import java.util.ArrayList;

public class Guard extends Character{
	
	private boolean isAsleep = true;
	private ArrayList<CellPosition> moves;
	private int moveNum = 0;
	
	public Guard() {
		this.id = 'G';
		this.position = new CellPosition(8,1);
		this.type = Type.GUARD;
		moves.add(new CellPosition(7,1)); moves.add(new CellPosition(7,2));
		moves.add(new CellPosition(7,3)); moves.add(new CellPosition(7,4));
		moves.add(new CellPosition(7,5)); moves.add(new CellPosition(6,5));
		moves.add(new CellPosition(5,5)); moves.add(new CellPosition(4,5));
		moves.add(new CellPosition(3,5)); moves.add(new CellPosition(2,5));
		moves.add(new CellPosition(1,5)); moves.add(new CellPosition(1,6));
		moves.add(new CellPosition(2,6)); moves.add(new CellPosition(3,6));
		moves.add(new CellPosition(4,6)); moves.add(new CellPosition(5,6));
		moves.add(new CellPosition(6,6)); moves.add(new CellPosition(7,6));
		moves.add(new CellPosition(8,6)); moves.add(new CellPosition(8,5));
		moves.add(new CellPosition(8,4)); moves.add(new CellPosition(8,3));
		moves.add(new CellPosition(8,2)); moves.add(new CellPosition(8,1));
	}
	
	@Override
	public void move(GameMap map, Move move){
		switch (move) {
			case RIGHT:
				position.setX(position.getX() + 1);
				break;
		
			case DOWN:
				position.setY(position.getY() + 1);
				break;
		
			case LEFT:
				position.setX(position.getX() - 1);
				break;
		
			case UP:
				position.setY(position.getY() - 1);
				break;
		
			default:
				break;
		}
	}

	public boolean isAsleep() {
		return isAsleep;
	}

	public void wakeUp() {
		this.isAsleep = false;
	}
	
	public void sleep() {
		this.isAsleep = true;
	}

	public ArrayList<CellPosition> getMoves() {
		return moves;
	}

	public void setMoves(ArrayList<CellPosition> moves) {
		this.moves = moves;
	}

	public int getMoveNum() {
		return moveNum;
	}

	public void setMoveNum(int moveNum) {
		this.moveNum = moveNum;
	}


}
