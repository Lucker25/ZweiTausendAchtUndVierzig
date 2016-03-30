package DHBW;

import static DHBW.Game.GameState.*;

public class Game {
    public Tile[][] tileArray; //Spielfeld
    public int gameSize = 4;// Größe des Spielfeldes
    public Paint paint;
    private boolean movedTile = true; // wenn keine Tiles bewegt werden = false; wird für den Spielablauf benötigt
    private int score = 0;

    enum GameState{
        GO_ON, LOST, WON
    }


    public Game() {
        tileArray = new Tile[gameSize][gameSize];
        paint = new Paint(this);

        // für den Spielablauf benötigt
        generateRandomTile();

        // nur zu Testzwecken integriert
        //generateRandomTile();
        //generateRandomTile();
        //generateRandomTile();
        //generateRandomTile();
        //generateTile(0,1,2);
        //generateTile(0,2);
        //generateTile(0,3);
        generateTile(1,0, 2048);
        //generateTile(0,1);
        //generateTile(0,2);
        //generateTile(0,3);
        //generateTile(1,1);
    }
    //------------------------------------------------------------------------------------------------------------------Generate Tile Funktionen
    public void generateRandomTile(){
        // Erzeugt eine zufällig im Spielfeld platzierte Tile
        if (movedTile) {
            int posX = (int) (Math.random() * gameSize);
            int posY = (int) (Math.random() * gameSize);

            // sucht nach einer zufällig generierten freien Spielfläche
            while (positionCheck(posX, posY) != 1){
                System.out.println("Tile besetzt: " + posX + "; " + posY);
                posX = (int) (Math.random() * gameSize);
                posY = (int) (Math.random() * gameSize);

            }
            // Tiles werden zufällig mit dem Wert 2 oder 4 gespawnt
            int value = (int) (Math.random() * 10);
            if (value > 5){
                value = 4;
            }
            else {
                value = 2;
            }

            System.out.println("generateRandomTile, stelle gefunden: "+  posX + "; " + posY);
            Tile tile = new Tile(posX, posY, value);
            tileArray[posX][posY] = tile;
            movedTile = false;
        }
        // Keine Fläche frei
        else {
            movedTile = false;// muss für den Spielablauf zurückgesetzt werden
        }


    }
    public void generateTile(int posX, int posY){
        // Funtkion um eine Tile mit dem Wert 2 an einer bestimmten Position zu erzeugen
        System.out.println("generateTile: teste Stelle: "+  posX + "; " + posY);
        while (positionCheck(posX, posY) != 1){
            posX = (int) (Math.random() * gameSize);
            posY = (int) (Math.random() * gameSize);
            System.out.println("generateTile: stelle gefunden: "+  posX + "; " + posY);
        }


        Tile tile = new Tile(posX, posY, 2);
        tileArray[posX][posY] = tile;
        System.out.println("generateTile: Tile erstellt: "+  posX + "; " + posY);

    }
    public void generateTile(int posX, int posY, int value){
        // Funtkion um eine Tile mit einem beliebigen Wert an einer bestimmten Position zu erzeugen
        System.out.println("generateTile: teste Stelle: "+  posX + "; " + posY);
        while (positionCheck(posX, posY) != 1){
            posX = (int) (Math.random() * gameSize);
            posY = (int) (Math.random() * gameSize);
            System.out.println("generateTile: stelle gefunden: "+  posX + "; " + posY);
        }

        Tile tile = new Tile(posX, posY, value);
        tileArray[posX][posY] = tile;
        System.out.println("generateTile: Tile erstellt: "+  posX + "; " + posY);

    }
    //------------------------------------------------------------------------------------------------------------------
    public GameState lostWinCheck(){
        // überprüft ob das Spiel gewonnen oder verloren wurde

        GameState result = LOST;
        for (int i = 0; i < gameSize; i++){
            for (int j = 0; j < gameSize; j++){
                // können noch Tiles zusammengeschoben werden?
                if (i < gameSize -1){
                    if (checkValue(tileArray[i][j], tileArray[i+1][j]) ){
                        result = GO_ON;
                    }
                }
                if (j < gameSize -1){
                    if (checkValue(tileArray[i][j], tileArray[i][j+1]) ){
                        result = GO_ON;
                    }
                }
                // gibt es noch Leere Felder
                if (tileArray[i][j] == null){
                    result = GO_ON;
                }
                // wurde das Spiel gewonnen?
                if (tileArray[i][j] != null){
                    if (tileArray[i][j].value == 2048 && Main.getGameContinued() != true){

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

    private int positionCheck(int posX, int posY){
        // return 1 wenn Position frei
        if ((posX < gameSize) && (posY < gameSize) && (posX >= 0) && (posY >= 0)) {
            if ((tileArray[posX][posY] == null)) {
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

    //------------------------------------------------------------------------------------------------------------------moveAllTiles
    //Diese funktionen bewegen alle Tiles in eine bestimmte Richtung
    public void moveAllTilesRight(){
        for (int i = 0; i < gameSize; i++){
            for (int j = gameSize -1; j >=0; j--){
                singleTileMoveRightCheck(j,i);
            }
        }
        resetAdded();
    }
    public void moveAllTilesLeft(){
        for (int i = 0; i < gameSize; i++){
            for (int j = 0; j < gameSize; j++){
                singleTileMoveLeftCheck(j, i);
            }
        }
        resetAdded();
    }
    public void moveAllTilesUp(){
        for (int i = 0; i < gameSize; i++){
            for (int j = 1; j < gameSize; j++){
                singleTileMoveUpCheck(i, j);
            }
        }
        resetAdded();
    }
    public void moveAllTilesDown(){
        for (int i = 0; i < gameSize; i++){
            for (int j = gameSize -1; j >= 0; j--){
                singleTileMoveDownCheck(i,j);
            }
        }
        resetAdded();
    }
    //setzt den added Wert aller Tiles zurück
    private void resetAdded(){
        for (int i = 0; i < gameSize; i++){
            for (int j = 0; j < gameSize; j++){
                if (tileArray[i][j] != null && tileArray[i][j].added == true){
                    tileArray[i][j].added = false;
                }
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    private boolean checkValue(Tile tile1, Tile tile2){
        //Testet ob die Werte von zwei Tiles identisch sind
        if (tile1 != null && tile2 != null) {
            if ((tile1.posX < gameSize) && (tile1.posY < gameSize) && (tile1.posX >= 0) && (tile1.posY >= 0) &&
                    (tile2.posX < gameSize) && (tile2.posY < gameSize) && (tile2.posX >= 0) && (tile2.posY >= 0)) {
                System.out.println("checkValues: (" + tile1.posX + ", " + tile1.posY + ") " + tile1.value + " + (" + tile2.posX + ", " + tile2.posY + ") " + tile2.value);
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

    //------------------------------------------------------------------------------------------------------------------moveSingleTile Funktionen
    private void singleTileMoveUpCheck(int posX, int posY) {
            if ((posX >= 0 && posY > 0) && (tileArray[posX][posY] != null)) {
                if (positionCheck(posX, posY-1) == 1) {
                    tileArray[posX][posY - 1] = tileArray[posX][posY];
                    tileArray[posX][posY - 1].posY--;
                    tileArray[posX][posY] = null;
                    movedTile = true;
                    singleTileMoveUpCheck(posX, posY-1);
                }
                else if ((positionCheck(posX, posY-1) != 1) &&
                        (checkValue(tileArray[posX][posY-1], tileArray[posX][posY]))&&
                        (tileArray[posX][posY - 1].added == false) &&
                        (tileArray[posX][posY].added == false)){
                    tileArray[posX ][posY - 1].value += tileArray[posX][posY].value;
                    score +=  tileArray[posX][posY - 1].value;
                    paint.deleteTile(posX, posY);
                    tileArray[posX][posY - 1].added = true;
                    movedTile = true;
                    singleTileMoveUpCheck(posX, posY-1);

                }
            }
    }
    private void singleTileMoveDownCheck(int posX, int posY) {
        if ((posX < gameSize && posY < gameSize -1) && (tileArray[posX][posY] != null)) {
            if (positionCheck(posX, posY + 1) == 1) {
                tileArray[posX][posY + 1] = tileArray[posX][posY];
                tileArray[posX][posY + 1].posY++;
                tileArray[posX][posY] = null;
                movedTile = true;
                singleTileMoveDownCheck(posX, posY + 1);
            }
            else if ((positionCheck(posX, posY + 1) != 1) &&
                    (checkValue(tileArray[posX][posY + 1], tileArray[posX][posY]))&&
                    (tileArray[posX][posY + 1].added == false)&&
                    (tileArray[posX][posY].added == false)){
                tileArray[posX ][posY + 1].value += tileArray[posX][posY].value;
                score +=  tileArray[posX][posY + 1].value;
                paint.deleteTile(posX, posY);
                tileArray[posX][posY + 1].added = true;
                movedTile = true;
                singleTileMoveDownCheck(posX, posY + 1);
            }
        }
    }
    private void singleTileMoveRightCheck(int posX, int posY) {
        if ((posX < gameSize - 1 && posY < gameSize) && (tileArray[posX][posY] != null)) {
            if (positionCheck(posX + 1, posY) == 1) {
                tileArray[posX + 1][posY] = tileArray[posX][posY];
                tileArray[posX + 1][posY].posX++;
                tileArray[posX][posY] = null;
                movedTile = true;
                singleTileMoveRightCheck(posX + 1, posY);
            }
            else if ((positionCheck(posX + 1, posY) != 1) &&
                    (checkValue(tileArray[posX + 1][posY], tileArray[posX][posY]))&&
                    (tileArray[posX + 1][posY].added == false)&&
                    (tileArray[posX][posY].added == false)){
                tileArray[posX + 1][posY].value += tileArray[posX][posY].value;
                score +=  tileArray[posX + 1][posY].value;
                paint.deleteTile(posX, posY);
                tileArray[posX + 1][posY].added = true;
                movedTile = true;
                singleTileMoveRightCheck(posX + 1, posY);
            }
        }
    }
    private void singleTileMoveLeftCheck(int posX, int posY) {
        if ((posX > 0 && posY >= 0) && (tileArray[posX][posY] != null)) {
            if (positionCheck(posX - 1, posY) == 1) {
                tileArray[posX - 1][posY] = tileArray[posX][posY];
                tileArray[posX - 1][posY].posX--;
                tileArray[posX][posY] = null;
                movedTile = true;
                singleTileMoveLeftCheck(posX - 1, posY);
            }
            else if ((positionCheck(posX - 1, posY) != 1) &&
                    (checkValue(tileArray[posX - 1][posY], tileArray[posX][posY])) &&
                    (tileArray[posX - 1][posY].added == false)&&
                    (tileArray[posX][posY].added == false)){
                tileArray[posX - 1][posY].value += tileArray[posX][posY].value;
                score +=  tileArray[posX - 1][posY].value;
                paint.deleteTile(posX, posY);
                tileArray[posX - 1][posY].added = true;
                movedTile = true;
                singleTileMoveLeftCheck(posX - 1, posY);
            }
        }
    }

    public void newGame(){
        // Setzt das Spielfeld zurück um ein neues Spiel beginnen zu können
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++){
                if (tileArray[i][j] != null){
                    paint.deleteTile(i, j);
                }
            }
        }
        movedTile = true;
        Main.unsetGameContinued();
        score = 0;
        generateRandomTile();
        paint.paint();

    }

    public int getScore(){
        return this.score;
    }


}
