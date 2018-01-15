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
	
	public GameModel(String url, String _playerFirstName, String  _playerLastName, String _studentID) {
		messageController = new MessageController(url);
		this.playerFirstName = _playerFirstName;
		this.playerLastName = _playerLastName;
		this.studentID = _studentID;
		this.map = new Map();
	}
	
	public void startNewGame() {
		try {
			logger.info("Initializing connection to the server...");
			gameID = messageController.newGame();
			logger.info("Retrieved Game ID, asking the server for a unique player ID...");
			uniqueplayerID = messageController.registerPlayer(playerFirstName, playerLastName, studentID, gameID);
			logger.info("Connection established!");
		} catch (JAXBException e) {
			logger.error("There was an error parsing the XML when creating a new Game.");
			e.printStackTrace();
		}
	}
	
	public void transferMapToServer() {
		generateOwnMap();
		try {
			logger.info("Sending Half Map to the server...");
			messageController.sendHalfMap(gameID, uniqueplayerID, map);
			logger.info("HalfMap has been transferred!");
		} catch (JAXBException e) {
			logger.error("There was an error parsing the XML when sending our halfmap to the server.");
			e.printStackTrace();
		}
	}
	
	public void updateGameState() {
		GameState gs = new GameState();
		
		try {
			messageController.requestGameState(gameID, uniqueplayerID);
		} catch (JAXBException e) {
			logger.error("There was an error parsing the XML when requesting the GameState.");
			e.printStackTrace();
		}
		//Update Map
		//Check Game State
		//Make Move
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
	
	private void generateOtherMap(MapNode[][] otherMapHalf) {
		map.setOtherHalf(otherMapHalf);
	}
	
	public Map getMap() {
		return this.map;
	}
}
