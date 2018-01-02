package Game;

import Message.MessageController;

public class GameModel {
	private MessageController messageController;
	private String gameID;
	
	public GameModel() {
		messageController = new MessageController();
	}
	
	public MessageController getMessageController(){
		return this.messageController;
	}
	
	public void setGameID(String _gameID) {
		this.gameID = _gameID;
	}
}
