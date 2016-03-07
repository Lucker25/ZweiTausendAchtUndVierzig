package sample;

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
                        canvas.getChildren().remove(game.TileArray[i][j].ID_Tile);
                        game.TileArray[i][j] = null;
                    }
                }*/
            }
        }
    }

    public void paintTile(Tile tile, int PosX, int PosY){
        System.out.println(canvas.getChildren().contains(tile) != true);
        if (canvas.getChildren().contains(tile) != true)
        {

            canvas.getChildren().add(tile);
            System.out.println("paintTile: Tile_ID: " + canvas.getChildren().indexOf(tile));
            game.TileArray[PosX][PosY].ID_Tile = canvas.getChildren().indexOf(tile);

            canvas.getChildren().add(tile.text);
            System.out.println("paintTile: Text_ID: " + canvas.getChildren().indexOf(tile.text));
            game.TileArray[PosX][PosY].ID_Text = canvas.getChildren().indexOf(tile.text);
        }

        //--------------------------------------------------------------------------------------------------------------Layout Tile
        tile.setStroke(Color.WHITESMOKE);
        tile.setFill(Color.BLUE);
        tile.setLayoutX((PosX*Tile.tile_size));
        tile.setLayoutY(PosY*Tile.tile_size);

        //--------------------------------------------------------------------------------------------------------------Layout Text
        tile.text.setText(String.valueOf(tile.value));
        tile.text.setStroke(Color.BLACK);
        tile.text.setX(PosX*Tile.tile_size+(Tile.tile_size/2));
        tile.text.setY(PosY*Tile.tile_size+(Tile.tile_size/2));

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
            System.out.println("deleteTile: ID_Tile " + (game.TileArray[PosX][PosY].ID_Tile));
            System.out.println("deleteTile: ID_Text " + (game.TileArray[PosX][PosY].ID_Text));


            canvas.getChildren().remove(game.TileArray[PosX][PosY].ID_Tile);
            canvas.getChildren().remove(game.TileArray[PosX][PosY].ID_Text);
            for (int i = 0; i< game.game_size; i++) {
                for (int j = 0; j < game.game_size; j++) {
                    if (game.TileArray[i][j] !=  null){
                        if (game.TileArray[i][j].ID_Tile >= game.TileArray[PosX][PosY].ID_Tile){
                            game.TileArray[i][j].ID_Tile--;
                        }
                        if (game.TileArray[i][j].ID_Tile >= game.TileArray[PosX][PosY].ID_Text){
                            game.TileArray[i][j].ID_Tile--;
                        }
                        if (game.TileArray[i][j].ID_Text >= game.TileArray[PosX][PosY].ID_Tile){
                            game.TileArray[i][j].ID_Text--;
                        }
                        if (game.TileArray[i][j].ID_Text >= game.TileArray[PosX][PosY].ID_Text){
                            game.TileArray[i][j].ID_Text--;
                        }
                        System.out.println("deleteTile: neu ID_Tile " + (game.TileArray[i][j].ID_Tile));
                        System.out.println("deleteTile: neu ID_Text " + (game.TileArray[i][j].ID_Text));
                    }

                }
            }
            game.TileArray[PosX][PosY] = null;
        }
        else{
            System.out.println("deleteTile: out of line");
            return;
        }
    }
}
