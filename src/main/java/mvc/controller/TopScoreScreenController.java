package mvc.controller;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mvc.model.PersistenceModule;
import mvc.model.Score;
import mvc.model.ScoreDao;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.pmw.tinylog.Logger;

import java.io.IOException;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TopScoreScreenController {

    private ScoreDao scoreDao;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Score> toptenTable;

    @FXML
    private TableColumn<Score, String> player;

    @FXML
    private TableColumn<Score, Duration> duration;

    @FXML
    private TableColumn<Score, String> level;

    @FXML
    private TableColumn<Score, ZonedDateTime> created;

    @FXML
    public void startScreen(ActionEvent actionEvent) throws IOException {
        Logger.info("A TopScoresScreen betöltve.");
        Stage stage = new Stage();
        Parent root;
        FXMLLoader startGamefXMLLoader = new FXMLLoader(getClass().getResource("/view/StartScreen.fxml"));
        root = startGamefXMLLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Aknakereső");
        Logger.info("Visszalépés a StartScreenre.");
        Stage actualStage = (Stage) backButton.getScene().getWindow();
        actualStage.close();
    }


    @FXML
    public void initialize() {
        Injector injector = Guice.createInjector(new PersistenceModule("jpa-persistence-unit-1"));
        scoreDao = injector.getInstance(ScoreDao.class);

        List<Score> toptenList = scoreDao.findBest(20);

        player.setCellValueFactory(new PropertyValueFactory<>("player"));
        level.setCellValueFactory(new PropertyValueFactory<>("level"));
        duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        created.setCellValueFactory(new PropertyValueFactory<>("created"));

        duration.setCellFactory(column -> {
            TableCell<Score, Duration> cell = new TableCell<Score, Duration>() {

                @Override
                protected void updateItem(Duration item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        setText(DurationFormatUtils.formatDuration(item.toMillis(),"H:mm:ss:SS"));
                    }
                }
            };

            return cell;
        });

        created.setCellFactory(column -> {
            TableCell<Score, ZonedDateTime> cell = new TableCell<Score, ZonedDateTime>() {
                private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm:ss Z");

                @Override
                protected void updateItem(ZonedDateTime item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        setText(item.format(formatter));
                    }
                }
            };

            return cell;
        });

        ObservableList<Score> observableResult = FXCollections.observableArrayList();
        observableResult.addAll(toptenList);

        toptenTable.setItems(observableResult);
    }

}
