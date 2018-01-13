package jaxb;

import java.util.ArrayList;

public class HalfMap {
	private String unqiuePlayerID;
	private ArrayList<NewMapNode> NewMapNodes;
	
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
