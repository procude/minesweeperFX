package mvc.controller;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mvc.model.*;
import org.pmw.tinylog.Logger;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class GameScreenController {

    private static final int TILE_SIZE = 40;
    private static int rowTiles;
    private static int columnTiles;

    private static Tile [][] board;

    private Button back;

    private String userName;

    private ScoreDao scoreDao;

    private Level levelScore;

    private Instant beginGame;

    @FXML
    public void initialize() {
        Injector injector = Guice.createInjector(new PersistenceModule("jpa-persistence-unit-1"));
        scoreDao = injector.getInstance(ScoreDao.class);
    }

    public Parent createContent(Level level, String userName) {

        beginGame = Instant.now();
        levelScore = level;

        Pane root = new Pane();

        root.setPrefSize(level.WIDTH,level.HEIGHT);

        initPlayer(userName);

        initGame(level);
        Logger.info("Játék inicializálása.");

        initGrid(root);

        setMines(level.mines);

        setValues();

        back = new Button("vissza");
        back.setVisible(false);

        return root;
    }

    public void initPlayer(String userName) {
        this.userName = userName;
    }

    public void initGame(Level level) {
        this.rowTiles = level.rowTiles;
        this.columnTiles = level.columnTiles;
        this.board = new Tile[level.rowTiles][level.columnTiles];
    }

    public void initGrid(Pane root){

        for (int y = 0; y < columnTiles; y++) {
            for (int x = 0; x < rowTiles; x++) {
                Tile tile = new Tile();
                setTileProperties(x,y,tile);
                click(tile);
                board[x][y] = tile;
                root.getChildren().add(tile);
            }
        }
    }

    public void setValues(){
        Game game = new Game();
        for (int y = 0; y < columnTiles; y++) {
            for (int x = 0; x < rowTiles; x++) {
                Tile tile = board[x][y];

                if (tile.isMine())
                    continue;

                long bombs = game.getNeighbors(tile, board, levelScore).stream().filter(t -> t.isMine()).count();
                tile.setValue((int)bombs);

                if (bombs > 0) {
                    tile.setValue((int) bombs);
                    tile.getText().setText(String.valueOf(tile.getValue()));

                    switch (tile.getValue()) {
                        case 1: {
                            tile.getText().setStroke(Color.BLUE);
                            break;
                        }
                        case 2: {
                            tile.getText().setStroke(Color.LIGHTGREEN);
                            break;
                        }
                        case 3: {
                            tile.getText().setStroke(Color.RED);
                            break;
                        }
                        case 4: {
                            tile.getText().setStroke(Color.DARKBLUE);
                            break;
                        }
                        case 5: {
                            tile.getText().setStroke(Color.INDIANRED);
                            break;
                        }
                        case 6: {
                            tile.getText().setStroke(Color.DARKCYAN);
                            break;
                        }
                        case 7: {
                            tile.getText().setStroke(Color.DARKRED);
                            break;
                        }
                        case 8: {
                            tile.getText().setStroke(Color.DARKGREEN);
                            break;
                        }
                    }
                }
            }
        }
    }

    public void setMines(int mines){

        Random row = new Random();
        Random column = new Random();
        int mineIndexRow;
        int minIndexColumn;
        int i = 0;
        while (i < mines) {
            mineIndexRow = row.nextInt(rowTiles);
            minIndexColumn = column.nextInt(columnTiles);
            if (board[mineIndexRow][minIndexColumn].isMine()) {
                continue;
            }
            i++;
            board[mineIndexRow][minIndexColumn].setMine(true);
            board[mineIndexRow][minIndexColumn].setPicture(new ImageView(new Image(getClass().getResourceAsStream("/bomb.png"))));
            board[mineIndexRow][minIndexColumn].getPicture().setFitHeight(board[mineIndexRow][minIndexColumn].getTile().getHeight());
            board[mineIndexRow][minIndexColumn].getPicture().setFitWidth(board[mineIndexRow][minIndexColumn].getTile().getWidth());
        }
    }

    private void setTileProperties(int x, int y, Tile tile){
        tile.setX(x);
        tile.setY(y);
        tile.setTile(new Rectangle(TILE_SIZE - 2, TILE_SIZE - 2));
        tile.getTile().setStroke(Color.LIGHTGRAY);
        tile.getTile().setFill(Color.GREY);
        tile.setText(new Text());
        tile.getText().setFont(Font.font(18));
        tile.getText().setVisible(false);
        tile.getChildren().addAll(tile.getTile(), tile.getText());
        tile.setTranslateX(x * TILE_SIZE);
        tile.setTranslateY(y * TILE_SIZE);
    }

    private Score getScore() {

        Score result = new Score(userName, Duration.between(beginGame, Instant.now()),levelScore.name);

        return result;
    }

    private void gameOver() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Vége");
        alert.setHeaderText("Sikertelen!");
        alert.setContentText(
                "Vesztettél. Ha az OK gombra kattintasz új játékot kezdhetsz.");
        alert.showAndWait();
        Logger.info("Játék vége: {}", alert.getContentText());
        if (alert.getResult() == ButtonType.OK) {
            Stage stage = new Stage();
            FXMLLoader newGamefXMLLoader = new FXMLLoader(getClass().getResource("/view/NewGameScreen.fxml"));
            try {
                Parent backRoot = newGamefXMLLoader.load();
                Scene dialogScene = new Scene(backRoot);
                stage.setScene(dialogScene);
                stage.show();
                Stage actualStage = (Stage) board[0][0].getScene().getWindow();
                actualStage.close();
            } catch (IOException e) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                alert.setContentText("Váratlan hiba történt: " + e.getMessage() + "\nKérlek próbáld újra!");
                errorAlert.showAndWait();
                Logger.info("{}", alert.getContentText());
                if (errorAlert.getResult() == ButtonType.OK) {
                    errorAlert.close();
                }
            }
            alert.close();
        } else if (alert.getResult() == ButtonType.CANCEL) {
            alert.close();
            System.exit(0);
        }
    }

    private void win(Tile tile) {
        Game game = new Game();
        game.setRowTiles(rowTiles);
        game.setColumnTiles(columnTiles);
        if (board[tile.getX()][tile.getY()] != null && game.isSolved(board)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Gratulálok");
            alert.setHeaderText("Siker!");
            alert.setContentText("Jól jelölted meg az összes aknát! Vége a játéknak.");
            alert.showAndWait();
            Logger.info("Játék vége: {}", alert.getContentText());
            if (alert.getResult() == ButtonType.OK) {
                Stage stage = new Stage();
                FXMLLoader newGamefXMLLoader = new FXMLLoader(getClass().getResource("/view/NewGameScreen.fxml"));
                try {
                    Parent backRoot = newGamefXMLLoader.load();
                    Scene dialogScene = new Scene(backRoot);
                    stage.setScene(dialogScene);
                    stage.show();
                    Stage actualStage = (Stage) board[0][0].getScene().getWindow();
                    actualStage.close();
                } catch (IOException e) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    alert.setContentText("Váratlan hiba történt: " + e.getMessage() + "\nKérlek próbáld újra!");
                    errorAlert.showAndWait();
                    Logger.info("{}", alert.getContentText());
                    if (errorAlert.getResult() == ButtonType.OK) {
                        errorAlert.close();
                    }
                }
                alert.close();
            }
            scoreDao.persist(getScore());
        }
    }

    public void click(Tile tile){
        tile.setOnMouseClicked(e -> {
            if (!tile.isFlag() && e.getButton().equals(MouseButton.PRIMARY)) {
                open(tile);
            }
            else if (!tile.isFlag() && e.getButton().equals(MouseButton.SECONDARY)){
                mark(tile);
            }
            else if (tile.isFlag() && e.getButton().equals(MouseButton.SECONDARY)){
                mark(tile);
            }
        });
    }

    public void open(Tile tile) {
        Game game = new Game();
        if (tile.isRevealed())
            return;

        if (tile.isMine()) {
            if (tile.isFlag()) {
                tile.getPicture().setImage(null);
            }
            revealBombs();
        }
        else {

            tile.setRevealed(true);
            tile.getText().setVisible(true);
            tile.getTile().setFill(null);
            tile.getChildren().remove(tile.getPicture());
            if (tile.getText().getText().isEmpty()) {
                game.getNeighbors(tile, board, levelScore).forEach(this::open);
            }
            win(tile);
        }
    }

    public void revealBombs(){
        for (int i = 0; i < rowTiles; i++) {
            for (int j = 0; j < columnTiles; j++) {
                if (board[i][j].isFlag()) {
                    board[i][j].getChildren().remove(board[i][j].getPicture());
                    board[i][j].getPicture().setImage(new Image(getClass().getResourceAsStream("/bomb.png")));
                }
                if (board[i][j].isMine()) {
                    board[i][j].getPicture().setVisible(true);
                    board[i][j].getChildren().add(board[i][j].getPicture());
                }
            }
        }
        gameOver();
        scoreDao.remove(getScore());
    }

    public void mark(Tile tile){
        if (tile.isRevealed()) {
            return;
        }
        if (tile.isMine()) {
            if (tile.isFlag()) {
                tile.setFlag(false);
                tile.getChildren().remove(tile.getPicture());
                tile.getPicture().setImage(new Image(getClass().getResourceAsStream("/bomb.png")));
            } else {
                tile.setFlag(true);
                tile.setPicture(new ImageView(new Image(getClass().getResourceAsStream("/flag.png"))));
                tile.getPicture().setFitWidth(tile.getTile().getWidth());
                tile.getPicture().setFitHeight(tile.getTile().getHeight());
                tile.getPicture().setVisible(true);
                tile.getChildren().add(tile.getPicture());
            }
        }
        else {
            if (tile.isFlag()) {
                tile.setFlag(false);
                tile.getChildren().remove(tile.getPicture());
                tile.getPicture().setImage(null);
            } else {
                tile.setFlag(true);
                tile.setPicture(new ImageView(new Image(getClass().getResourceAsStream("/flag.png"))));
                tile.getPicture().setFitWidth(tile.getTile().getWidth());
                tile.getPicture().setFitHeight(tile.getTile().getHeight());
                tile.getPicture().setVisible(true);
                tile.getChildren().add(tile.getPicture());
            }
        }
        win(tile);
    }
}
