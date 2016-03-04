package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application implements EventHandler<KeyEvent> {

    private Pane canvas;

    public Game game;

    @Override
    public void start(Stage primaryStage) throws Exception{

        game = new Game();
        canvas = new Pane();
        Scene scene = new Scene(canvas, Game.game_size * Tile.tile_size, Game.game_size * Tile.tile_size);

        primaryStage.setScene(scene);

        //scene.setOnKeyPressed(this);
        scene.setOnKeyPressed(this);
        primaryStage.show();
        paint();

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
                paint();
                break;
            case LEFT:
                game.moveAllTilesLeft();
                paint();
                break;
            case UP:
                game.moveAllTilesUp();
                paint();
                break;
            case DOWN:
                game.moveAllTilesDown();
                paint();
                break;
        }
    }

    public void paint(){
        System.out.println("paint: Zeichne Feld neu");
        for (int i = 0; i< game.game_size; i++){
            for (int j=0; j< game.game_size; j++){
                if (game.TileArray[i][j] !=  null){
                    paintTile(game.TileArray[i][j], i, j);
                }
                else if ((game.TileArray[i][j] == null){
                    canvas.getChildren().remove(game.TileArray[i][j]);
                }

            }
        }
    }

    public void paintTile(Tile tile, int PosX, int PosY){
        if (canvas.getChildren().contains(tile) != true)
        {
            canvas.getChildren().add(tile);
        }
        tile.setStroke(Color.WHITESMOKE);
        tile.setFill(Color.BLUE);
        if (tile.value!=2){
            tile.setFill(Color.RED);
        }

        //System.out.println("paintTile: " + PosX + "; "+ PosY);
        tile.setLayoutX((PosX*Tile.tile_size));
        tile.setLayoutY(PosY*Tile.tile_size);
        //System.out.println("Paint: " + (PosX*Tile.tile_size) + "; "+ PosY*Tile.tile_size);
        //System.out.println(tile.getPosX() + "; "+ tile.getPosY());
    }
}
