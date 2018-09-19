package by.silverscreen.controller;

import by.silverscreen.App;
import by.silverscreen.model.Human;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static javafx.scene.control.Alert.*;

public class HumanController implements Initializable {

    @FXML
    private TreeTableView<Human> humanTreeTableView;
    @FXML
    private TreeTableColumn<Human, String> nameTreeTableColumn;
    @FXML
    private TreeTableColumn<Human, Number> ageTreeTableColumn;
    @FXML
    private TreeTableColumn<Human, LocalDate> birthdayTreeTableColumn;

    private TreeItem<Human> rootTreeItem = new TreeItem<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.humanTreeTableView.setRoot(this.rootTreeItem);

        this.nameTreeTableColumn.setCellValueFactory(v -> v.getValue().getValue().nameProperty());
        this.ageTreeTableColumn.setCellValueFactory(v -> v.getValue().getValue().ageProperty());
        this.birthdayTreeTableColumn.setCellValueFactory(v -> v.getValue().getValue().birthdayProperty());

        this.rootTreeItem.getChildren().add(new TreeItem<>(new Human("111", 2, LocalDate.now())));
        this.rootTreeItem.getChildren().add(new TreeItem<>(new Human("222", 3, LocalDate.now())));
    }

    @FXML
    public void OnAdd() {
        try {
            URL url = App.class.getClassLoader().getResource("save.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);

            SaveHumanController controller = fxmlLoader.getController();
            controller.setRoot(this.rootTreeItem);
            controller.setHuman(null);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Add");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void OnEdit() {
        try {
            final int index = this.humanTreeTableView.getSelectionModel().getSelectedIndex();
            URL url = App.class.getClassLoader().getResource("save.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);

            SaveHumanController controller = fxmlLoader.getController();
            controller.setRoot(this.rootTreeItem);
            controller.setHuman(this.rootTreeItem.getChildren().get(index).getValue());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Edit");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            AlertGenerator.showAlert(AlertType.WARNING, "Edit", "No human selected", "Please, select a human in the table");
        }
    }

    @FXML
    public void OnDelete() {
        try {
            final int index = this.humanTreeTableView.getSelectionModel().getSelectedIndex();
            this.rootTreeItem.getChildren().remove(index);
        } catch (IndexOutOfBoundsException e) {
            AlertGenerator.showAlert(AlertType.WARNING, "Delete", "No human selected", "Please, select a human in the table");
        }
    }
}
