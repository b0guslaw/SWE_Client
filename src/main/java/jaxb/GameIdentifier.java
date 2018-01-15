package jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="GameIdentifier")
public class GameIdentifier {
	@XmlElement(name = "UniqueGameID")
	private String UniqueGameID;
	
	public String getGameID(){
		return this.UniqueGameID;
	}
	
	public void setGameID(String _gameID) {
		this.UniqueGameID = _gameID;
	}
}
