package jaxb;

import javax.xml.bind.annotation.XmlElement;

public class UniquePlayerID {
	@XmlElement(name="data")
	private String uniquePlayerID;

	public String getUniquePlayerID() {
		return uniquePlayerID;
	}

	public void setUniquePlayerID(String uniquePlayerID) {
		this.uniquePlayerID = uniquePlayerID;
	}
}
