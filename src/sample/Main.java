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
        Scene scene = new Scene(game.paint.canvas, Game.game_size * Tile.tile_size, Game.game_size * Tile.tile_size);

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
                game.paint.paint();
                break;
            case LEFT:
                game.moveAllTilesLeft();
                game.paint.paint();
                break;
            case UP:
                game.moveAllTilesUp();
                game.paint.paint();
                break;
            case DOWN:
                game.moveAllTilesDown();
                game.paint.paint();
                break;
        }
    }




}
