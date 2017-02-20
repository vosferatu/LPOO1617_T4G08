package dkeep.logic;

public class Map {
	public Character map[][] = new Character[10][10];
	
	public void startmap(){
		map[0] = new char[] {'X','X','X','X','X','X','X','X','X','X'};
		map[1] = new char[] {'X','H','\0','\0','I','\0','X','\0','G','X'};
		map[2] = new char[] {'X','X','X','\0','X','X','X','\0','\0','X'};
		map[3] = new char[] {'X','\0','I','\0','I','\0','X','\0','\0','X'};
		map[4] = new char[] {'X','X','X','\0','X','X','X','\0','\0','X'};
		map[5] = new char[] {'I','\0','\0','\0','\0','\0','\0','\0','\0','X'};
		map[6] = new char[] {'I','\0','\0','\0','\0','\0','\0','\0','\0','X'};
		map[7] = new char[] {'X','X','X','\0','X','X','X','X','\0','X'};
		map[8] = new char[] {'X','X','X','\0','X','X','X','k','\0','X'};
		map[9] = new char[] {'X','X','X','X','X','X','X','X','X','X'};
	}
	
	public void printmap(Map m){
	   for(int i = 0; i < 10; i++)
	   {
		  System.out.print(map[i][0]);
	      for(int j = 1; j < 10; j++)
	      {
	         System.out.printf(" " + map[i][j]);
	      }
	      System.out.println();
	   }
	}
	
}

