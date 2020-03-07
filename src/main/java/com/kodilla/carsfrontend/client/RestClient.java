package com.kodilla.carsfrontend.client;

import com.kodilla.carsfrontend.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Component
public class RestClient {
    @Autowired
    private RestTemplate restTemplate;

    public List<UserDto> getAllUsers() {
        try {
            UserDto[] response = restTemplate.getForObject("http://localhost:8081/v1/users", UserDto[].class);
            return Arrays.asList(ofNullable(response).orElse(new UserDto[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }

    public List<UserDto> getUsersByLastName(final String lastName) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/v1/users/getUsersByLastName")
                .queryParam("lastName", lastName)
                .build().encode().toUri();

        try {
            UserDto[] response = restTemplate.getForObject(url, UserDto[].class);
            return Arrays.asList(ofNullable(response).orElse(new UserDto[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }
}
