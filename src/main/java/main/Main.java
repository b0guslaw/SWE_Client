package main;

import javax.xml.bind.JAXBException;

import game.GameController;

public class Main {

	public static void main(String[] args) throws JAXBException {
		GameController cont = new GameController();
		cont.startGame();
		System.out.println(cont.getGameModel().getGameID());
	}

}
