package sample;

import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    public int value;
    public static int tile_size = 50;



    public Tile(int startX, int startY, int value) {
        super(startX, startY, tile_size, tile_size);
        this.value = value;
    }

}
