package game;

import Message.MessageController;

public class GameModel {
	private MessageController messageController;
	private String gameID, playerID;
	private String playerFirstName, playerLastName;
	
	public GameModel() {
		messageController = new MessageController();
	}
	
	public MessageController getMessageController(){
		return this.messageController;
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
}
