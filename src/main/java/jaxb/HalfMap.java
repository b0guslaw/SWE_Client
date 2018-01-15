package jaxb;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(propOrder = {"uniquePlayerID", "newMapNodes"})
@XmlRootElement(name="halfMap")
public class HalfMap {
	@XmlElement(name="uniquePlayerID")
	private String uniquePlayerID;
	@XmlElementWrapper(name="newMapNodes")
	@XmlElement(name="NewMapNode")
	private ArrayList<NewMapNode> NewMapNodes;
	
	public String getUnqiuePlayerID() {
		return uniquePlayerID;
	}
	public void setUnqiuePlayerID(String unqiuePlayerID) {
		this.uniquePlayerID = unqiuePlayerID;
	}
	public ArrayList<NewMapNode> getNewMapNodes() {
		return NewMapNodes;
	}
	public void setNewMapNodes(ArrayList<NewMapNode> newMapNodes) {
		NewMapNodes = newMapNodes;
	}
}
