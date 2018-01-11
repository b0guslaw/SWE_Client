package game;

public class MapNode {
	private String terrainType;
	private boolean fortPresent;
	private boolean treasurePresent;
	
	public MapNode(String _terraintype, boolean fortPresent, boolean treasurePresent) {
		if((_terraintype.equals("water") || _terraintype.equals("grass") || _terraintype.equals("mountain"))) {
			this.terrainType = _terraintype;
			this.fortPresent = fortPresent;	
			this.treasurePresent = treasurePresent;
			System.out.println("I am: " + terrainType);
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
	
	public void setFort() {
		this.fortPresent = true;
	}
	
	public void setTreasure() {
		this.treasurePresent = true;
	}
}
