package dkeep.cli;

import dkeep.logic.Game;
import dkeep.logic.Move;

import java.util.Scanner;

public class Runner {
	
	private static Game game = null;
	
	public static void main(String[] args) {
		
		mainMenu();

		Scanner scanner = new Scanner(System.in);
		int input = userInput(scanner, "Option?", 1, 2);
		
		boolean bad = true;
		while(bad){
			switch (input) {
				case 1:
					startDungeon(scanner); bad = false;
				break;
				case 2:
					System.out.println("\tLeaving Dungeon Keeper. Goodbye...");
					bad = false;
				break;
				default:
				break;
			}
		}
		
		scanner.close();
	}
	
	private static void startDungeon(Scanner scanner) {
		
		boolean end = false;
		
		//printDungeon();

		while (!end) {
			Move move = getHeroMove(scanner);
			
			end = game.updateGame(move);

			//printDungeon();
		}

		if (game.getHero().isDead()) {
			System.out.println("\t************");
			System.out.println("\t*GAME OVER!*");
			System.out.println("\t************");
		} else {
			System.out.println("\t**********");
			System.out.println("\t*YOU WON!*");
			System.out.println("\t**********");
		}
		System.out.println();
	}

	private static int userInput(Scanner scanner, String prompt,
			int min, int max) {
		int option;
	
		do {
			System.out.print(prompt);
			System.out.print("> ");
			
			option = scanner.nextInt();
		} while (option > max || option < min);

		return option;
	}
	
	/*private static void printDungeon() {
		System.out.println(game.getLabyrinth().drawToString(
				game.getLivingBeings(), game.getSword(), game.getEagle()));
	}*/

	private static Move getHeroMove(Scanner scanner) {
		String input;

		do {
			System.out.print("W/A/S/D to move: ");
			input = scanner.next();
		} while (!input.equals("w") && !input.equals("a") && !input.equals("s")
				&& !input.equals("d") && !input.equals("W") && !input.equals("A") 
				&& !input.equals("S") && !input.equals("D"));

		input.toUpperCase();
		Move move = null;
		
		switch (input) {
			case "D":
				move = Move.RIGHT;
			break;
			
			case "A":
				move = Move.LEFT;
			break;
			
			case "W":
				move = Move.UP;
			break;
			
			case "S":
				move = Move.DOWN;
			break;

			default:
			break;
		}

		return move;
	}
	
	private static void mainMenu() {
		
		System.out.println("\t******************");
		System.out.println("\t* Dungeon Keeper *");
		System.out.println("\t******************");
		System.out.println();
		System.out.println("\t1. Play");
		System.out.println("\t2. Exit");
		System.out.println();
	}

}