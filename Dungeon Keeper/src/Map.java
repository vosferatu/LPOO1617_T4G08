
public class Map {
	public static char map[][] = new char[10][10];
	
	public static void main(String[] args) {
		startmap();
		printmap();
	}

	public static void startmap(){
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
	
	public static void printmap(){
	   for(int i = 0; i < 10; i++)
	   {
	      for(int j = 0; j < 10; j++)
	      {
	         System.out.print(map[i][j]);
	      }
	      System.out.println();
	   }
	}
	
	public static void game(){
		
	}
}
