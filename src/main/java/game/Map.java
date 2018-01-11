package game;

import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Map {
	private MapNode[][] _fullMap, _ownHalf, _otherHalf;
	private int treasureIndex, castleIndex;
	
	private Logger logger = LoggerFactory.getLogger(Map.class);
	
	public Map() {
		_fullMap = new MapNode[8][8];
		_ownHalf = new MapNode[4][8];
		_otherHalf = new MapNode[4][8];
	}
	
	public boolean generateOwnHalf() {
		int grassCount = 0, waterCount = 0, mountainCount = 0, nodeCount = 0;
		int randNum;
		MapNode node = null;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 8; j++) {
				randNum = ThreadLocalRandom.current().nextInt(0, 11);
				if(randNum < 2 && waterCount != 4 && i != 0) {
					node = new MapNode("water", false, false);
					++waterCount;
					
				} else if (randNum > 6 && mountainCount != 4) {
					node = new MapNode("mountain", false, false);
					++mountainCount;
					
				} else {
					node = new MapNode("grass", false, false);
					++grassCount;
				}
				_ownHalf[i][j] = node;
				nodeCount++;
			}
		}
	
		
		/*This is to uphold business rules. Map generation happens quickly and is
		 * fairly cheap. If this function returns false, we'll just generate a new map. By
		 * chance mountains and grass each have a 40% chance, water a 20% chance. This also
		 * reduces the risk of islands
		 */
		if(waterCount > 4 && grassCount > 5 && mountainCount > 3) {
			return false;
		}
		
		int randRow = ThreadLocalRandom.current().nextInt(5, 9);
		int randCol = ThreadLocalRandom.current().nextInt(0, 8);		
		_ownHalf[randRow][randCol].setTreasure();
		
		int randRowFort = ThreadLocalRandom.current().nextInt(5, 9);
		int randColFort = ThreadLocalRandom.current().nextInt(0, 8);	
		while(_ownHalf[randRowFort][randColFort].setFort()) {
			randRowFort = ThreadLocalRandom.current().nextInt(5, 9);
			randColFort = ThreadLocalRandom.current().nextInt(0, 8);
		}
		return true;
	}
	
	public void setOtherHalf(MapNode[][] otherMapHalf) {
		try {
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 8; j++) {
					_otherHalf[i][j] = otherMapHalf[i][j];
				}
			}
		}
		catch (NullPointerException npe) {
				npe.printStackTrace();
		}
	}	
	
	public void resetOwnHalf() {
		_ownHalf = null;
		_ownHalf = new MapNode[4][8];
	}

	public void assembleFullMap() {
		try {
			//assembling top half
			for(int i = 0; i < 4; ++i) {
				for(int j = 0; j < 8; ++j) {
					_fullMap[i][j] = _otherHalf[i][j]; 
				}
			}
			//assembling lower (own) half
			int ownRow = 0;
			for(int i = 5; i <= 8; ++i) {
				for(int j = 0; j < 8; ++j) {
					_fullMap[i][j] = _ownHalf[ownRow][j];
					++ownRow;
				}
			}
		} catch (NullPointerException npe) {
			logger.error("Assembling the full map failed. Maybe the other half was missing?");
			npe.printStackTrace();
		}
	}
	
	public MapNode[][] getRawOwnNode() {
		return this._ownHalf;
	}
}
