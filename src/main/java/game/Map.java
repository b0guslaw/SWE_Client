package game;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Map {
	private MapNode[][] _fullMap, _ownHalf, _otherHalf;
	
	private ArrayList<MapNode> ownMapHalf, otherMapHalf, fullMap;
	private int treasureIndex, castleIndex;
	
	public Map() {
		//ownMapHalf = new ArrayList<MapNode>();
		//otherMapHalf = new ArrayList<MapNode>();
		//fullMap = new ArrayList<MapNode>();
		
		//_fullMap = new MapNode[8][8];
		_ownHalf = new MapNode[4][8];
		//_otherHalf = new MapNode[4][8];
	}
	
	public void generateOwnHalf() {
		int castleCount = 0, treasureCount = 0;
		int grassCount, waterCount, mountainCount = 0;
		int randNum;
		MapNode node = null;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 8; j++) {
				randNum = ThreadLocalRandom.current().nextInt(0, 101);
				if(randNum < 33) {
					node = new MapNode("water",false);
				} else if (randNum >= 33 && randNum < 66) {
					node = new MapNode("grass", false);
				} else if (randNum <= 66) {
					node = new MapNode("mountain", false);
				}
				_ownHalf[i][j] = node;
			}
		}
	}
	
	public void setOtherHalf(ArrayList<MapNode> otherMapHalf) {
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
	
	public MapNode[][] getRawOwnNode() {
		return this._ownHalf;
	}
}
