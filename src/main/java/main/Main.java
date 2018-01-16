package main;

import java.util.Scanner;

import javax.xml.bind.JAXBException;

import game.GameController;

public class Main {

	public static void main(String[] args) throws JAXBException {
		
		String firstName, lastName, studentID, url = null, gameID = null;
		
		if(args.length == 0 || args.length > 5) {
			System.out.println("No or wrong parameters given. Quitting.");
			System.out.println("Correct usage: ");
			System.out.println("To start a new game: -url <url>");
			System.out.println("To connect to an existing game: -url <url> -id <gameID>");
			return;
		}
		
		if(args[0].startsWith("-url")) {
			if(args[1].startsWith("http")) {
				url = args[1];
			} else {
				System.out.println("That is not a valid url. It should start with http");
				return;
			}
		}
		
		if(args[0].startsWith("-id")) {
			gameID = args[1];
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your first name:");
		firstName = sc.nextLine();
		System.out.println("Enter your last name:");
		lastName = sc.nextLine();
		System.out.println("Enter your studentID:");
		studentID = sc.nextLine();
		sc.close();
		
		GameController cont = new GameController(url, firstName, lastName, studentID, gameID);
		cont.startGame();
		cont.getGameModel().generateOwnMap();
		cont.getGameModel().transferMapToServer();
		
		String endResult = null;
		
		try {
			endResult = cont.runGame();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(endResult != null) {
			System.out.println("The game was " + endResult);
		} else {
			System.out.println("Something went wrong, please refer to the stacktrace. Ending...");
		}
	}

}
