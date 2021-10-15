package com.example.demo1.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("twitter")
public class TwitterReplyController {
	
	@Autowired
	private TwitterReplyService service;
	
	@GetMapping("/reply/{str}")
	public void replyTweet(@PathVariable String str) {
		service.replyToTweet();
	}
}
