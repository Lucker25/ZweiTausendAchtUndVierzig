package sample;

public class Game {
    public Tile[][] TileArray;
    public static int game_size = 4;
    public Paint paint;

    public Game() {
        TileArray = new Tile[game_size][game_size];
        paint = new Paint(this);
        /*
        TileArray[X][Y] -> Array der Objekte Tiles
        Single Tile
        -> PositionX
        -> PositionY
        -> Value
         */
        //generateRandomTile();
        //generateRandomTile();
        //generateRandomTile();
        //generateRandomTile();
        //generateRandomTile();
        //generateTile(0,0);
        generateTile(1,0);
        generateTile(2,0);
        //generateTile(1,1);



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
        System.out.println("generateTile: teste Stelle: "+  PosX + "; " + PosY);
        while (positionCheck(PosX, PosY) != 1){
            PosX = (int) (Math.random() * game_size);
            PosY = (int) (Math.random() * game_size);
            System.out.println("generateTile: stelle gefunden: "+  PosX + "; " + PosY);
        }

        Tile tile = new Tile(PosX, PosY, 2);
        TileArray[PosX][PosY] = tile;
        System.out.println("generateTile: Tile erstellt: "+  PosX + "; " + PosY);

    }

    public int positionCheck(int PosX, int PosY){
        // return 1 wenn Position frei
        //System.out.println("Check: " + PosX + "; " +PosY);
        if ((PosX < game_size) && (PosY < game_size) && (PosX >= 0) && (PosY >= 0)) {
            if ((TileArray[PosX][PosY] == null)) {
                //System.out.println("positionCheck: " +PosX+"; "+ PosY + " ist frei");
                return 1;
            } else {
                //System.out.println("positionCheck: " +PosX+"; "+ PosY + " ist belegt");
                return 0;
            }
        }
        else {
            System.out.println("positionCheck: out of line");
            return 0;
        }
    }

    public void singleTileMoveRight(Tile tile) {
        if (tile.PosX < game_size-1  && tile.PosY < game_size) {
            if (positionCheck(tile.PosX +1, tile.PosY) == 1) {
                TileArray[tile.PosX + 1][tile.PosY] = TileArray[tile.PosX][tile.PosY];
                tile.setPos(tile.PosX + 1, tile.PosY);
                System.out.println("singleTileMoveRight: von: "+tile.PosX+"; "+ tile.PosY +" nach " + (tile.PosX+1) +"; "+tile.PosY);
                tile = null;
                singleTileMoveRight(TileArray[tile.PosX+1][tile.PosY]);
            }
        }
    }
    public void singleTileMoveLeft(Tile tile) {
            if (tile.PosX > 0 && tile.PosY >= 0) {
                if (positionCheck(tile.PosX - 1, tile.PosY) == 1) {
                    TileArray[tile.PosX - 1][tile.PosY] = TileArray[tile.PosX][tile.PosY];
                    tile.setPos(tile.PosX - 1, tile.PosY);
                    System.out.println("singleTileMoveLeft: von: "+tile.PosX+"; "+ tile.PosY +" nach " + (tile.PosX-1) +"; "+tile.PosY);
                    tile = null;
                    singleTileMoveLeft(TileArray[tile.PosX-1][tile.PosY]);
                }
            }
    }
    public void singleTileMoveUp(Tile tile) {
        if (tile.PosX >= 0 && tile.PosY > 0) {
            if (positionCheck(tile.PosX, tile.PosY-1) == 1) {
                TileArray[tile.PosX][tile.PosY - 1] = TileArray[tile.PosX][tile.PosY];
                tile.setPos(tile.PosX, tile.PosY - 1);
                //System.out.println(TileArray[PosX][PosY-1].value);
                tile = null;
                singleTileMoveUp(TileArray[tile.PosX][tile.PosY-1]);
            }
        }

    }
    public void singleTileMoveDown(Tile tile) {
        if (tile.PosX <= game_size -  1 && tile.PosY < game_size-1) {
            if (positionCheck(tile.PosX, tile.PosY-1) == 1) {
                TileArray[tile.PosX][tile.PosY + 1] = TileArray[tile.PosX][tile.PosY];
                tile.setPos(tile.PosX, tile.PosY + 1);
                //System.out.println(TileArray[PosX][PosY + 1].value);
                tile = null;
                singleTileMoveDown(TileArray[tile.PosX][tile.PosY+1]);
            }
        }
    }

    public void moveAllTilesRight(){
        for (int i=0; i < game_size; i++){
            for (int j=game_size-1; j >=0;  j--){
                //System.out.println("Loop: " + i + "; " +j);
                singleTileMoveRightCheck(j,i);
            }
        }
    }
    public void moveAllTilesLeft(){
        for (int i= 0; i < game_size; i++){
            for (int j=0; j < game_size; j++){
                //System.out.println("Loop: " + i + "; " +j);
                singleTileMoveLeftCheck(j, i);
            }
        }
    }
    public void moveAllTilesUp(){
        for (int i= 0 ; i < game_size; i++){
            for (int j=1 ; j < game_size; j++){
                //System.out.println("Loop: " + i + "; " +j);
                singleTileMoveUpCheck(i, j);
            }
        }
    }
    public void moveAllTilesDown(){
        for (int i=0; i < game_size; i++){
            for (int j=game_size-1; j >= 0; j--){
                //System.out.println("Loop: " + i + "; " +j);
                singleTileMoveDownCheck(i,j);
            }
        }
    }


