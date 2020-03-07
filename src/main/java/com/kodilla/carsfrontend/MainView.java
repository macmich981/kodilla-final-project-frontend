package com.kodilla.carsfrontend;

import com.kodilla.carsfrontend.client.RestClient;
import com.kodilla.carsfrontend.domain.UserDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {
    private final Grid<UserDto> grid = new Grid<>(UserDto.class);
    private final TextField filter = new TextField();
    private final RestClient restClient;
    private final UserForm userForm;
    private Button addNewUser = new Button("Add new user");

    public MainView(RestClient restClient) {
        this.restClient = restClient;
        userForm = new UserForm(this, restClient);
        filter.setPlaceholder("Filter by last name");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        grid.setColumns("firstName", "lastName", "cardIdNumber", "drivingLicenseNumber");

        addNewUser.addClickListener(e -> {
            grid.asSingleSelect().clear();
            userForm.setUserDto(new UserDto());
        });
        HorizontalLayout toolbar = new HorizontalLayout(filter, addNewUser);

        HorizontalLayout mainContent = new HorizontalLayout(grid, userForm);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        userForm.setUserDto(null);
        setSizeFull();
        refresh();

        grid.asSingleSelect().addValueChangeListener(event -> userForm.setUserDto(grid.asSingleSelect().getValue()));
    }

    public void refresh() {
        grid.setItems(restClient.getAllUsers());
    }

    private void update() {
        grid.setItems(restClient.getUsersByLastName(filter.getValue()));
    }
}
