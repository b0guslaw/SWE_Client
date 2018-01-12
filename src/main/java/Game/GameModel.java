package game;

import javax.xml.bind.JAXBException;

import Message.MessageController;

public class GameModel {
	private MessageController messageController;
	private String gameID, playerID;
	private String playerFirstName, playerLastName;
	private Map map;
	
	public GameModel() {
		messageController = new MessageController("");
		this.map = new Map();
	}
	
	public void startNewGame() {
		try {
			gameID = messageController.newGame();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
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
		this.playerID = _playerID;
	}
	
	public String getPlayerID() {
		return this.playerID;
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
	
	private void generateOwnMap() {
		boolean finished = false;
		while(finished) {
			map.resetOwnHalf();
			finished = map.generateOwnHalf();
		}
	}
	
	private void generateOtherMap(MapNode[][] otherMapHalf) {
		map.setOtherHalf(otherMapHalf);
	}
	
	public void generateMap () {
		generateOwnMap();
		//map.setOtherHalf(); <- XML goes here
		map.assembleFullMap();
	}
}
