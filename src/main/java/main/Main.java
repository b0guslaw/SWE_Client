package main;

import java.util.Scanner;

import javax.xml.bind.JAXBException;

import game.GameController;

public class Main {

	public static void main(String[] args) throws JAXBException {
		boolean testingEnv = true; //toggle this to false for productive usage
		if(args.length != 1 && !testingEnv) {
			System.out.println("Please provide the URL of the server you are trying to connect to as argument");
			return;
		}
		String firstName, lastName, studentID, url;
		url = args[0];
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your first name:");
		firstName = sc.nextLine();
		System.out.println("Enter your last name:");
		lastName = sc.nextLine();
		System.out.println("Enter your studentID:");
		studentID = sc.nextLine();
		sc.close();
		
		GameController cont = new GameController(url, firstName, lastName, studentID);
		cont.startGame();
	}

}
