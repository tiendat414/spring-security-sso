package com.example.oauth2server.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
        ;

        http.csrf().disable().authorizeHttpRequests()
                .antMatchers("/myCard").hasAuthority("SCOPE_message.read")
                .anyRequest().authenticated()
                .and().oauth2ResourceServer().jwt();
//                .and().oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter);
        return http.build();
    }
// 
// @Bean
// public PasswordEncoder passwordEncoder() {
//	 return new BCryptPasswordEncoder();
// }

}