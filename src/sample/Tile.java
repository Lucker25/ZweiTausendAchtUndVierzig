package sample;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Tile extends Rectangle {

    public int value;
    public int ID_Tile;
    public int ID_Text;
    public int PosX;
    public int PosY;
    public static int tile_size = 50;
    public Text text;
    public boolean turn;
    public Path path;
    public int CanvasPosX;
    public int CanvasPosY;



    public Tile(int startX, int startY, int value) {
        super(0, 0, tile_size, tile_size);
        //super(startX,startY, tile_size,tile_size, 10,10);
        this.PosX = startX;
        this.PosY = startY;
        this.value = value;
        this.setArcWidth(tile_size/3);
        this.setArcHeight(tile_size/3);

        text = new Text();
        this.text.setText(String.valueOf(this.value));
        this.text.setX(startX);
        this.text.setY(startY);
        this.ID_Text = -1;
        this.ID_Tile = -1;
        this.turn = false;
        this.CanvasPosX = startX*Tile.tile_size;
        this.CanvasPosY = startY*Tile.tile_size;


        //this.ID_Tile = 1;
    }

    public void setPos(int PosX, int PosY){
        this.PosX = PosX;
        this.PosY = PosY;
    }

}
