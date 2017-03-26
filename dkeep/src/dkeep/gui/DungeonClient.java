package dkeep.gui;

import java.io.IOException;

public class DungeonClient {
	
	public static void main(String args[]) throws IOException {
		DungeonKeep dkeep = new DungeonKeep();
		dkeep.run();
	}
	
	public void start() throws IOException {//to run on console
		DungeonKeep dkeep = new DungeonKeep();
		dkeep.run();
	}
	
}