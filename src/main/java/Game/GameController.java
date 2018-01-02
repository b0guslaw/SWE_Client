package Game;

public class GameController {
	private GameModel model;
	private GameView view;
	
	public GameController() {
		model = new GameModel();
		view = new GameView();
	}
}
