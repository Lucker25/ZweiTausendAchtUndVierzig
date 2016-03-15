package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<KeyEvent> {



    public Game game;

    @Override
    public void start(Stage primaryStage) throws Exception{



        game = new Game();
        Scene scene = new Scene(game.paint.canvas, game.game_size * Tile.tile_size, game.game_size * Tile.tile_size);

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
        }
    }

    private void gameHandler(){
        // Spiel läuft
        if (game.lostWinCheck() == 0){
            game.generateRandomTile();
            game.paint.paint();

        }
        // Spiel verloren
        else if (game.lostWinCheck() == 1){

        }
        //Spiel gewonnen
        else if (game.lostWinCheck() == 2){
            game.generateRandomTile();
            game.paint.paint();
        }


    }




}
