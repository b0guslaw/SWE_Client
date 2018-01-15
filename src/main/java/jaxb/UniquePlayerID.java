package jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="uniquePlayerID")
@XmlType(propOrder = {"uniquePlayerID"})
public class UniquePlayerID {
	private String uniquePlayerID;

	public String getUniquePlayerID() {
		return uniquePlayerID;
	}

	public void setUniquePlayerID(String uniquePlayerID) {
		this.uniquePlayerID = uniquePlayerID;
	}
	
}
