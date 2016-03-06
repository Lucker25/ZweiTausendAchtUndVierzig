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
        canvas.getChildren().removeAll();
        for (int i = 0; i< game.game_size; i++){
            for (int j=0; j< game.game_size; j++){
                if (game.TileArray[i][j] !=  null){
                    if ((game.TileArray[i][j].value != 0) /*&& (canvas.getChildren().contains(game.TileArray[i][j])== true)*/) {
                        paintTile(game.TileArray[i][j], i, j);
                    }
                }
                if (game.TileArray[i][j] !=  null) {
                    if ((game.TileArray[i][j].value == 0) /*&& (canvas.getChildren().contains(game.TileArray[i][j])== true)*/) {
                        canvas.getChildren().remove(game.TileArray[i][j].ID);
                        game.TileArray[i][j] = null;
                    }
                }
            }
        }
    }

    public void paintTile(Tile tile, int PosX, int PosY){
        if (canvas.getChildren().contains(tile) != true)
        {
            canvas.getChildren().add(tile);
            game.TileArray[PosX][PosY].ID = canvas.getChildren().indexOf(tile);
            //System.out.print("ID: " + game.TileArray[PosX][PosY].ID);
        }
        tile.setStroke(Color.WHITESMOKE);
        tile.setFill(Color.BLUE);

        //System.out.println("paintTile: " + PosX + "; "+ PosY);
        tile.setLayoutX((PosX*Tile.tile_size));
        tile.setLayoutY(PosY*Tile.tile_size);
        if (tile.value >2 ){
            tile.setFill(Color.RED);
        }
        if (tile.value > 4){
            tile.setFill(Color.GREEN);
        }

        //System.out.println("Paint: " + (PosX*Tile.tile_size) + "; "+ PosY*Tile.tile_size);
        //System.out.println(tile.getPosX() + "; "+ tile.getPosY());
    }
    public void removeTile(Tile tile){
        canvas.getChildren().remove(tile);
    }
}
