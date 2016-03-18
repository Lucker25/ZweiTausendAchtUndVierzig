package sample;

public class Game {
    public Tile[][] TileArray;
    public int game_size = 4;
    public Paint paint;
    private boolean movedTile = true;

    public static final int WON = 2;
    public static final int GO_ON =0;
    public static final int LOST=1;

    public Game() {
        TileArray = new Tile[game_size][game_size];
        paint = new Paint(this);

        //generateRandomTile();
        //generateRandomTile();
        //generateRandomTile();
        //generateRandomTile();
        //generateRandomTile();
        generateTile(0,0, 2048);
        //generateTile(1,0, 2048);
        //generateTile(0,1);
        //generateTile(0,2);
        //generateTile(0,3);
        //generateTile(1,1);




    }

    public void generateRandomTile(){
        if (movedTile) {
            int PosX = (int) (Math.random() * game_size);
            int PosY = (int) (Math.random() * game_size);

            while (positionCheck(PosX, PosY) != 1){
                System.out.println("Tile besetzt: " + PosX + "; " + PosY);
                PosX = (int) (Math.random() * game_size);
                PosY = (int) (Math.random() * game_size);

            }
            int value = (int) (Math.random() * 10);
            if (value > 5){
                value = 4;
            }
            else {
                value = 2;
            }
            System.out.println("generateRandomTile, stelle gefunden: "+  PosX + "; " + PosY);
            Tile tile = new Tile(PosX, PosY, value);
            TileArray[PosX][PosY] = tile;
            movedTile = false;
        }
        else {
            movedTile = false;
            return;
        }


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


        int result = LOST;
        for (int i= 0; i < game_size; i++){
            for (int j=0; j < game_size; j++){
                if (i < game_size-1){
                    if (checkValue(TileArray[i][j], TileArray[i+1][j]) ){
                        result = GO_ON;
                    }
                }
                if (j < game_size-1){
                    if (checkValue(TileArray[i][j], TileArray[i][j+1]) ){
                        result = GO_ON;
                    }
                }
                if (TileArray[i][j] == null){
                    result = GO_ON;
                }
                if (TileArray[i][j] != null){
                    if (TileArray[i][j].value == 2048){

                        System.out.println("lostWinCheck: Spiel gewonnen");
                        return WON;
                    }
                }


            }
        }
        if (result == LOST){
            System.out.println("lostWinCheck: Spiel verloren");
        }
        return result;
    }

    private int positionCheck(int PosX, int PosY){
        // return 1 wenn Position frei
        if ((PosX < game_size) && (PosY < game_size) && (PosX >= 0) && (PosY >= 0)) {
            if ((TileArray[PosX][PosY] == null)) {
                return 1;
            } else {
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
                singleTileMoveRightCheck(j,i);
            }
        }
        resetAdded();
    }
    public void moveAllTilesLeft(){
        for (int i= 0; i < game_size; i++){
            for (int j=0; j < game_size; j++){
                singleTileMoveLeftCheck(j, i);
            }
        }
        resetAdded();
    }
    public void moveAllTilesUp(){
        for (int i= 0 ; i < game_size; i++){
            for (int j=1 ; j < game_size; j++){
                singleTileMoveUpCheck(i, j);
            }
        }
        resetAdded();
    }
    public void moveAllTilesDown(){
        for (int i=0; i < game_size; i++){
            for (int j=game_size-1; j >= 0; j--){
                singleTileMoveDownCheck(i,j);
            }
        }
        resetAdded();
    }

    private void resetAdded(){
        for (int i= 0; i < game_size; i++){
            for (int j=0; j < game_size; j++){
                if (TileArray[i][j] != null && TileArray[i][j].added == true){
                    TileArray[i][j].added = false;
                }
            }
        }
    }

