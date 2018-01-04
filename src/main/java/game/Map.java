package game;

import java.util.ArrayList;

public class Map {
	private ArrayList<MapNode> ownMapHalf, otherMapHalf, fullMap;
	
	public void generateOwnHalf() {
		//TODO 
	}
	
	public void setOtherHalf(ArrayList<MapNode> otherMapHalf) {
		this.otherMapHalf = new ArrayList<MapNode>();
		try {
			for(MapNode node : otherMapHalf) {
				otherMapHalf.add(node);
			} 
		}
		catch (NullPointerException npe) {
				npe.printStackTrace();
		}
	}
	
	public void generateFullMap() {
		try {
			fullMap = new ArrayList<MapNode>();
			for(MapNode node : ownMapHalf) {
				fullMap.add(node);
			}
			for(MapNode node : otherMapHalf) {
				fullMap.add(node);
			}
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		}
	}
}
