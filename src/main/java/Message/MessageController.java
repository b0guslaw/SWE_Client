package Message;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import game.Map;
import jaxb.GameIdentifier;
import jaxb.GameState;
import jaxb.HalfMap;
import jaxb.PlayerRegistration;
import jaxb.ResponseEnvelope;

/*This class is responsible of all communication with the server. It also serves as a XML Parser.
* Messages and their parsing are kept in the same class and functions to avoid unnecessary function class */
public class MessageController {
	private String url; //= "http://swe.wst.univie.ac.at:18235";
	private Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	/**
	 * Specifies the server url in the constructor
	 * @param _url
	 */
	public MessageController(String _url) {
		this.url = _url;
	}
	
	/**
	 * Sends a request to the server to create a new game. Upon receive the XML it is unpacked
	 * and the gameID returned as String 
	 * @return String
	 * @throws JAXBException
	 */
	public String newGame() throws JAXBException {
		String response = "", requestString = url + "/game/new";
		RestTemplate restTemplate = new RestTemplate();
		response = restTemplate.getForObject(requestString, String.class);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(GameIdentifier.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		//System.out.println("XML" + response);
		
		StringReader reader = new StringReader(response);
		GameIdentifier game = (GameIdentifier) unmarshaller.unmarshal(reader);
		
		return game.getGameID();
	}

	/**
	 * Registers player to the earlier specified gameID. Returns the unique player ID as we
	 * get it from the server.
	 * @param firstName, lastName, gameID
	 * @return String
	 * @throws JAXBException 
	 */
	public String registerPlayer(String firstName, String lastName, String studentID, String gameID) throws JAXBException {
		//Build XML
		PlayerRegistration playerRegistration = new PlayerRegistration();
		playerRegistration.setStudentFirstName(firstName);
		playerRegistration.setStudentLastName(lastName);
		playerRegistration.setStudentID(studentID);
		
		JAXBContext context = JAXBContext.newInstance(PlayerRegistration.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		StringWriter xmlString = new StringWriter();
		marshaller.marshal(playerRegistration, xmlString);

		//Build POST
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		HttpEntity<String> request = new HttpEntity<String>(xmlString.toString(), headers);
		//Send POST
		String postUrl = url + "/game/" + gameID + "/register";
		System.out.println("Sent request: " + request.toString());
		String response = restTemplate.postForObject(postUrl, request, String.class);
		System.out.println("Found response: " + response);
		StringReader reader = new StringReader(response);
		//unmarshall response from Server
		JAXBContext responseContext = JAXBContext.newInstance(ResponseEnvelope.class);
		Unmarshaller unmarshaller = responseContext.createUnmarshaller();
		ResponseEnvelope envelope = (ResponseEnvelope) unmarshaller.unmarshal(reader);
		
		//System.out.println("~~~~~~~~~~~ XML: " + response);
		if(envelope != null) {
			System.out.println(envelope.getState());
			System.out.println(envelope.getUniquePlayerIdentifier());
			return envelope.getUniquePlayerIdentifier();
		} else {
			logger.error("Something went wrong during the player registration");
			return null;
		}
	}
	
	/**
	 * Sends the generated halfmap to the server as XML. Returns the status code of the game as String
	 * @param gameID
	 * @param playerID
	 * @param _map
	 * @return String
	 * @throws JAXBException
	 */
	public String sendHalfMap(String gameID, String playerID, Map _map) throws JAXBException {
		//Build XML
		HalfMap map = new HalfMap();
		map.setUnqiuePlayerID(playerID);
		map.setNewMapNodes(_map.getOwnHalfArrayList());
		
		JAXBContext context = JAXBContext.newInstance(HalfMap.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		StringWriter xmlString = new StringWriter();
		marshaller.marshal(map, xmlString);
		
		//Build POST
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		HttpEntity<String> request = new HttpEntity<String>(xmlString.toString(), headers);
		//Send POST
		String postUrl = url + "/game/" + gameID + "/halfmap";
		String response = restTemplate.postForObject(postUrl, request, String.class);
		StringReader reader = new StringReader(response);
		//unmarshal response from server
		JAXBContext responseContext = JAXBContext.newInstance(ResponseEnvelope.class);
		Unmarshaller unmarshaller = responseContext.createUnmarshaller();
		ResponseEnvelope envelope = (ResponseEnvelope) unmarshaller.unmarshal(reader);
		return envelope.getState();
	}
	
	/**
	 * Handles gamestate GET requests to the server. Returns the entire GameState object,
	 * as the gameController needs to pass it to the GameModel for processing
	 * @param gameID
	 * @param playerID
	 * @return GameState
	 * @throws JAXBException
	 */
	public GameState requestGameState(String gameID, String playerID) throws JAXBException {
		String response = null;
		String _url = this.url + "/game/" + gameID + "/state/" + playerID;
		GameState gameState = new GameState();
		
		RestTemplate restTemplate = new RestTemplate();
		response = restTemplate.getForObject(_url, String.class);
		
		System.out.println("--------------------");
		System.out.println(response);
		System.out.println("--------------------");
		JAXBContext jaxbContext = JAXBContext.newInstance(GameState.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		
		StringReader reader = new StringReader(response);
		gameState = (GameState) unmarshaller.unmarshal(reader);
		
		return gameState;
	}
}