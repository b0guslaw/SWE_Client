package game;

public class MapNode {
	private String terrainType;
	private boolean fortPresent;
	private boolean treasurePresent;
	
	public MapNode(String terraintype, boolean fortPresent) {
		if(terraintype.equals("water") || terraintype.equals("grass") || terraintype.equals("mountain")) {
			this.terrainType = terraintype;
			this.fortPresent = fortPresent;			
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public String getTerrainType() {
		return this.terrainType;
	}
	
	public boolean IsFortPresent(){
		return fortPresent;
	}
	
	public boolean IsTreasurePresent(){
		return treasurePresent;
	}
}
