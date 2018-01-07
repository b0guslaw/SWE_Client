package game;

public class GameView {

	public void printOwn(MapNode[][] _ownHalf) {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 8; j++) {
				if(_ownHalf[i][j].getTerrainType().equals("water")) {
					System.out.print("~");
				} else if(_ownHalf[i][j].getTerrainType().equals("grass")) {
					System.out.print("w");
				} else if(_ownHalf[i][j].getTerrainType().equals("mountain")) {
					System.out.print("A");
				}
			}
			System.out.println("");
		}
	}
}
