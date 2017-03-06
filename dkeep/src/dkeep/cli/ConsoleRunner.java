package dkeep.cli;

import dkeep.logic.Game;
import dkeep.logic.Move;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleRunner {
	
	private static Game game = null;
	
	public static void main(String[] args) {
		
		mainMenu();

		Scanner scanner = new Scanner(System.in);
		int input = userInput(scanner, "\n\t1. Play\n\t2. Exit\nOption?", 1, 2);
		
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
		
		boolean gameEnd = false;
		
		//ask for type of guard
		
		//printDungeon();

		while (!gameEnd) {
			Move move = getHeroMove(scanner);
			
			gameEnd = game.updateGame(move);

			//printDungeon();
			
			if(game.getState() == Game.State.TRANSITION){
				//change map. ask for number of ogres
			}
		}

		if (game.getState() == Game.State.DEFEAT) {
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
	
	/*private static void printDungeon() {
		System.out.println();
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
		
		char c = input.charAt(0);
		
		switch (c) {
			case 'D':
				move = Move.RIGHT;
			break;
			
			case 'A':
				move = Move.LEFT;
			break;
			
			case 'W':
				move = Move.UP;
			break;
			
			case 'S':
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
		System.out.println("\t******************\n");
	}

}