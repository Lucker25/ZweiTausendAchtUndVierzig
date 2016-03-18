package sample;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Tile extends Rectangle {

    public int value;// Wert der Tile
    public int ID_Tile;// ID der Tile (wird wahrscheinlich nicht mehr benötigt)
    public int ID_Text;// s.o.
    public int PosX;// Position der Tile im Array
    public int PosY;
    public static int tile_size = 50; //Größe der Tile (Quadrat)
    public Text text;//Beschriftung der Tile
    public boolean added;// Tiles können nur einmal im Zug zusammengeschoben werden



    public Tile(int startX, int startY, int value) {
        super(0, 0, tile_size, tile_size);
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
        this.added = false;
    }

}
