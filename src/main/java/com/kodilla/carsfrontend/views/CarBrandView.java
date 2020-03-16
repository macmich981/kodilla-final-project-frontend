package com.kodilla.carsfrontend.views;

import com.kodilla.carsfrontend.client.RestCarBrandClient;
import com.kodilla.carsfrontend.domain.CarBrandDto;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class CarBrandView extends VerticalLayout {
    private final RestCarBrandClient restCarBrandClient;
    private final Grid<CarBrandDto> grid = new Grid<>(CarBrandDto.class);

    public CarBrandView(RestCarBrandClient restCarBrandClient) {
        this.restCarBrandClient = restCarBrandClient;
        grid.setColumns("brandName", "constructionYear");
        add(grid);
        setSizeFull();
        refresh();
    }

    public void refresh() {
        grid.setItems(restCarBrandClient.getAllCarBrands());
    }
}