    private boolean checkValue(Tile tile1, Tile tile2){
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

    private void addValues(Tile tile1, Tile tile2){
            System.out.println("addValues: " + tile1.value + " + " + tile2.value + " = " + (tile1.value+tile2.value));
            tile1.value += tile2.value;
    }

    private void singleTileMoveUpCheck(int PosX, int PosY) {
            if ((PosX >= 0 && PosY > 0) && (TileArray[PosX][PosY] != null)) {
                if (positionCheck(PosX, PosY-1) == 1) {
                    TileArray[PosX][PosY - 1] = TileArray[PosX][PosY];
                    TileArray[PosX][PosY - 1].PosY--;
                    TileArray[PosX][PosY] = null;
                    movedTile = true;
                    singleTileMoveUpCheck(PosX, PosY-1);
                }
                else if ((positionCheck(PosX, PosY-1) != 1) &&
                        (checkValue(TileArray[PosX][PosY-1], TileArray[PosX][PosY]))&&
                        (TileArray[PosX][PosY - 1].added == false) &&
                        (TileArray[PosX][PosY].added == false)){
                    TileArray[PosX ][PosY - 1].value += TileArray[PosX][PosY].value;
                    paint.deleteTile(PosX, PosY);
                    TileArray[PosX][PosY - 1].added = true;
                    movedTile = true;
                    singleTileMoveUpCheck(PosX, PosY-1);

                }
            }
    }
    private void singleTileMoveDownCheck(int PosX, int PosY) {
        if ((PosX < game_size && PosY < game_size-1) && (TileArray[PosX][PosY] != null)) {
            if (positionCheck(PosX, PosY + 1) == 1) {
                TileArray[PosX][PosY + 1] = TileArray[PosX][PosY];
                TileArray[PosX][PosY + 1].PosY++;
                TileArray[PosX][PosY] = null;
                movedTile = true;
                singleTileMoveDownCheck(PosX, PosY + 1);
            }
            else if ((positionCheck(PosX, PosY + 1) != 1) &&
                    (checkValue(TileArray[PosX][PosY + 1], TileArray[PosX][PosY]))&&
                    (TileArray[PosX][PosY + 1].added == false)&&
                    (TileArray[PosX][PosY].added == false)){
                TileArray[PosX ][PosY + 1].value += TileArray[PosX][PosY].value;
                paint.deleteTile(PosX, PosY);
                TileArray[PosX][PosY + 1].added = true;
                movedTile = true;
                singleTileMoveDownCheck(PosX, PosY + 1);
            }
        }
    }
    private void singleTileMoveRightCheck(int PosX, int PosY) {
        if ((PosX < game_size - 1 && PosY < game_size) && (TileArray[PosX][PosY] != null)) {
            if (positionCheck(PosX + 1, PosY) == 1) {
                TileArray[PosX + 1][PosY] = TileArray[PosX][PosY];
                TileArray[PosX + 1][PosY].PosX++;
                TileArray[PosX][PosY] = null;
                movedTile = true;
                singleTileMoveRightCheck(PosX + 1, PosY);
            }
            else if ((positionCheck(PosX + 1, PosY) != 1) &&
                    (checkValue(TileArray[PosX + 1][PosY], TileArray[PosX][PosY]))&&
                    (TileArray[PosX + 1][PosY].added == false)&&
                    (TileArray[PosX][PosY].added == false)){
                TileArray[PosX + 1][PosY].value += TileArray[PosX][PosY].value;
                paint.deleteTile(PosX, PosY);
                TileArray[PosX + 1][PosY].added = true;
                movedTile = true;
                singleTileMoveRightCheck(PosX + 1, PosY);
            }
        }
    }
    private void singleTileMoveLeftCheck(int PosX, int PosY) {
        if ((PosX > 0 && PosY >= 0) && (TileArray[PosX][PosY] != null)) {
            if (positionCheck(PosX - 1, PosY) == 1) {
                TileArray[PosX - 1][PosY] = TileArray[PosX][PosY];
                TileArray[PosX - 1][PosY].PosX--;
                TileArray[PosX][PosY] = null;
                movedTile = true;
                singleTileMoveLeftCheck(PosX - 1, PosY);
            }
            else if ((positionCheck(PosX - 1, PosY) != 1) &&
                    (checkValue(TileArray[PosX - 1][PosY], TileArray[PosX][PosY])) &&
                    (TileArray[PosX - 1][PosY].added == false)&&
                    (TileArray[PosX][PosY].added == false)){
                TileArray[PosX - 1][PosY].value += TileArray[PosX][PosY].value;
                paint.deleteTile(PosX, PosY);
                TileArray[PosX - 1][PosY].added = true;
                movedTile = true;
                singleTileMoveLeftCheck(PosX - 1, PosY);
            }
        }
    }

    public void setMovedTile(){
        movedTile=true;
    }

    public void newGame(){
        for (int i = 0; i < game_size; i++) {
            for (int j = 0; j < game_size; j++){
                if (TileArray[i][j] != null){
                    paint.deleteTile(i, j);
                }
            }
        }
        setMovedTile();
        Main.unsetGameContinued();
        generateRandomTile();
        paint.paint();

    }


}
