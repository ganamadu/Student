package com.stud.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class StudentRestClient {
	
	
	public <T> List<T> getListApi(final String path, final HttpMethod method) {     
	    final RestTemplate restTemplate = new RestTemplate();
	    final ResponseEntity<List<T>> response = restTemplate.exchange(
	      path,
	      method,
	      null,
	      new ParameterizedTypeReference<List<T>>(){});
	    List<T> list = response.getBody();
	    return list;
	}
	
	public <T> T getObjectApi(final String path, final HttpMethod method, Class<T> returnType) {     
	    final RestTemplate restTemplate = new RestTemplate();
	    final ResponseEntity<T> response = restTemplate.exchange(
	      path,
	      method,
	      null,
	      returnType);
	    return response.getBody();
	}

}
