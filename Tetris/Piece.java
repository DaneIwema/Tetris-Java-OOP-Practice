package Tetris;

import java.util.Random;

class Piece {

    private Tetrominoe [] pieces;

    //[0]-[3]([0]-[1]) block coordinates
    //[0]-[3]([2]) Is bottom piece or not
    //[4]([0]-[1]) piece coordinates
    //[4][2] piece type
    //[4][3] piece rotation

    int [][] pieceData;
    
    public Piece() {
        newPiece(4, 6);
    }

    public int getX(){
        return pieceData[4][1];
    }

    public int getRotation(){
        return pieceData[4][3];
    }

    public int getY(){
        return pieceData[4][0];
    }

    public int getTX(int slot){
        return pieceData[slot][0];
    }

    public int getTY(int slot){
        return pieceData[slot][1];
    }

    public Tetrominoe getTetrominoe(int slot){
        return pieces[slot];
    }

    public void moveRight(){
        pieceData[4][1] = pieceData[4][1] + 1;
    }

    public void moveLeft(){
        pieceData[4][1] = pieceData[4][1] - 1;
    }

    public void moveDown(){
        pieceData[4][0] = pieceData[4][0] + 1;
    }

    public void moveUp(){
        pieceData[4][0] = pieceData[4][0] - 1;
    }

    public void rotate(){
        if (pieceData[4][2] != 1){
            for (int i = 0; i < 4; i++){
            int column = (pieceData[i][0]*(int)Math.cos(Math.toRadians(90)))-(pieceData[i][1]*(int)Math.sin(Math.toRadians(90)));
            int row = (pieceData[i][0]*(int)Math.sin(Math.toRadians(90)))-(pieceData[i][1]*(int)Math.cos(Math.toRadians(90)));
            pieceData[i][0] = column;
            pieceData[i][1] = row;
            }
            if (pieceData[4][3] == 4)
                pieceData[4][3] = 0;
            pieceData[4][3] = pieceData[4][3] + 1;
            updateBottomPieces();
        }
    }

    public void revRotate(){
        if (pieceData[4][2] != 1){
            for (int i = 0; i < 4; i++){
            int column = (pieceData[i][0]*(int)Math.cos(Math.toRadians(-90)))-(pieceData[i][1]*(int)Math.sin(Math.toRadians(-90)));
            int row = (pieceData[i][0]*(int)Math.sin(Math.toRadians(-90)))-(pieceData[i][1]*(int)Math.cos(Math.toRadians(-90)));
            pieceData[i][0] = column;
            pieceData[i][1] = row;
            }
            if (pieceData[4][3] == 0)
                pieceData[4][3] = 5;
            pieceData[4][3] = pieceData[4][3] - 1;
            updateBottomPieces();
        }
    }
    
    private boolean checkBottomMost(int slot){
        if (Math.max(Math.max(getTY(0), getTY(1)), 
            Math.max(getTY(2), getTY(3))) == getTY(slot))
            return true;
        return false;
    }

    //returns the bottom most piece if there is one
    private int getBottomMostPiece(){
        for (int i = 0; i < 3; i++){
            if (checkBottomMost(i))
                return i;
        }
        return 3;
    }

    public boolean checkBottom(int slot){
        if(pieceData[slot][2] == 1)
            return true;
        return false;
    }

    public void updateBottomPieces(){
        switch(pieceData[4][2]){
            case 0:
            case 1:
                updateBottomIfIOrCubePiece();
                break;
            case 2:
                updateBottomIfIOrCubePiece();
                ifT();
                break;
            case 3:
            case 4:
                if (pieceData[4][3] == 1)
                    updateBottomIfIOrCubePiece();
                else if (pieceData[4][3] == 2 || pieceData[4][3] == 4){
                    updateBottomIfIOrCubePiece();
                    IfHasWIngs();
                }
                else
                    ifLOrJThirdRot();
                break;
            case 5:
            case 6:
                if(pieceData[4][3] == 1 || pieceData[4][3] == 3){
                    updateBottomIfIOrCubePiece();
                    IfHasWIngs();
                }
                else {
                    updateBottomIfIOrCubePiece();
                    ifStackedSides(getBottomMostPiece());
                }
        }
    }

