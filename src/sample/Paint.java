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

        if (game.TileArray[PosX][PosY].ID_Tile == -1)
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
        System.out.println("paintTile: Text_ID debug: " + canvas.getChildren().indexOf(tile.text));
        tile.text.setText(String.valueOf(tile.value));
        tile.text.setStroke(Color.BLACK);
        tile.text.setX(PosX*Tile.tile_size+(Tile.tile_size/2)-2);
        tile.text.setY(PosY*Tile.tile_size+(Tile.tile_size/2)+2);

        if (tile.value > 1024 ){
            tile.setFill(Color.GOLD);
        }
        else if (tile.value > 512){
            tile.setFill(Color.SILVER);
        }
        else if (tile.value > 256){
            tile.setFill(Color.YELLOW);
        }
        else if (tile.value > 128){
            tile.setFill(Color.ORANGE);
        }
        else if (tile.value > 64){
            tile.setFill(Color.RED);
        }
        else if (tile.value > 32){
            tile.setFill(Color.CHOCOLATE);
        }
        else if (tile.value > 16){
            tile.setFill(Color.CADETBLUE);
        }
        else if (tile.value > 8){
            tile.setFill(Color.BROWN);
        }
        else if (tile.value > 4){
            tile.setFill(Color.BLUEVIOLET);
        }
        else if (tile.value > 2){
            tile.setFill(Color.AQUAMARINE);
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
            canvas.getChildren().remove(game.TileArray[PosX][PosY].ID_Tile);
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
                        System.out.println("deleteTile: neu ID_Tile " + i + j + (game.TileArray[i][j].ID_Tile));
                        System.out.println("deleteTile: neu ID_Text " + i + j + (game.TileArray[i][j].ID_Text));
                    }

                }
            }
            game.TileArray[PosX][PosY] = null;
        }
        else{
            System.out.println("deleteTile: out of line");
        }
    }
}
