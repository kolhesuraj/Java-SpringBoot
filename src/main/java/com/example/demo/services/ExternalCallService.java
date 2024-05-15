package com.example.demo.services;


import com.example.demo.dto.Location;
import com.example.demo.dto.whetherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalCallService {

    @Value("${whether.api.key}")
    private static String API_KEY;

    @Value("${whether.api.url}")
    private static String API_URL;

    @Autowired
    private RestTemplate restTemplate;

    public Location getWhether(String location){

          String final_location =   location != null ? location : "42.3478,-71.0466";
          String final_url = API_URL.replace("API_KEY",API_KEY).replace("LOCATION",final_location);
          ResponseEntity<whetherDTO> response = restTemplate.exchange(final_url, HttpMethod.GET,null, whetherDTO.class);
          whetherDTO body =response.getBody();
          return body.location;
    }
}
