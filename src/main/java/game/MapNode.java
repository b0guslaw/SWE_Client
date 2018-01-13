package game;

public class MapNode {
	private String terrainType;
	private boolean fortPresent;
	
	public MapNode(String _terraintype, boolean fortPresent, boolean treasurePresent) {
		if((_terraintype.equals("water") || _terraintype.equals("grass") || _terraintype.equals("mountain"))) {
			this.terrainType = _terraintype;
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
	
	public void setFort() {
		this.fortPresent = true;
	}
	
}
