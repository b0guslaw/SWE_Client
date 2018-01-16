package jaxb;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="ResponseEnvelope")
public class GameState {
	@XmlElement(name = "exceptionName")
	private String exceptionName;
	@XmlElement(name = "exceptionMessage")
	private String exceptionMessage;
	@XmlElement(name = "state")
	private String state;
	@XmlElement(name = "data")
	private ArrayList<Player> player;
	
	public String getExceptionName() {
		return exceptionName;
	}
	public void setExceptionName(String exceptionName) {
		this.exceptionName = exceptionName;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public ArrayList<Player> getPlayer() {
		return player;
	}
	public void setPlayer(ArrayList<Player> player) {
		this.player = player;
	}
	
	private static class Player {
		private String firstName;
		private String lastName;
		private String uniquePlayerID;
		private String playerState;
		
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getUniquePlayerID() {
			return uniquePlayerID;
		}
		public void setUniquePlayerID(String uniquePlayerID) {
			this.uniquePlayerID = uniquePlayerID;
		}
		public String getPlayerState() {
			return playerState;
		}
		public void setPlayerState(String playerState) {
			this.playerState = playerState;
		}
	}
	
	private static class Map {
		
	}

}
