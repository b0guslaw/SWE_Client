package Game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameController {
	private GameModel model;
	private GameView view;
	private Logger logger = LoggerFactory.getLogger(GameController.class);
	
	public GameController() {
		model = new GameModel();
		view = new GameView();
	}
}
