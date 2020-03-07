package com.kodilla.carsfrontend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kodilla.carsfrontend.client.RestClient;
import com.kodilla.carsfrontend.domain.UserDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class UserForm extends FormLayout {
    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private TextField cardIdNumber = new TextField("Card ID number");
    private TextField drivingLicenseNumber = new TextField("Driving license number");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Binder<UserDto> binder = new Binder<>(UserDto.class);
    private RestClient restClient;
    private MainView mainView;

    public UserForm(MainView mainView, RestClient restClient) {
        this.mainView = mainView;
        this.restClient = restClient;
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(firstName, lastName, cardIdNumber, drivingLicenseNumber, buttons);
        binder.bindInstanceFields(this);
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
    }

    private void save() {
        UserDto userDto = binder.getBean();
        try {
            restClient.addUser(userDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        mainView.refresh();
        setUserDto(null);
    }

    private void delete() {
        UserDto userDto = binder.getBean();
        restClient.deleteUser(userDto.getId());
        mainView.refresh();
        setUserDto(null);
    }

    public void setUserDto(UserDto userDto) {
        binder.setBean(userDto);

        if (userDto == null) {
            setVisible(false);
        } else {
            setVisible(true);
            firstName.focus();
        }
    }
}
