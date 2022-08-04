package com.javatechie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class ReactiveVideoStreamingApplication {

	@Autowired
	private StreamingService streamingService;
	
	// we don't need all bytes of the video , based on the range the bytes will loaded
	@GetMapping(value = "video/{title}" , produces = "video/mp4")
	public Mono<Resource> getVideo(@PathVariable String title,@RequestHeader String range){
		System.out.println("Title: "+title+" Range in Bytes : "+range);
		return streamingService.getVideo(title);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ReactiveVideoStreamingApplication.class, args);
	}
 
}
