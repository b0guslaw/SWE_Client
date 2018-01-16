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
		_ownHalf = new MapNode[8][4];
		_otherHalf = new MapNode[8][4];
		castleIndex = new int[2];
	}

	/**
	 * Map generation takes place with a random number generator. The map generator
	 * itself does not check for business rules during generation. Instead it places
	 * all MapNodes into the array and then checks if the business rules where
	 * upheld. If not this function returns false.
	 * 
	 * @return boolean
	 */
	public boolean generateOwnHalf() {
		logger.info("Starting map generation");
		int grassCount = 0, waterCount = 0, mountainCount = 0;
		int randNum;
		MapNode node = null;
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 4; y++) {
				randNum = ThreadLocalRandom.current().nextInt(0, 11);
				if (randNum < 2 && waterCount != 4 && x != 0) {
					node = new MapNode("Water", false, false);
					++waterCount;

				} else if (randNum > 6 && mountainCount != 4) {
					node = new MapNode("Mountain", false, false);
					++mountainCount;

				} else {
					node = new MapNode("Grass", false, false);
					++grassCount;
				}
				_ownHalf[x][y] = node;
			}
		}

		/*
		 * This is to uphold business rules. Map generation happens quickly and is
		 * fairly cheap. If this function returns false, we'll just generate a new map.
		 * By chance mountains and grass each have a 40% chance, water a 20% chance.
		 * This also reduces the risk of islands.
		 */
		if (waterCount > 4 && grassCount > 5 && mountainCount > 3) {
			logger.warn("Map generation failed because of not upheld business rules. Retrying...");
			return false; // no reason to continue at this point
		}

		int xFort = ThreadLocalRandom.current().nextInt(0, 8);
		int yFort = ThreadLocalRandom.current().nextInt(0, 4);
		
		while(true) {
			if(_ownHalf[xFort][yFort].getTerrainType().equalsIgnoreCase("grass")) {
				_ownHalf[xFort][yFort].setFort();
				break;
			}
			xFort = ThreadLocalRandom.current().nextInt(0, 8);
			yFort = ThreadLocalRandom.current().nextInt(0, 4);
		}
		
		//this is just to be sure I can find the castle again by easy access without going through the whole map
		castleIndex[0] = xFort;
		castleIndex[1] = yFort;
		logger.info("Map generation completed");
		return true;
	}

	/**
	 * Should map generation fail we have this function to delete the map,
	 * initialize a new one and start over.
	 */
	public void resetOwnHalf() {
		_ownHalf = null;
		_ownHalf = new MapNode[8][4];
	}

	/**
	 * After map generation is done, this function rebuilds the full map. 
	 * @param ArrayList<NewMapNode> 
	 */
	public void assembleFullMap(ArrayList<NewMapNode> mapNodes) {
		try {
			//TODO
		} catch (NullPointerException npe) {
			logger.error("Assembling the full map failed. Maybe the other half was missing?");
			npe.printStackTrace();
		}
	}

	public ArrayList<NewMapNode> getOwnHalfArrayList() {
		ArrayList<NewMapNode> helperArrayList = new ArrayList<NewMapNode>();

		for (int x = 0; x < 8; ++x) {
			for (int y = 0; y < 4; ++y) {
				NewMapNode temp = new NewMapNode();
				temp.setX(x);
				temp.setY(y);
				temp.setTerrain(_ownHalf[x][y].getTerrainType());
				temp.setFortPresent(_ownHalf[x][y].IsFortPresent());
				helperArrayList.add(temp);
			}
		}
		return helperArrayList;
	}
	
	public MapNode[][] getOwnHalf() {
		return this._ownHalf;
	}

	public void createFullMapFromArrayList(ArrayList<NewMapNode> map) {
		
	}
}
