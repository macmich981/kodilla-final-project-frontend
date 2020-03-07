package com.kodilla.carsfrontend;

import com.kodilla.carsfrontend.client.RestClient;
import com.kodilla.carsfrontend.domain.UserDto;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {
    private final Grid<UserDto> grid = new Grid<>(UserDto.class);
    private TextField filter = new TextField();
    private final RestClient restClient;

    public MainView(RestClient restClient) {
        this.restClient = restClient;
        filter.setPlaceholder("Filter by last name");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        add(filter, grid);
        grid.setColumns("firstName", "lastName", "cardIdNumber", "drivingLicenseNumber");
        add(grid);
        setSizeFull();
        refresh();
    }

    public void refresh() {
        grid.setItems(restClient.getAllUsers());
    }

    private void update() {
        grid.setItems(restClient.getUsersByLastName(filter.getValue()));
    }
}
