package game;

import javax.xml.bind.JAXBException;

import Message.MessageController;

public class GameModel {
	private MessageController messageController;
	private String gameID, uniqueplayerID;
	private String playerFirstName, playerLastName, studentID;
	private Map map;
	
	public GameModel(String url, String _playerFirstName, String  _playerLastName, String _studentID) {
		messageController = new MessageController(url);
		this.playerFirstName = _playerFirstName;
		this.playerLastName = _playerLastName;
		this.studentID = _studentID;
		this.map = new Map();
	}
	
	public void startNewGame() {
		try {
			gameID = messageController.newGame();
			uniqueplayerID = messageController.registerPlayer(playerFirstName, playerLastName, studentID, gameID);
			//System.out.println(uniqueplayerID);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public void transferMapToServer() {
		generateOwnMap();
		messageController.sendHalfMap(gameID, uniqueplayerID, map);
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
