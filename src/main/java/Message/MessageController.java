package Message;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.xml.sax.SAXException;

import jaxb.GameIdentifier;
import jaxb.PlayerRegistration;

/*This class is responsible of all communication with the server. It also serves as a XML Parser.
*Messages and their parsing are kept in the same class and functions to avoid unnecessary function class
*/
public class MessageController {
	private String url; //= "http://swe.wst.univie.ac.at:18235";
	
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
	 * @throws JAXBException 
	 */
	public boolean registerPlayer(String firstName, String lastName, String studentID, String gameID) throws JAXBException {
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
		System.out.println(xmlString);
		//Build POST
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		HttpEntity<String> request = new HttpEntity<String>(xmlString.toString(), headers);
		
		String postUrl = url + "/game/" + gameID + "/register";
		final ResponseEntity<String> response = restTemplate.postForEntity(postUrl, request, String.class);
		System.out.println(response.getBody());
		//return true if POST request was successful, otherwise false
		return true;
	}

}