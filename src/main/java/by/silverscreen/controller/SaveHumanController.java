package by.silverscreen.controller;

import by.silverscreen.model.wrapper.HumanWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.xml.bind.ValidationException;
import java.time.LocalDate;
import java.util.Objects;

import static javafx.scene.control.Alert.*;

public class SaveHumanController {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField ageTextField;
    @FXML
    private DatePicker birthdayDatePicker;

    private TreeItem<HumanWrapper> root;

    private HumanWrapper human;

    public void setRoot(TreeItem<HumanWrapper> root) {
        this.root = root;
    }

    public void setHumanWrapper(HumanWrapper human) {
        this.human = human;
        initSaveForm();
    }

    @FXML
    public void OnSave() {
        try {
            if (this.human == null) {
                addHumanWrapper();
            } else {
                editHumanWrapper();
            }
            OnCancel();
        } catch (NullPointerException | ValidationException e) {
            AlertGenerator.showAlert(AlertType.ERROR, "Save", "Save error", e.getMessage());
        }
    }

    @FXML
    public void OnCancel() {
        Stage stage = (Stage) this.anchorPane.getScene().getWindow();
        stage.close();
    }

    private void addHumanWrapper() throws ValidationException {
        if (isValidFields()) {
            final HumanWrapper human = new HumanWrapper(
                    this.nameTextField.getText(),
                    Integer.valueOf(this.ageTextField.getText()),
                    this.birthdayDatePicker.getValue()
            );
            final TreeItem<HumanWrapper> treeItem = new TreeItem<>(human);
            Objects.requireNonNull(this.root, "Property root cannot be null").getChildren().add(treeItem);
        } else {
            throw new ValidationException("Not valid some fields");
        }
    }

    private void editHumanWrapper() throws ValidationException {
        if (isValidFields()) {
            final HumanWrapper human = Objects.requireNonNull(this.human, "Property human cannot be null");
            human.setName(this.nameTextField.getText());
            human.setAge(Integer.valueOf(this.ageTextField.getText()));
            human.setBirthday(this.birthdayDatePicker.getValue());
        } else {
            throw new ValidationException("Not valid some fields");
        }
    }

    private void initSaveForm() {
        if (human == null) {
            initFields("", "", LocalDate.now());
        } else {
            initFields(this.human.getName(), String.valueOf(this.human.getAge()), this.human.getBirthday());
        }
    }

    private void initFields(String name, String age, LocalDate birthday) {
        this.nameTextField.setText(name);
        this.ageTextField.setText(age);
        this.birthdayDatePicker.setValue(birthday);
    }

    private boolean isValidFields() {
        boolean validity = true;
        try {
            int age = Integer.parseInt(this.ageTextField.getText());
            if (age < 1) {
                validity = false;
            }

            // TODO: Change birthday validation
            if (this.birthdayDatePicker.getValue().isAfter(LocalDate.now().minusYears(age))) {
                validity = false;
            }
        } catch (NumberFormatException e) {
            validity = false;
        }
        return validity;
    }
}
