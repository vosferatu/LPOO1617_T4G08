package dkeep.cli;

import dkeep.logic.Game;
import dkeep.logic.State;
import dkeep.logic.Direction;
import dkeep.logic.Personality;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleRunner {
	
	private static Game game = null;
	
	public static void main(String[] args) {
		
		mainMenu();

		Scanner scanner = new Scanner(System.in);
		int input = userInput(scanner, "\t1. Play\n\t2. Exit\nOption?", 1, 2);
		

		switch (input) {
		case 1:
			startDungeon(scanner);
			break;
		case 2:
			System.out.println("\tLeaving Dungeon Keeper. Goodbye...");
			break;
		default:
			break;
		}
		
		scanner.close();
	}
	
	private static void startDungeon(Scanner scanner) {
		
		game = new Game(guardPreferences(scanner, game)); //passes the personality of the guard
		
		//gets the number of ogres
		int ogreCount = userInput(scanner, "\nPlease enter the number of ogres", 1, 5);
		game.setOgreCount(ogreCount);
		
		State gameState = game.getState();
		
		printDungeon(game);

		while (!(gameState == State.DEFEAT  || gameState == State.WON)) {
			
			Direction move = getHeroMove(scanner);
			
			gameState = game.updateGame(move);

			printDungeon(game);
			
		}

		if (gameState == State.DEFEAT) {
			System.out.println("\t************");
			System.out.println("\t*GAME OVER!*");
			System.out.println("\t************");
		} else {
			System.out.println("\t**********");
			System.out.println("\t*YOU WON!*");
			System.out.println("\t**********\n");
		}
	}
	
	private static Personality guardPreferences(Scanner scanner, Game game){
		
		int input = userInput(scanner, "\nGuard Personality:\n 1. Rookie\n 2. Drunken\n 3. Suspicious\n 4. Simple\nOption?", 1, 4);
		Personality person = Personality.SIMPLE;

		switch (input) {
		case 1:
			person = Personality.ROOKIE;
			break;
		case 2:
			person = Personality.DRUNKEN;
			break;
		case 3:
			person = Personality.SUSPICIOUS;
			break;
		default:
			break;
		}
		
		return person;
	}

	private static int userInput(Scanner scanner, String prompt,
			int min, int max) {
		int option = 0;
	
		do {
			
		    try {
				System.out.print(prompt);
				System.out.print("> ");
				
				option = scanner.nextInt();
		    } catch (InputMismatchException e) {
		        System.out.print("Invalid input.\n");
		    }
			
			scanner.nextLine();

		} while (option > max || option < min);

		return option;
	}
	
	private static void printDungeon(Game game) {
		System.out.println(game);
	}

	private static Direction getHeroMove(Scanner scanner) {
		String input;

		do {
			System.out.print("W/A/S/D to move: ");
			input = scanner.next();
		} while (!input.equals("w") && !input.equals("a") && !input.equals("s")
				&& !input.equals("d") && !input.equals("W") && !input.equals("A") 
				&& !input.equals("S") && !input.equals("D"));

		input = input.toUpperCase();
		Direction move = Direction.STAY;
		
		char c = input.charAt(0);
		
		switch (c) {
			case 'D':
				move = Direction.RIGHT;
			break;
			
			case 'A':
				move = Direction.LEFT;
			break;
			
			case 'W':
				move = Direction.UP;
			break;
			
			case 'S':
				move = Direction.DOWN;
			break;

			default:
				move = Direction.STAY;
			break;
		}

		return move;
	}
	
	private static void mainMenu() {
		
		System.out.println("\t******************");
		System.out.println("\t* Dungeon Keeper *");
		System.out.println("\t******************\n");
	}

}