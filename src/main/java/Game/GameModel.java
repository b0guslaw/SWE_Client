package game;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Message.MessageController;
import jaxb.GameState;

public class GameModel {
	private MessageController messageController;
	private String gameID, uniqueplayerID;
	private String playerFirstName, playerLastName, studentID;
	private Map map;
	
	private Logger logger = LoggerFactory.getLogger(GameModel.class);
	
	public GameModel(String url, String _playerFirstName, String  _playerLastName, String _studentID, String _gameID) {
		messageController = new MessageController(url);
		this.playerFirstName = _playerFirstName;
		this.playerLastName = _playerLastName;
		this.studentID = _studentID;
		if(_gameID != null) {
			this.gameID = _gameID;	
		}
		this.map = new Map();
	}
	
	public void startNewGame() {
		try {
			if(gameID == null) {
				logger.info("Initializing connection to the server...");
				gameID = messageController.newGame();
				logger.info("Retrieved Game ID, asking the server for a unique player ID...");
				uniqueplayerID = messageController.registerPlayer(playerFirstName, playerLastName, studentID, gameID);
				logger.info("Connection established!");
			} else {
				logger.info("Trying to connect to supplied gameID...");
				uniqueplayerID = messageController.registerPlayer(playerFirstName, playerLastName, studentID, gameID);
				logger.info("Retrieved Player ID!");
				transferMapToServer();
			}
		} catch (JAXBException e) {
			logger.error("There was an error parsing the XML when creating a new Game.");
			e.printStackTrace();
		}
	}
	
	public void transferMapToServer() {
		try {
			logger.info("Sending Half Map to the server...");
			messageController.sendHalfMap(gameID, uniqueplayerID, map);
			logger.info("HalfMap has been transferred!");
		} catch (JAXBException e) {
			logger.error("There was an error parsing the XML when sending our halfmap to the server.");
			e.printStackTrace();
		}
	}
	
	public String updateGameState() {
		GameState gs = new GameState();
		String winState = null;
		
		try {
			gs = messageController.requestGameState(gameID, uniqueplayerID);
			winState = gs.getState();
		} catch (JAXBException e) {
			logger.error("There was an error parsing the XML when requesting the GameState.");
			e.printStackTrace();
		}
		//update game state
		if(winState.equalsIgnoreCase("shouldactnext")) {
			
		} else if (winState.equalsIgnoreCase("shouldwait")) {
			return winState;
		} else if (winState.equalsIgnoreCase("won")) {
			return winState;
		} else if (winState.equalsIgnoreCase("lost")) {
			return winState;
		}
		
		logger.info("I could not determine the current gameState. This means the connection to the server was lost.");
		return null;
	}
	
	public GameModel getGameModel() {
		return this;
	}
	
	public void setGameID(String _gameID) {
		this.gameID = _gameID;
	}
	
	public String getGameID() {
		return this.gameID;
	}
	
	public void setPlayerID(String _playerID) {
		this.uniqueplayerID = _playerID;
	}
	
	public String getPlayerID() {
		return this.uniqueplayerID;
	}
	
	public void setPlayerFirstName(String _playerName) {
		this.playerFirstName = _playerName;
	}
	
	public String getPlayerFirstName() {
		return this.playerFirstName;
	}
	
	public void setPlayerName(String _playerName) {
		this.playerLastName = _playerName;
	}
	
	public String getPlayerLastName() {
		return this.playerLastName;
	}
	
	public void generateOwnMap() {
		boolean finished = map.generateOwnHalf();
		while(!finished) {
			map.resetOwnHalf();
			finished = map.generateOwnHalf();
		}
	}
	
	public Map getMap() {
		return this.map;
	}
}
