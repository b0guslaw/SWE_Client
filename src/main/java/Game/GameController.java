package game;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jaxb.GameIdentifier;

public class GameController {
	private GameModel model;
	private GameView view;
	
	private Logger logger = LoggerFactory.getLogger(GameController.class);
	
	public GameController(String url, String _playerFirstName, String _playerLastName, String _studentID, String _gameID) {
		model = new GameModel(url, _playerFirstName, _playerLastName, _studentID, _gameID);
		view = new GameView();
	}
	
	public void startGame() {
		model.startNewGame();
	}
	
	public String runGame() throws InterruptedException {
		String winState = model.updateGameState();
		
		while(true) {
			if(winState == null) {
				break;
			}
			if(winState.equalsIgnoreCase("shouldWait")) {
				Thread.sleep(2000);
				winState = model.updateGameState();
				continue;
			}
			if(winState.equalsIgnoreCase("shouldActNext")) {
				winState = model.updateGameState();
				continue;
			}
			if(winState.equalsIgnoreCase("won")) {
				break;
			}
			if(winState.equalsIgnoreCase("lost")) {
				break;
			}
		}
			
		//TODO pass new information to gameView
		return winState;
	}
	
	public GameModel getGameModel() {
		return this.model;
	}
	
	public GameView getGameView() {
		return this.view;
	}
}
