package dkeep.logic;

class Character {
	private int x;
	private int y;
	private char id;

	public Character() {
		super();
	}
	
	public int getx() {
		return x;		
	}
	
	public int gety(){
		return y;
	}
	
	public void setx(int x)
	{
		this.x = x;
	}
	
	public void sety(int y)
	{
		this.y = y;
	}
	
	public void setid(char h){
		this.id = h;
	}
}