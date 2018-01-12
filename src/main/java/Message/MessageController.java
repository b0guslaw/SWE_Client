package Message;

import java.io.File;
import java.io.StringReader;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;

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

		System.out.println("XML" + response);
		
		StringReader reader = new StringReader(response);
		GameIdentifier game = (GameIdentifier) unmarshaller.unmarshal(reader);
		
		return game.getGameID();
	}

	/**
	 * Registers player to the earlier specified gameID. Returns false
	 * when registering the player did not succeed.
	 * @param firstName, lastName, gameID
	 * @return boolean
	 */
	public boolean registerPlayer(String firstName, String lastName, String gameID) {
		//Build XML
		
		//Build POST
		
		RestTemplate restTemplate = new RestTemplate();
		
		//return true if POST request was sucessful, otherwise false
		return true;
	}


}
