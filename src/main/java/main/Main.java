package main;

import java.util.Scanner;

import javax.xml.bind.JAXBException;

import game.GameController;

public class Main {

	public static void main(String[] args) throws JAXBException {
		boolean testingEnv = true; //toggle this to false for productive usage
		if(args.length > 2 && !testingEnv) {
			System.out.println("Please provide the URL of the server you are trying to connect to as argument");
			return;
		}
		String firstName, lastName, studentID, url, gameID = null;
		url = args[0];
		if(args.length == 2) {
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
		try {
			cont.runGame();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
