package mul.cam.a.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import mul.cam.a.chatbot.Chatbot;

@RestController
public class ChatbotController {
	
	@PostMapping(value = "chatBot")
	public String chatBot(String msg) {
		//System.out.println("NaverCloudController chatBot " +  new Date());
		
		String json = Chatbot.chatBot(msg);
		return json;
	}
}