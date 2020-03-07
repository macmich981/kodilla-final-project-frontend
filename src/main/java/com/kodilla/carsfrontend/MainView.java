package com.kodilla.carsfrontend;

import com.kodilla.carsfrontend.client.RestClient;
import com.kodilla.carsfrontend.domain.UserDto;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {
    private final Grid<UserDto> grid = new Grid<>(UserDto.class);
    private final RestClient restClient;

    public MainView(RestClient restClient) {
        this.restClient = restClient;
        grid.setColumns("firstName", "lastName", "cardId", "drivingLicense");
        add(grid);
        setSizeFull();
        refresh();
    }

    public void refresh() {
        grid.setItems(restClient.getAllUsers());
    }
}
