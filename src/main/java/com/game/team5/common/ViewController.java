package com.game.team5.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {
	
	@RequestMapping(value = "/views/**", method = RequestMethod.GET)
	public void viewJsp() {
		
	}
}
