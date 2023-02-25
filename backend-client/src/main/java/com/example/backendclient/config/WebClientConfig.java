package com.example.backendclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
	@Bean
	OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager (ClientRegistrationRepository clientRegistrationRepository,
			OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository) {
		OAuth2AuthorizedClientProvider authorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder()
				.authorizationCode()
				.refreshToken()
				.build();
		DefaultOAuth2AuthorizedClientManager authorizedClientManager = new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository, oAuth2AuthorizedClientRepository);
		authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
		return authorizedClientManager;
	}
	
	@Bean
	WebClient webClient(OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager) {
		ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 =
				new ServletOAuth2AuthorizedClientExchangeFilterFunction(oAuth2AuthorizedClientManager);
		return WebClient.builder()
				.apply(oauth2.oauth2Configuration())
				.build();
	}
}