    public boolean checkValue(Tile tile1, Tile tile2){
        if (tile1 != null && tile2 != null) {
            if ((tile1.PosX < game_size) && (tile1.PosY < game_size) && (tile1.PosX >= 0) && (tile1.PosY >= 0) &&
                    (tile2.PosX < game_size) && (tile2.PosY < game_size) && (tile2.PosX >= 0) && (tile2.PosY >= 0)) {
                System.out.println("checkValues: (" + tile1.PosX + ", " + tile1.PosY + ") " + tile1.value + " + (" + tile2.PosX + ", " + tile2.PosY + ") " + tile2.value);
                if (tile1.value == tile2.value) {
                    System.out.println("checkValues: Werte identisch");
                    return true;
                } else {
                    System.out.println("checkValues: Werte unterschiedlich");
                    return false;
                }
            }
        }
        return false;
    }

    public void addValues(Tile tile1, Tile tile2){
        //if (checkValue(tile1, tile2)){
            System.out.println("addValues: " + tile1.value + " + " + tile2.value + " = " + (tile1.value+tile2.value));
            tile1.value += tile2.value;
        //}
    }

    public void singleTileMoveUpCheck(int PosX, int PosY) {
            if ((PosX >= 0 && PosY > 0) && (TileArray[PosX][PosY] != null)) {
                if (positionCheck(PosX, PosY-1) == 1) {
                    TileArray[PosX][PosY - 1] = TileArray[PosX][PosY];
                    //System.out.println(TileArray[PosX][PosY-1].value);
                    //TileArray[PosX][PosY - 1].setPos(PosX, PosY - 1);
                    TileArray[PosX][PosY - 1].PosY--;
                    TileArray[PosX][PosY] = null;
                    singleTileMoveUpCheck(PosX, PosY-1);
                }
                else if ((positionCheck(PosX, PosY-1) != 1) &&
                        (checkValue(TileArray[PosX][PosY-1], TileArray[PosX][PosY]))){

                    TileArray[PosX ][PosY - 1].value += TileArray[PosX][PosY].value;
                    paint.deleteTile(PosX, PosY);
                    singleTileMoveUpCheck(PosX, PosY-1);

                }
            }
    }
    public void singleTileMoveDownCheck(int PosX, int PosY) {
        if ((PosX < game_size && PosY < game_size-1) && (TileArray[PosX][PosY] != null)) {
            if (positionCheck(PosX, PosY + 1) == 1) {
                TileArray[PosX][PosY + 1] = TileArray[PosX][PosY];
                //System.out.println(TileArray[PosX][PosY-1].value);
                //TileArray[PosX][PosY - 1].setPos(PosX, PosY - 1);
                TileArray[PosX][PosY + 1].PosY++;
                TileArray[PosX][PosY] = null;
                singleTileMoveDownCheck(PosX, PosY + 1);
            }
            else if ((positionCheck(PosX, PosY + 1) != 1) &&
                    (checkValue(TileArray[PosX][PosY + 1], TileArray[PosX][PosY]))){
                TileArray[PosX ][PosY + 1].value += TileArray[PosX][PosY].value;
                paint.deleteTile(PosX, PosY);
                singleTileMoveDownCheck(PosX, PosY + 1);
            }
        }
    }
    public void singleTileMoveRightCheck(int PosX, int PosY) {
        if ((PosX < game_size - 1 && PosY < game_size) && (TileArray[PosX][PosY] != null)) {
            if (positionCheck(PosX + 1, PosY) == 1) {
                TileArray[PosX + 1][PosY] = TileArray[PosX][PosY];
                //System.out.println(TileArray[PosX][PosY-1].value);
                //TileArray[PosX][PosY - 1].setPos(PosX, PosY - 1);
                TileArray[PosX + 1][PosY].PosX++;
                TileArray[PosX][PosY] = null;
                singleTileMoveRightCheck(PosX + 1, PosY);
            }
            else if ((positionCheck(PosX + 1, PosY) != 1) &&
                    (checkValue(TileArray[PosX + 1][PosY], TileArray[PosX][PosY]))){
                TileArray[PosX + 1][PosY].value += TileArray[PosX][PosY].value;
                paint.deleteTile(PosX, PosY);
                singleTileMoveRightCheck(PosX + 1, PosY);
            }
        }
    }
    public void singleTileMoveLeftCheck(int PosX, int PosY) {
        if ((PosX > 0 && PosY >= 0) && (TileArray[PosX][PosY] != null)) {
            if (positionCheck(PosX - 1, PosY) == 1) {
                TileArray[PosX - 1][PosY] = TileArray[PosX][PosY];
                //System.out.println(TileArray[PosX][PosY-1].value);
                //TileArray[PosX][PosY - 1].setPos(PosX, PosY - 1);
                TileArray[PosX - 1][PosY].PosX--;
                TileArray[PosX][PosY] = null;
                singleTileMoveLeftCheck(PosX - 1, PosY);
            }
            else if ((positionCheck(PosX - 1, PosY) != 1) &&
                    (checkValue(TileArray[PosX - 1][PosY], TileArray[PosX][PosY]))){
                TileArray[PosX - 1][PosY].value += TileArray[PosX][PosY].value;
                paint.deleteTile(PosX, PosY);
                singleTileMoveLeftCheck(PosX - 1, PosY);
            }
        }
    }





}
