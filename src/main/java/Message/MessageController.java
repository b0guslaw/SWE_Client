package Message;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;

public class MessageController {
	private final String url = "http://swe.wst.univie.ac.at:18235";
	
	public String newGame() {
		String template = "", requestString = url + "/game/new";
		RestTemplate restTemplate = new RestTemplate();
		
		template = restTemplate.getForObject(requestString, String.class);
		return template;
	}
}
