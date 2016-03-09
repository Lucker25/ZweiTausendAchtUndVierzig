package sample;

public class Game {
    public Tile[][] TileArray;
    public int game_size = 4;
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
        generateTile(0,0);
        generateTile(0,1);
        generateTile(0,2);
        generateTile(0,3);
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

    public void generateTile(int PosX, int PosY, int value){
        System.out.println("generateTile: teste Stelle: "+  PosX + "; " + PosY);
        while (positionCheck(PosX, PosY) != 1){
            PosX = (int) (Math.random() * game_size);
            PosY = (int) (Math.random() * game_size);
            System.out.println("generateTile: stelle gefunden: "+  PosX + "; " + PosY);
        }

        Tile tile = new Tile(PosX, PosY, value);
        TileArray[PosX][PosY] = tile;
        System.out.println("generateTile: Tile erstellt: "+  PosX + "; " + PosY);

    }

    public int lostWinCheck(){

        /*  return 0: Spiel läuft
            return 1: Spiel verloren
            return 2: Spiel gewonnen
        */

        int result = 1;
        for (int i= 0; i < game_size; i++){
            for (int j=0; j < game_size; j++){
                //System.out.println("Loop: " + i + "; " +j);
                if (TileArray[i][j] == null){
                    result = 0;
                    System.out.println("lostWinCheck: Spiel läuft ");
                }
                else if (TileArray[i][j].value == 2048){
                    result = 2;
                    System.out.println("lostWinCheck: Spiel gewonnen");
                }

            }
        }
        if (result == 1){
            System.out.println("lostWinCheck: Spiel verloren");
        }
        return result;
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

    public void moveAllTilesRight(){
        for (int i=0; i < game_size; i++){
            for (int j=game_size-1; j >=0;  j--){
                //System.out.println("Loop: " + i + "; " +j);
                singleTileMoveRightCheck(j,i);
            }
        }
        resetTurn();
    }
    public void moveAllTilesLeft(){
        for (int i= 0; i < game_size; i++){
            for (int j=0; j < game_size; j++){
                //System.out.println("Loop: " + i + "; " +j);
                singleTileMoveLeftCheck(j, i);
            }
        }
        resetTurn();
    }
    public void moveAllTilesUp(){
        for (int i= 0 ; i < game_size; i++){
            for (int j=1 ; j < game_size; j++){
                //System.out.println("Loop: " + i + "; " +j);
                singleTileMoveUpCheck(i, j);
            }
        }
        resetTurn();
    }
    public void moveAllTilesDown(){
        for (int i=0; i < game_size; i++){
            for (int j=game_size-1; j >= 0; j--){
                //System.out.println("Loop: " + i + "; " +j);
                singleTileMoveDownCheck(i,j);
            }
        }
        resetTurn();
    }

    public void resetTurn(){
        for (int i= 0; i < game_size; i++){
            for (int j=0; j < game_size; j++){
                //System.out.println("Loop: " + i + "; " +j);
                if (TileArray[i][j] != null && TileArray[i][j].turn == true){
                    TileArray[i][j].turn = false;
                }
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
                        (checkValue(TileArray[PosX][PosY-1], TileArray[PosX][PosY]))&&
                        (TileArray[PosX][PosY - 1].turn == false) &&
                        (TileArray[PosX][PosY].turn == false)){
                    TileArray[PosX ][PosY - 1].value += TileArray[PosX][PosY].value;
                    paint.deleteTile(PosX, PosY);
                    TileArray[PosX][PosY - 1].turn = true;
                    singleTileMoveUpCheck(PosX, PosY-1);

                }
            }
    }
    public void singleTileMoveDownCheck(int PosX, int PosY) {
        if ((PosX < game_size && PosY < game_size-1) && (TileArray[PosX][PosY] != null)) {
            if (positionCheck(PosX, PosY + 1) == 1) {
                TileArray[PosX][PosY + 1] = TileArray[PosX][PosY];
                TileArray[PosX][PosY + 1].PosY++;
                TileArray[PosX][PosY] = null;
                singleTileMoveDownCheck(PosX, PosY + 1);
            }
            else if ((positionCheck(PosX, PosY + 1) != 1) &&
                    (checkValue(TileArray[PosX][PosY + 1], TileArray[PosX][PosY]))&&
                    (TileArray[PosX][PosY + 1].turn == false)&&
                    (TileArray[PosX][PosY].turn == false)){
                TileArray[PosX ][PosY + 1].value += TileArray[PosX][PosY].value;
                paint.deleteTile(PosX, PosY);
                TileArray[PosX][PosY + 1].turn = true;
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
                    (checkValue(TileArray[PosX + 1][PosY], TileArray[PosX][PosY]))&&
                    (TileArray[PosX + 1][PosY].turn == false)&&
                    (TileArray[PosX][PosY].turn == false)){
                TileArray[PosX + 1][PosY].value += TileArray[PosX][PosY].value;
                paint.deleteTile(PosX, PosY);
                TileArray[PosX + 1][PosY].turn = true;
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
                    (checkValue(TileArray[PosX - 1][PosY], TileArray[PosX][PosY])) &&
                    (TileArray[PosX - 1][PosY].turn == false)&&
                    (TileArray[PosX][PosY].turn == false)){
                TileArray[PosX - 1][PosY].value += TileArray[PosX][PosY].value;
                paint.deleteTile(PosX, PosY);
                TileArray[PosX - 1][PosY].turn = true;
                singleTileMoveLeftCheck(PosX - 1, PosY);
            }
        }
    }





}
