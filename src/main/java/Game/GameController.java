package Game;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jaxb.NewGame;

public class GameController {
	private GameModel model;
	private GameView view;
	
	private JAXBContext context;
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	
	private Logger logger = LoggerFactory.getLogger(GameController.class);
	
	public GameController() {
		model = new GameModel();
		view = new GameView();
	}
	
	public void startGame() throws JAXBException {
		String id = model.getMessageController().newGame();
		context = JAXBContext.newInstance(NewGame.class);
		unmarshaller = context.createUnmarshaller();
	}
}
