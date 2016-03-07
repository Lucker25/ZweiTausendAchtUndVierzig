package sample;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Created by Nico on 07.03.2016.
 */
public class Paint {

    public Pane canvas;
    private Game game;

    public Paint(Game game) {
        this.game = game;
        canvas = new Pane();
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
                /*if (game.TileArray[i][j] !=  null) {
                    if ((game.TileArray[i][j].value == 0) /*&& (canvas.getChildren().contains(game.TileArray[i][j])== true)) {
                        canvas.getChildren().remove(game.TileArray[i][j].ID);
                        game.TileArray[i][j] = null;
                    }
                }*/
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

    public void deleteTile(int PosX, int PosY){
        if (PosX < game.game_size && PosY < game.game_size) {
            System.out.println("deleteTile: " + PosX + "; " + PosY);
            System.out.println("deleteTile: ID " + (game.TileArray[PosX][PosY].ID - 1));
            System.out.println("deleteTile: ID " + (canvas.getId().));
            canvas.getChildren().remove(game.TileArray[PosX][PosY].ID );
            game.TileArray[PosX][PosY] = null;
        }
        else{
            System.out.println("deleteTile: out of line");
            return;
        }
    }
}
