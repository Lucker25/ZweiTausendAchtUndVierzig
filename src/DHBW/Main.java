package DHBW;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.Optional;

import static DHBW.Game.GameState;

public class Main extends Application implements EventHandler<KeyEvent> {


    private static boolean game_continued = false;
    public Game game;

    @Override
    public void start(Stage primaryStage) throws Exception{

        game = new Game();
        Scene scene = new Scene(game.paint.canvas, game.gameSize * Tile.tile_size, game.gameSize * Tile.tile_size);
        primaryStage.setScene(scene);

        //scene.setOnKeyPressed(this);
        scene.setOnKeyPressed(this);
        primaryStage.show();
        game.paint.paint();

    }



    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void handle(KeyEvent event) {
        KeyCode code = event.getCode();

        switch (code) {
            case RIGHT:
                game.moveAllTilesRight();
                gameHandler();

                break;
            case LEFT:
                game.moveAllTilesLeft();
                gameHandler();
                break;
            case UP:
                game.moveAllTilesUp();
                gameHandler();
                break;
            case DOWN:
                game.moveAllTilesDown();
                gameHandler();
                break;
            case S:
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Score Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Score: " + game.getScore());
                alert.showAndWait();

        }
    }

    //Überprüft den Spielablauf
    private void gameHandler(){
        // Spiel läuft
        GameState check = game.lostWinCheck();
        if ((check == GameState.GO_ON) || ((check == GameState.WON) && (game_continued))){

            game.generateRandomTile();
            game.paint.paint();

        }
        // Spiel verloren
        else if (check == GameState.LOST){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Score Dialog");
            alert.setHeaderText(null);
            alert.setContentText("You`ve lost!! \r\nYour Score : " + game.getScore() + " \n\rNew Game?");
            Optional<ButtonType> result = alert.showAndWait();
            //int result = JOptionPane.showConfirmDialog(null, "You`ve lost!! \r\nYour Score : " + game.getScore() + " \n\rNew Game?", "LOST", JOptionPane.YES_NO_CANCEL_OPTION);
            if (result.get() == ButtonType.OK ) {
                game.newGame();
                //System.exit(0);
            } else if (result.get() == ButtonType.CANCEL) {
                System.exit(0);
            }

        }
        //Spiel gewonnen
        else if ((check == GameState.WON) && (game_continued == false)){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Score Dialog");
            alert.setHeaderText(null);
            alert.setContentText("You`ve WON!!\r\nCongratulations!!!! \r\nYour Score : " + game.getScore() + " \n\rNew Game?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK ) {
                game.newGame();

            } else if (result.get() == ButtonType.CANCEL) {

                game_continued = true;
                game.generateRandomTile();
                game.paint.paint();
            }
        }



    }


    public static void unsetGameContinued(){
        game_continued = false;
    }

    public static boolean getGameContinued(){
        return game_continued;
    }








}
