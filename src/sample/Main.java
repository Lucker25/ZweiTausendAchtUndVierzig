package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javax.swing.*;

public class Main extends Application implements EventHandler<KeyEvent> {


    private static boolean game_continued = false;
    public Game game;

    @Override
    public void start(Stage primaryStage) throws Exception{

        game = new Game();
        Scene scene = new Scene(game.paint.canvas, game.game_size * Tile.tile_size, game.game_size * Tile.tile_size + 100);
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
                alert.setContentText("Score: " + game.score);
                alert.showAndWait();

        }
    }

    //Überprüft den Spielablauf
    private void gameHandler(){
        // Spiel läuft
        int check = game.lostWinCheck();
        if ((check == game.GO_ON) || ((check == game.WON) && (game_continued))){

            game.generateRandomTile();
            game.paint.paint();

        }
        // Spiel verloren
        else if (check == game.LOST){
            int result = JOptionPane.showConfirmDialog(null, "You`ve lost!! \r\nYour Score : " + game.score + " \n\rNew Game?", "LOST", JOptionPane.YES_NO_CANCEL_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                game.newGame();
                //System.exit(0);
            } else if (result == JOptionPane.NO_OPTION) {
                System.exit(0);
            }

        }
        //Spiel gewonnen
        else if ((check == game.WON) && (game_continued == false)){
            int result = JOptionPane.showConfirmDialog(null, "You`ve WON!! Congratulations!!! \r\nYour Score : "+ game.score+" \n\rNew Game?", "WON", JOptionPane.YES_NO_CANCEL_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                game.newGame();
                //System.exit(0);
            } else if (result == JOptionPane.NO_OPTION) {
                game_continued = true;
                game.generateRandomTile();
                game.paint.paint();
            }


        }



    }


    public static void unsetGameContinued(){
        game_continued = false;
    }








}