    private void ifLOrJThirdRot(){
        if(pieceData[4][2] == 4){
            pieceData[0][2] = 1;
            pieceData[1][2] = 0;
            pieceData[2][2] = 1;
            pieceData[3][2] = 1;
        }
        else if (pieceData[4][2] == 3){
            pieceData[0][2] = 1;
            pieceData[1][2] = 1;
            pieceData[2][2] = 0;
            pieceData[3][2] = 1;
        }
    }

    private void updateBottomIfIOrCubePiece(){
        for (int i = 0; i < 4; i++)
            if (checkBottomMost(i))
                pieceData[i][2] = 1;
            else
                pieceData[i][2] = 0;
    }

    private void ifT(){
        for (int i = 0; i < 4; i++)
                if(getTX(i) != 0)
                    pieceData[i][2] = 1;
    }

    private void IfHasWIngs(){
        for (int i = 0; i < 4; i++)
            if(getTX(i) != 0)
                pieceData[i][2] = 1;
    }

    public void ifStackedSides(int bottom){
        for (int i = 0; i < 4; i++)
            if (getTX(bottom) != getTX(i) && getTY(i) == 0)
                pieceData[i][2] = 1;
    }

    public void newPiece(int x, int y) {
        Random rand = new Random();
        switch(rand.nextInt(7)){
            case 0: //I
                pieces = new Tetrominoe [4];
                    for (int i = 0; i < 4; i++)
                        pieces[i] = new Tetrominoe("\033[48;2;20;240;240m \033[0m");
                pieceData = new int[][] {
                    {-1, 0, 0},
                    {0, 0, 0},
                    {1, 0, 0},
                    {2, 0, 0},
                    {x, y, 0, 1}
                };
                break;
            case 1: // Cube
                pieces = new Tetrominoe [4];
                    for (int i = 0; i < 4; i++)
                        pieces[i] = new Tetrominoe("\033[48;2;234;236;35m \033[0m");
                pieceData = new int[][] {
                    {-1, 0, 0},
                    {0, 0, 0},
                    {-1, 1, 0},
                    {0, 1, 0},
                    {x, y, 1, 1}
                };
                break;
            case 2: // T
                pieces = new Tetrominoe [4];
                    for (int i = 0; i < 4; i++)
                        pieces[i] = new Tetrominoe("\033[48;2;180;0;158m \033[0m");
                pieceData = new int[][] {
                    {-1, 0, 0},
                    {0, 0, 0},
                    {1, 0, 0},
                    {0, 1, 0},
                    {x, y, 2, 1}
                };
                break;
            case 3: // L
                pieces = new Tetrominoe [4];
                    for (int i = 0; i < 4; i++)
                        pieces[i] = new Tetrominoe("\033[48;2;252;127;0m \033[0m");
                pieceData = new int[][] {
                    {-1, 0, 0},
                    {0, 0, 0},
                    {1, 0, 0},
                    {1, -1, 0},
                    {x, y, 3, 1}
                };
                break;
            case 4: // J
                pieces = new Tetrominoe [4];
                    for (int i = 0; i < 4; i++)
                        pieces[i] = new Tetrominoe("\033[48;2;88;51;255m \033[0m");
                pieceData = new int[][] {
                    {-1, -1, 0},
                    {-1, 0, 0},
                    {0, 0, 0},
                    {1, 0, 0},
                    {x, y, 4, 1}
                };
                break;
            
            case 5: // Z
                pieces = new Tetrominoe [4];
                    for (int i = 0; i < 4; i++)
                        pieces[i] = new Tetrominoe("\033[48;2;252;57;31m \033[0m");
                pieceData = new int[][] {
                    {-1, 0, 0},
                    {0, 0, 0},
                    {0, 1, 0},
                    {1, 1, 0},
                    {x, y, 5, 1}
                };
                break;
            case 6: // S
                pieces = new Tetrominoe [4];
                    for (int i = 0; i < 4; i++)
                        pieces[i] = new Tetrominoe("\033[48;2;49;231;34m \033[0m");
                pieceData = new int[][] {
                    {0, 0, 0},
                    {1, 0, 0},
                    {-1, 1, 0},
                    {0, 1, 0},
                    {x, y, 6, 1}
                };
                break;
        }
        updateBottomPieces();
    }
}
