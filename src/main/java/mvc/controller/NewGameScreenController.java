package mvc.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import mvc.model.Level;

import java.io.IOException;

public class NewGameScreenController {

    @FXML
    private Button playButton;
    @FXML
    private Button backButton;
    @FXML
    private TextField userNameTextfield;
    @FXML
    private RadioButton easyRadioButton;
    @FXML
    private RadioButton mediumRadioButton;

    Level level;

    @FXML
    public void gameScreen(ActionEvent actionEvent) throws IOException {
        Stage stage;
        Parent root;

        if (easyRadioButton.isSelected()) {
            level = level.EASY;
        } else if (mediumRadioButton.isSelected()) {
            level = level.MEDIUM;
        } else {
            level = level.HARD;
        }

        if (userNameTextfield.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hiba");
            alert.setHeaderText("Sikertelen indítás!");
            alert.setContentText("Adjon meg egy játékosnevet!");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                alert.close();
            }
        }
        else {

            FXMLLoader gamefXMLLoader = new FXMLLoader(getClass().getResource("/view/GameScreen.fxml"));
            gamefXMLLoader.load();
            root = gamefXMLLoader.<GameScreenController>getController().createContent(level, userNameTextfield.getText());
            stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            stage.setTitle("Aknakereső");
            Stage actualStage = (Stage) playButton.getScene().getWindow();
            actualStage.close();
        }
    }

    @FXML
    public void startScreen(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root;
        FXMLLoader startGamefXMLLoader = new FXMLLoader(getClass().getResource("/view/StartScreen.fxml"));
        root = startGamefXMLLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Aknakereső");
        Stage actualStage = (Stage) backButton.getScene().getWindow();
        actualStage.close();
    }
}
