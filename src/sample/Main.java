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
                game.generateRandomTile();
                game.paint.paint();
                break;
            case LEFT:
                game.moveAllTilesLeft();
                game.generateRandomTile();
                game.paint.paint();
                //game.resetTurn();
                break;
            case UP:
                game.moveAllTilesUp();
                game.generateRandomTile();
                game.paint.paint();
                break;
            case DOWN:
                game.moveAllTilesDown();
                game.generateRandomTile();
                game.paint.paint();
                break;
        }
    }




}
