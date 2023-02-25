package com.example.backendclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class BaseController {
	@Autowired
	WebClient webClient;
	@GetMapping("/getdata")
	public String getdata(@RegisteredOAuth2AuthorizedClient("message-client-authorization-code") OAuth2AuthorizedClient oauth) {
		return webClient.get()
				.uri("http://resource:9000/myCard")
				.attributes(ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(oauth))
				.retrieve().bodyToMono(String.class).block();
	}
}
