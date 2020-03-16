package com.kodilla.carsfrontend.client;

import com.kodilla.carsfrontend.domain.CarBrandDto;
import com.kodilla.carsfrontend.mapper.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

@Component
public class RestCarBrandClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JsonMapper jsonMapper;

    public List<CarBrandDto> getAllCarBrands() {
        try {
            CarBrandDto[] response = restTemplate.getForObject("http://localhost:8081/v1/carbrands", CarBrandDto[].class);
            return Arrays.asList(ofNullable(response).orElse(new CarBrandDto[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }
}
