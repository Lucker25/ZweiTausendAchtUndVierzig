package sample;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    public int value;
    public int ID;
    public int PosX;
    public int PosY;
    public static int tile_size = 50;



    public Tile(int startX, int startY, int value) {
        super(startX, startY, tile_size, tile_size);
        this.PosX = startX;
        this.PosY = startY;
        this.value = value;
        //this.ID = 1;
    }

    public void setPos(int PosX, int PosY){
        this.PosX = PosX;
        this.PosY = PosY;
    }

    public void delete(Pane canvas){
        this.value = 0;
        canvas.getChildren().remove(this.ID);
        //TileArray[PosX][PosY] = null;
        System.out.println("deleteTile: " + this.PosX + "; " + this.PosY + "; " );
        //System.out.println("debugMessage: " + 0 + "; " + 3 + "; " + TileArray[0][3]);

    }

}
