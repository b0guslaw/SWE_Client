package game;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jaxb.NewMapNode;

public class Map {
	private MapNode[][] _fullMap, _ownHalf, _otherHalf;
	private int castleIndex[];
	
	private Logger logger = LoggerFactory.getLogger(Map.class);
	
	/**
	 * Constructor initializes all 
	 */
	public Map() {
		_fullMap = new MapNode[8][8];
		_ownHalf = new MapNode[4][8];
		_otherHalf = new MapNode[4][8];
		castleIndex = new int[2];
	}
	
	/**
	 * Map generation takes place with a random number generator. The map generator itself does not check
	 * for business rules during generation. Instead it places all MapNodes into the array and then checks
	 * if the business rules where upheld. If not this function returns false.
	 * @return boolean
	 */
	public boolean generateOwnHalf() {
		logger.info("Starting map generation");
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
	
		/* This is to uphold business rules. Map generation happens quickly and is
		 * fairly cheap. If this function returns false, we'll just generate a new map. By
		 * chance mountains and grass each have a 40% chance, water a 20% chance. This also
		 * reduces the risk of islands.
		 */
		if(waterCount > 4 && grassCount > 5 && mountainCount > 3) {
			logger.warn("Map generation failed because of not upheld business rules. Retryin...");
			return false; //no reason to continue at this point
		}
		
		int randRowFort = ThreadLocalRandom.current().nextInt(5, 9);
		int randColFort = ThreadLocalRandom.current().nextInt(0, 8);	
		randRowFort = ThreadLocalRandom.current().nextInt(5, 9);
		randColFort = ThreadLocalRandom.current().nextInt(0, 8);
		
		castleIndex[0] = randRowFort;
		castleIndex[1] = randColFort;
		logger.info("Map generation completed");
		return true;
	}
	
	/**
	 * This function takes a 2D Array of MapNodes as argument and copies it into the local field.
	 * @param otherMapHalf
	 */
	public void setOtherHalf(MapNode[][] otherMapHalf) {
		try {
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 8; j++) {
					_otherHalf[i][j] = otherMapHalf[i][j];
				}
			}
		}
		catch (NullPointerException npe) {
			logger.error("The received map was null ");
				npe.printStackTrace();
		}
	}	
	
	/**
	 * Should map generation fail we have this function to delete the map, initialize a new one
	 * and start over.
	 */
	public void resetOwnHalf() {
		_ownHalf = null;
		_ownHalf = new MapNode[4][8];
	}

	/**
	 * After map generation is done, we can assemble the two map halfs. In this client's implementation the lower map is always the own map.
	 */
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
	
	public ArrayList<NewMapNode> getOwnHalfArrayList(){
		ArrayList<NewMapNode> helperArrayList = new ArrayList<NewMapNode>();
		
		for(int i = 0; i < 4; ++i) {
			for(int j = 0; j < 8; ++j) {
				NewMapNode temp = new NewMapNode();
				temp.setX();
			}
		}
		return helperArrayList;
	}
	
	public MapNode[][] getOwnHalf() {
		return this._ownHalf;
	}
}
