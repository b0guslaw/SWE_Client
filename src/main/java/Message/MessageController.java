package Message;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;

import jaxb.GameIdentifier;

public class MessageController {
	private String url = "http://swe.wst.univie.ac.at:18235";
	
	public MessageController(String _url) {
		//TODO: REMOVE THIS CONTENT FOR FINAL VERSION
		//this.url = _url;
	}
	
	public String newGame() throws JAXBException {
		String response = "", requestString = url + "/game/new";
		RestTemplate restTemplate = new RestTemplate();
		
		response = restTemplate.getForObject(requestString, String.class);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(GameIdentifier.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(response);
		GameIdentifier game = (GameIdentifier) unmarshaller.unmarshal(reader);
		
		return game.getGameID();
	}
	
	public boolean registerPlayer(String playerName) {
		//Build XML
		
		
		RestTemplate restTemplate = new RestTemplate();
		
		
		return true;
	}
}
