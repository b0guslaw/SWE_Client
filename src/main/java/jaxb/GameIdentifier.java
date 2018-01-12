package jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class GameIdentifier {
	@XmlElementWrapper(name = "GameIdentifier")
	@XmlElement(name = "UniqueGameID")
	private String UniqueGameID;
	
	public String getGameID(){
		return this.UniqueGameID;
	}
	
	public void setGameID(String _gameID) {
		this.UniqueGameID = _gameID;
	}
}
