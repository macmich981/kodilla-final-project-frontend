package com.kodilla.carsfrontend;

import com.kodilla.carsfrontend.client.RestUserClient;
import com.kodilla.carsfrontend.domain.UserDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route
public class UserView extends VerticalLayout {
    private final Grid<UserDto> grid = new Grid<>(UserDto.class);
    private final TextField filter = new TextField();
    private final RestUserClient restUserClient;
    private final UserForm userForm;
    private final Button addNewUser = new Button("Add new user");
    private boolean newUser;

    public UserView(RestUserClient restUserClient) {
        this.restUserClient = restUserClient;
        userForm = new UserForm(this, restUserClient);
        filter.setPlaceholder("Filter by last name");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        grid.setColumns("firstName", "lastName", "cardIdNumber", "drivingLicenseNumber");

        addNewUser.addClickListener(e -> {
            newUser = true;
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
        grid.setItems(restUserClient.getAllUsers());
    }

    private void update() {
        grid.setItems(restUserClient.getUsersByLastName(filter.getValue()));
    }

    public boolean isNewUser() {
        return newUser;
    }

    public void setNewUser(boolean newUser) {
        this.newUser = newUser;
    }
}
