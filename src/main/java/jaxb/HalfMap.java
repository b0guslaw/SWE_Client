package jaxb;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="halfMap")
public class HalfMap {
	@XmlElementWrapper(name="newMapNodes")
	@XmlElement(name="NewMapNode")
	private ArrayList<NewMapNode> NewMapNodes;
	private String unqiuePlayerID;
	
	public String getUnqiuePlayerID() {
		return unqiuePlayerID;
	}
	public void setUnqiuePlayerID(String unqiuePlayerID) {
		this.unqiuePlayerID = unqiuePlayerID;
	}
	public ArrayList<NewMapNode> getNewMapNodes() {
		return NewMapNodes;
	}
	public void setNewMapNodes(ArrayList<NewMapNode> newMapNodes) {
		NewMapNodes = newMapNodes;
	}
}
