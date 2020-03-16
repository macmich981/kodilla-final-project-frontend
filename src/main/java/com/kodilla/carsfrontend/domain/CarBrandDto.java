package com.kodilla.carsfrontend.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarBrandDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("brandName")
    private String brandName;

    @JsonProperty("constructionYear")
    private int constructionYear;
}
