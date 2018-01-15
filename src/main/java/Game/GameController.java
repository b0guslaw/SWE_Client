package game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameController {
	private GameModel model;
	private GameView view;
	
	private Logger logger = LoggerFactory.getLogger(GameController.class);
	
	public GameController(String url, String _playerFirstName, String _playerLastName, String _studentID, String _gameID) {
		model = new GameModel(url, _playerFirstName, _playerLastName, _studentID, _gameID);
		view = new GameView();
	}
	
	public void startGame() {
		logger.info("Initalizing game");
		model.startNewGame();
	}
	
	public String runGame() throws InterruptedException {
		logger.info("Starting main game logic");
		String winState = model.updateGameState();
		
		while(true) {
			if(winState == null) {
				break;
			}
			if(winState.equalsIgnoreCase("shouldWait")) {
				Thread.sleep(1000);
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
		
		//when this return statement is reached, the game is over. Checking the state happens in main
		return winState;
	}
	
	public GameModel getGameModel() {
		return this.model;
	}
	
	public GameView getGameView() {
		return this.view;
	}
}
