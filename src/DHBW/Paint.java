package DHBW;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 * Created by Nico on 07.03.2016.
 * Game 2048
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
        //Alle Tiles des Spielfeldes werden gezeichnet bzw verschoben
        System.out.println("paint: Zeichne Feld neu");
        for (int i = 0; i< game.gameSize; i++){
            for (int j = 0; j< game.gameSize; j++){
                if (game.tileArray[i][j] !=  null){
                    if ((game.tileArray[i][j].value != 0) /*&& (canvas.getChildren().contains(game.tileArray[i][j])== true)*/) {
                        paintTile(game.tileArray[i][j], i, j);
                    }
                }



            }
        }
    }

    private void paintTile(Tile tile, int PosX, int PosY){
        //--------------------------------------------------------------------------------------------------------------Tile wurde noch nicht erzeugt
        if (canvas.getChildren().contains(tile) != true)
        {
            // füge die Tile hinzu
            canvas.getChildren().add(tile);
            //System.out.println("paintTile: Tile_ID: " + canvas.getChildren().indexOf(tile));

            tile.setLayoutX((PosX*Tile.tile_size));
            tile.setLayoutY(PosY*Tile.tile_size);
            // füge den Text der Tile hinzu
            canvas.getChildren().add(tile.text);
            //System.out.println("paintTile: Text_ID: " + canvas.getChildren().indexOf(tile.text));
        }

        //--------------------------------------------------------------------------------------------------------------Animation der Tile

        animationMoveTile(tile, PosX, PosY);
        tile.setFill(Color.BLUE);// Farbe der Tile unabhängig vom Value
        //--------------------------------------------------------------------------------------------------------------Layout Text
        //System.out.println("paintTile: Text_ID debug: " + canvas.getChildren().indexOf(tile.text));
        tile.text.setText(String.valueOf(tile.value));
        tile.text.setStroke(Color.BLACK);
        tile.text.setX(PosX*Tile.tile_size+(Tile.tile_size/2)-((tile.text.getText().length())*4));
        tile.text.setY(PosY*Tile.tile_size+(Tile.tile_size/2)+5);
        //--------------------------------------------------------------------------------------------------------------Tile Color abhängig vom Wert
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

    public void deleteTile(int PosX, int PosY){// WIP-> animation richtung stimmt noch nicht!!!

        System.out.println("deleteTile: " + PosX + "; " + PosY);
        if (PosX < game.gameSize && PosY < game.gameSize){

            double moveX = (Tile.tile_size/2);
            double moveY = (Tile.tile_size/2);
            System.out.println("moveX: " + moveX +"moveY: " + moveY);

            double lineX =(PosX*Tile.tile_size)-(game.tileArray[PosX][PosY].getLayoutX())+(Tile.tile_size/2);
            double lineY = ((PosY*Tile.tile_size)-(game.tileArray[PosX][PosY].getLayoutY())+(Tile.tile_size/2));

            Path path = new Path();
            PathTransition pathTransition = new PathTransition();

            // Es wird eine temporäre Tile erzeugt, welche nur auf der canvas-Oberfläche animiert wird, jedoch nicht im Array existiert
            // so wird nicht der weitere Spielverlauf behindert
            Tile tempTile = game.tileArray[PosX][PosY];
            canvas.getChildren().remove(game.tileArray[PosX][PosY].text);
            canvas.getChildren().remove(game.tileArray[PosX][PosY]);
            game.tileArray[PosX][PosY] = null;
            canvas.getChildren().add(tempTile);
            canvas.getChildren().add(tempTile.text);

            // Der Pfad wird festgelegt
                path.getElements().add(new MoveTo(moveX, moveY));
                path.getElements().add(new LineTo(lineX, lineY));
                pathTransition.setPath(path);
                pathTransition.setDuration(Duration.millis(20));
                pathTransition.setNode(tempTile);
                pathTransition.setCycleCount(1);
                pathTransition.play();

            // Bei beenden der Animation wird die temporäre Tile wieder entfernt
            pathTransition.setOnFinished(event -> {
                canvas.getChildren().remove(tempTile);
                canvas.getChildren().remove(tempTile.text);
            });



        }
        else{
            System.out.println("deleteTile: out of line");
        }
    }

    private void animationMoveTile (Tile tile, int PosX, int PosY){
        //------------------------------------------------------Setzen des Startwerts der Animation
        double moveX = (Tile.tile_size/2);
        double moveY = (Tile.tile_size/2);
        //System.out.println("moveX: " + moveX +"moveY: " + moveY);
        //------------------------------------------------------Setzen des Endwertes der Animation
        double lineX =(PosX*Tile.tile_size)-(tile.getLayoutX())+(Tile.tile_size/2);
        double lineY = ((PosY*Tile.tile_size)-(tile.getLayoutY())+(Tile.tile_size/2));
        //System.out.println("lineX: " + lineX +"lineY: " + lineY);
        //------------------------------------------------------Animation wird erzeugt
        Path path = new Path();
        PathTransition pathTransition = new PathTransition();
        // Animation soll nur ausgeführt werden wenn die Tile sich auch bewegt
        if (((moveX == lineX) && (moveY != lineY)) || ((moveY == lineY) && (moveX != lineX))) {


            path.getElements().add(new MoveTo(moveX, moveY));
            path.getElements().add(new LineTo(lineX, lineY));
            pathTransition.setPath(path);
            pathTransition.setDuration(Duration.millis(50));
            pathTransition.setNode(tile);
            pathTransition.setCycleCount(1);
            pathTransition.play();
        }
        //-----------------------------------------------------Animation beendet
        pathTransition.setOnFinished(event -> {
            pathTransition.stop();
            tile.setTranslateX(0);
            tile.setTranslateY(0);
            tile.setLayoutX((PosX*Tile.tile_size));
            tile.setLayoutY(PosY*Tile.tile_size);
        });

    }

}
