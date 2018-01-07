package main;

import game.GameController;

public class Main {

	public static void main(String[] args) {
		GameController cont = new GameController();
		cont.getGameModel().getMap().generateOwnHalf();
		cont.getGameView().printOwn(cont.getGameModel().getMap().getRawOwnNode());

	}

}
