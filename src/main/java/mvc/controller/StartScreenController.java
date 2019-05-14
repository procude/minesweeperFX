package mvc.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class StartScreenController {

    @FXML
    private Button newGame;
    @FXML
    private Button topScores;

    @FXML
    public void newGameScreen(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root;
        FXMLLoader newGamefXMLLoader = new FXMLLoader(getClass().getResource("/view/NewGameScreen.fxml"));
        root = newGamefXMLLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Új játék");
        Stage actualStage = (Stage) newGame.getScene().getWindow();
        actualStage.close();
    }

    @FXML
    public void topScoreScreen(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root;
        FXMLLoader topScorefXMLLoader = new FXMLLoader(getClass().getResource("/view/TopScoreScreen.fxml"));
        root = topScorefXMLLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Toplista");
        Stage actualStage = (Stage) topScores.getScene().getWindow();
        actualStage.close();
    }


    @FXML
    private void handleCloseGame(){
        System.exit(0);
    }

}
