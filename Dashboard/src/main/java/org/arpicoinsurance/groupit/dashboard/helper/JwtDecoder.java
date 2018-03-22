package org.arpicoinsurance.groupit.dashboard.helper;
import java.io.IOException;
import java.util.HashMap;

import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtDecoder {
	public String generate(String token) {
		
		Jwt jwtToken =JwtHelper.decode(token);
		String claims = jwtToken.getClaims();
		try {
			HashMap claimsMap = new ObjectMapper().readValue(claims, HashMap.class);
			System.out.println(claimsMap.get("userCode").toString()+"///////");
			return claimsMap.get("userCode").toString();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(claims);
		return null;
		
	}
}
