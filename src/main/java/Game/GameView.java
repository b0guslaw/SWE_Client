package game;

import java.util.ArrayList;

public class GameView {

	public void printOwn(MapNode[][] _ownHalf) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 4; j++) {
				System.out.print("X:" +j + " Y: "+i +" ");
				if(_ownHalf[i][j].getTerrainType().equalsIgnoreCase("water")) {
					System.out.print("W ");
				} else if(_ownHalf[i][j].getTerrainType().equalsIgnoreCase("grass")) {
					System.out.print("G ");
				} else if(_ownHalf[i][j].getTerrainType().equalsIgnoreCase("mountain")) {
					System.out.print("M ");
				}
			}
			System.out.println("");
		}
		
	}
}
