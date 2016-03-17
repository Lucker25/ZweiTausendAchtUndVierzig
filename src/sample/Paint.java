package sample;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 * Created by Nico on 07.03.2016.
 */
public class Paint {

    public Pane canvas;
    private Game game;

    public Paint(Game game) {
        this.game = game;
        canvas = new Pane();
        Path path = new Path();

    }



    public void paint(){

        System.out.println("paint: Zeichne Feld neu");
        for (int i = 0; i< game.game_size; i++){
            for (int j=0; j< game.game_size; j++){
                if (game.TileArray[i][j] !=  null){
                    if ((game.TileArray[i][j].value != 0) /*&& (canvas.getChildren().contains(game.TileArray[i][j])== true)*/) {
                        paintTile(game.TileArray[i][j], i, j);
                    }
                }



            }
        }
    }

    public void paintTile(Tile tile, int PosX, int PosY){

        if (canvas.getChildren().contains(tile) != true)
        {

            canvas.getChildren().add(tile);
            System.out.println("paintTile: Tile_ID: " + canvas.getChildren().indexOf(tile));
            game.TileArray[PosX][PosY].ID_Tile = canvas.getChildren().indexOf(tile);



            tile.setLayoutX((PosX*Tile.tile_size));
            tile.setLayoutY(PosY*Tile.tile_size);
            //tile.CanvasPosX = PosX*Tile.tile_size;
            //tile.CanvasPosY = PosY*Tile.tile_size;



            canvas.getChildren().add(tile.text);
            System.out.println("paintTile: Text_ID: " + canvas.getChildren().indexOf(tile.text));
            game.TileArray[PosX][PosY].ID_Text = canvas.getChildren().indexOf(tile.text);
        }

        //--------------------------------------------------------------------------------------------------------------Layout Tile

        animationMoveTile(tile, PosX, PosY);


        tile.setFill(Color.BLUE);
        //tile.setLayoutX((PosX*Tile.tile_size));
        //tile.setLayoutY(PosY*Tile.tile_size);

        //System.out.println(tile.getLayoutX() + " " + tile.getX() + " "  + tile.getLayoutY() + " " + tile.getY());

        //--------------------------------------------------------------------------------------------------------------Layout Text
        System.out.println("paintTile: Text_ID debug: " + canvas.getChildren().indexOf(tile.text));
        tile.text.setText(String.valueOf(tile.value));
        tile.text.setStroke(Color.BLACK);
        //System.out.println(tile.text.getText().toString().length());
        tile.text.setX(PosX*Tile.tile_size+(Tile.tile_size/2)-((tile.text.getText().toString().length())*4));
        tile.text.setY(PosY*Tile.tile_size+(Tile.tile_size/2)+5);
        //--------------------------------------------------------------------------------------------------------------Tile Color
        switch (tile.value){
            case 2048:
                tile.setFill(Color.GOLD);
                break;
            case 1024:
                tile.setFill(Color.SILVER);
                break;
            case 512:
                tile.setFill(Color.YELLOW);
                break;
            case 256:
                tile.setFill(Color.ORANGE);
                break;
            case 128:
                tile.setFill(Color.RED);
                break;
            case 64:
                tile.setFill(Color.CHOCOLATE);
                break;
            case 32:
                tile.setFill(Color.CADETBLUE);
                break;
            case 16:
                tile.setFill(Color.BROWN);
                break;
            case 8:
                tile.setFill(Color.BLUEVIOLET);
                break;
            case 4:
                tile.setFill(Color.AQUAMARINE);
                break;
            case 2:
                tile.setFill(Color.BLUE);
                break;

        }
    }

    public void deleteTile(int PosX, int PosY){


        if (PosX < game.game_size && PosY < game.game_size) {
            System.out.println("deleteTile: " + PosX + "; " + PosY);
            canvas.getChildren().remove(game.TileArray[PosX][PosY]);
            canvas.getChildren().remove(game.TileArray[PosX][PosY].text);
            game.TileArray[PosX][PosY] = null;
        }
        else{
            System.out.println("deleteTile: out of line");
        }
    }

    public void animationMoveTile (Tile tile, int PosX, int PosY){

        double MoveX = (Tile.tile_size/2);
        double MoveY = (Tile.tile_size/2);
        System.out.println("MoveX: " + MoveX +"MoveY: " + MoveY);

        double LineX =(PosX*Tile.tile_size)-(tile.getLayoutX())+(Tile.tile_size/2);
        double LineY = ((PosY*Tile.tile_size)-(tile.getLayoutY())+(Tile.tile_size/2));
        System.out.println("LineX: " + LineX +"LineY: " + LineY);
        Path path = new Path();
        PathTransition pathTransition = new PathTransition();

        if (((MoveX == LineX) && (MoveY != LineY)) || ((MoveY == LineY) && (MoveX != LineX))) {


            path.getElements().add(new MoveTo(MoveX, MoveY));
            path.getElements().add(new LineTo(LineX, LineY));
            tile.path = path;
            pathTransition.setPath(tile.path);
            pathTransition.setDuration(Duration.millis(50));
            pathTransition.setNode(tile);
            pathTransition.setCycleCount(1);
            pathTransition.play();
        }

        pathTransition.setOnFinished(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                tile.path = null;
                pathTransition.stop();
                tile.setTranslateX(0);
                tile.setTranslateY(0);
                tile.setLayoutX((PosX*Tile.tile_size));
                tile.setLayoutY(PosY*Tile.tile_size);
            }

        });

    }

   /* public void animationDeleteTile (int PosX, int PosY){

        double MoveX = (Tile.tile_size/2);
        double MoveY = (Tile.tile_size/2);
        System.out.println("MoveX: " + MoveX +"MoveY: " + MoveY);



        double LineX =(PosX*Tile.tile_size)-(game.TileArray[PosX][PosY].getLayoutX())+(Tile.tile_size/2);
        double LineY = ((PosY*Tile.tile_size)-(game.TileArray[PosX][PosY].getLayoutY())+(Tile.tile_size/2));
        System.out.println("LineX: " + LineX +"LineY: " + LineY);
        Path path = new Path();
        PathTransition pathTransition = new PathTransition();

        if (((MoveX == LineX) && (MoveY != LineY)) || ((MoveY == LineY) && (MoveX != LineX))) {


            path.getElements().add(new MoveTo(MoveX, MoveY));
            path.getElements().add(new LineTo(LineX, LineY));
            pathTransition.setPath(path);
            pathTransition.setDuration(Duration.millis(50));
            pathTransition.setNode(game.TileArray[PosX][PosY]);
            pathTransition.setCycleCount(1);
            pathTransition.play();
            if (PosX < game.game_size && PosY < game.game_size) {
                System.out.println("deleteTile: " + PosX + "; " + PosY);
                canvas.getChildren().remove(game.TileArray[PosX][PosY]);
                canvas.getChildren().remove(game.TileArray[PosX][PosY].text);
                game.TileArray[PosX][PosY] = null;
            }
            else{
                System.out.println("deleteTile: out of line");
            }
        }

        pathTransition.setOnFinished(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                game.TileArray[PosX][PosY].path = null;
                pathTransition.stop();
                game.TileArray[PosX][PosY].setTranslateX(0);
                game.TileArray[PosX][PosY].setTranslateY(0);
                game.TileArray[PosX][PosY].setLayoutX((PosX*Tile.tile_size));
                game.TileArray[PosX][PosY].setLayoutY(PosY*Tile.tile_size);

            }

        });

    }*/

}
