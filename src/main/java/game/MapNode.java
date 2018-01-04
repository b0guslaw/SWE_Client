package game;

public class MapNode {
	private String terrainType;
	private boolean fortPresent;
	
	public MapNode(String terraintype, boolean fortPresent) {
		this.terrainType = terraintype;
		this.fortPresent = fortPresent;
	}
}
