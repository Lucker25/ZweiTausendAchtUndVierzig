package sample;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public Tile[][] TileArray;
    public static int game_size = 4;



    public Game() {
        TileArray = new Tile[game_size][game_size];
        /*
        TileArray[X][Y] -> Array der Objekte Tiles
        Single Tile
        -> PositionX
        -> PositionY
        -> Value
         */
        //generateRandomTile();
        //generateRandomTile();
        generateTile(0,0);
        generateTile(0,1);


    }

    public void generateRandomTile(){
        int PosX = (int) (Math.random() * game_size);
        int PosY = (int) (Math.random() * game_size);

        while (positionCheck(PosX, PosY) != 1){
            System.out.println("Tile besetzt: " + PosX + "; " + PosY);
            PosX = (int) (Math.random() * game_size);
            PosY = (int) (Math.random() * game_size);

        }
        System.out.println("generateRandomTile, stelle gefunden: "+  PosX + "; " + PosY);
        Tile tile = new Tile(PosX, PosY, 2);
        TileArray[PosX][PosY] = tile;

    }

    public void generateTile(int PosX, int PosY){
        System.out.println("generateTile: "+  PosX + "; " + PosY);
        while (positionCheck(PosX, PosY) != 1){
            PosX = (int) (Math.random() * game_size);
            PosY = (int) (Math.random() * game_size);
            System.out.println("generate, stelle gefunden: "+  PosX + "; " + PosY);
        }
        Tile tile = new Tile(PosX, PosY, 2);
        TileArray[PosX][PosY] = tile;

    }

    public int positionCheck(int PosX, int PosY){
        // return 1 wenn Position frei
        //System.out.println("Check: " + PosX + "; " +PosY);
        if ((PosX < game_size) && (PosY < game_size) && (PosX >= 0) && (PosY >= 0)) {
            if ((TileArray[PosX][PosY] == null)) {
                return 1;
            } else {
                return 0;
            }
        }
        else {
            return 0;
        }
    }

    public void singleTileMoveRight(Tile tile, int PosX, int PosY) {
        if (PosX < game_size && PosY < game_size) {
            if (positionCheck(PosX + 1, PosY) == 1) {
                TileArray[PosX + 1][PosY] = TileArray[PosX][PosY];
                //System.out.println(TileArray[PosX+1][PosY].value);
                TileArray[PosX][PosY] = null;
            }
        }

    }
    public void singleTileMoveLeft(Tile tile, int PosX, int PosY) {
            if (PosX >= 0 && PosY >= 0) {
                if (positionCheck(PosX - 1, PosY) == 1) {
                    TileArray[PosX - 1][PosY] = TileArray[PosX][PosY];
                    //System.out.println(TileArray[PosX-1][PosY].value);
                    TileArray[PosX][PosY] = null;
                }
            }
    }
    public void singleTileMoveUp(Tile tile, int PosX, int PosY) {
        if (PosX >= 0 && PosY >= 0) {
            if (positionCheck(PosX, PosY - 1) == 1) {
                TileArray[PosX][PosY - 1] = TileArray[PosX][PosY];
                //System.out.println(TileArray[PosX][PosY-1].value);
                TileArray[PosX][PosY] = null;
            }
        }

    }
    public void singleTileMoveDown(Tile tile, int PosX, int PosY) {
        if (PosX < game_size && PosY < game_size) {
            if (positionCheck(PosX, PosY + 1) == 1) {
                TileArray[PosX][PosY + 1] = TileArray[PosX][PosY];
                //System.out.println(TileArray[PosX][PosY + 1].value);
                TileArray[PosX][PosY] = null;
            }
        }
    }

    public void moveAllTilesRight(){
        for (int i=0; i < game_size; i++){
            for (int j=0; j < game_size; j++){
                //System.out.println("Loop: " + i + "; " +j);
                singleTileMoveRight(TileArray[i][j], i, j);
            }
        }
    }
    public void moveAllTilesLeft(){
        for (int i= game_size - 1; i >= 0; i--){
            for (int j=game_size - 1; j >= 0; j--){
                //System.out.println("Loop: " + i + "; " +j);
                singleTileMoveLeft(TileArray[i][j], i, j);
            }
        }
    }
    public void moveAllTilesUp(){
        for (int i= game_size - 1; i >= 0; i--){
            for (int j=game_size - 1; j >= 0; j--){
                //System.out.println("Loop: " + i + "; " +j);
                singleTileMoveUp(TileArray[j][i], j, i);
            }
        }
    }
    public void moveAllTilesDown(){
        for (int i=0; i < game_size; i++){
            for (int j=0; j < game_size; j++){
                //System.out.println("Loop: " + i + "; " +j);
                singleTileMoveDown(TileArray[j][i], j, i);
            }
        }
    }

    public boolean checkValue(Tile tile1, Tile tile2){
        if (tile1.value == tile2.value){
            return true;
        }
        else{
            return false;
        }
    }

    public void addValues(Tile tile1, Tile tile2){
        if (checkValue(tile1, tile2)){
            tile1.value += tile2.value;
        }
    }

    public void deleteTile(int PosX, int PosY){
        TileArray[PosX][PosY].value = 0;
        TileArray[PosX][PosY] = null;
    }

}
