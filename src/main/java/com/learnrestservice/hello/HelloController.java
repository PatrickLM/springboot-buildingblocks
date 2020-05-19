package com.learnrestservice.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	// Simple method
	// URI - /helloworld
	//GET
	// @RequestMapping(method=RequestMethod.GET, path="/helloworld")
	@GetMapping("/helloworld")
	public String helloWorld() {
		return "Hello world Rest controller. Using GetMapping";
	}
	
	@GetMapping("/helloworld-bean")
	public UserDetails hellowWorldBean() {
		return new UserDetails("Patrick", "Mashaba", "Mbombela");
	}
}
