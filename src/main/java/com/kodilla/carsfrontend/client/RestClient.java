package com.kodilla.carsfrontend.client;

import com.kodilla.carsfrontend.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class RestClient {
    @Autowired
    private RestTemplate restTemplate;

    public List<UserDto> getAllUsers() {
        try {
            UserDto[] response = restTemplate.getForObject("http://localhost:8081/v1/users", UserDto[].class);
            return Arrays.asList(Optional.ofNullable(response).orElse(new UserDto[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }
}
