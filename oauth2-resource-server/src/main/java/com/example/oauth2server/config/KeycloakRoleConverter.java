package com.example.oauth2server.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>>{

	@Override
	@SuppressWarnings("unchecked")
	public Collection<GrantedAuthority> convert(Jwt jwt) {
		Map<String,Object> realAccess = (Map<String, Object>) jwt.getClaims().get("real_access");
		if(realAccess == null|| realAccess.isEmpty()) {
			return new ArrayList<>();
		}
		
		List<String> a = (List<String>) realAccess.get("roles");
		System.out.println("ROLE "+ a.toString());
		Collection<GrantedAuthority> returnValue = a.stream().map(roleName -> "ROLE_"+ roleName)
				.map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		return returnValue;
	}

}
