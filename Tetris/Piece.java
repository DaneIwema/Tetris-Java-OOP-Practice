package Tetris;

import java.util.Random;

class Piece {

    private Block [] blocks;

    int [] pieceData;

    int [][] blockData;
    //[0]-[3]([0]-[1]) block coordinates
    //[0]-[3]([2]) Is bottom piece or not
    //[4]([0]-[1]) piece coordinates
    //[4][2] piece type
    //[4][3] piece rotation

    public Piece() {
        newPiece(2, 6);
    }

    public int getCol(){
        return blockData[4][1];
    }

    public void setCol(int col){
        blockData[4][1] = col;
    }

    public int getRotation(){
        return blockData[4][3];
    }

    public void setRotation(int target){
        for (int i = blockData[4][3]; i < target + 4; i++)
            rotate();
        blockData[4][3] = target;
    }

    public int getBCol(int slot){
        return blockData[slot][0];
    }

    public int getBRow(int slot){
        return blockData[slot][1];
    }

    public Block getBlock(int slot){
        return blocks[slot];
    }

    public void moveRight(){
        blockData[4][1] = blockData[4][1] + 1;
    }

    public void moveLeft(){
        blockData[4][1] = blockData[4][1] - 1;
    }

    public void rotate(){
        if (blockData[4][2] != 1){
            for (int i = 0; i < 4; i++){
            int column = (blockData[i][0]*(int)Math.cos(Math.toRadians(90)))-(blockData[i][1]*(int)Math.sin(Math.toRadians(90)));
            int row = (blockData[i][0]*(int)Math.sin(Math.toRadians(90)))-(blockData[i][1]*(int)Math.cos(Math.toRadians(90)));
            blockData[i][0] = column;
            blockData[i][1] = row;
            }
            if (blockData[4][3] == 4)
                blockData[4][3] = 0;
            blockData[4][3] = blockData[4][3] + 1;
            updateBottomPieces();
        }
    }
    
    private boolean checkBottomMost(int slot){
        if (Math.max(Math.max(getBRow(0), getBRow(1)), 
            Math.max(getBRow(2), getBRow(3))) == getBRow(slot))
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
        if(blockData[slot][2] == 1)
            return true;
        return false;
    }

    public void updateBottomPieces(){
        switch(blockData[4][2]){
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
                if (blockData[4][3] == 1)
                    updateBottomIfIOrCubePiece();
                else if (blockData[4][3] == 2 || blockData[4][3] == 4){
                    updateBottomIfIOrCubePiece();
                    IfHasWIngs();
                }
                else
                    ifLOrJThirdRot();
                break;
            case 5:
            case 6:
                if(blockData[4][3] == 1 || blockData[4][3] == 3){
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
        if(blockData[4][2] == 4){
            blockData[0][2] = 1;
            blockData[1][2] = 0;
            blockData[2][2] = 1;
            blockData[3][2] = 1;
        }
        else if (blockData[4][2] == 3){
            blockData[0][2] = 1;
            blockData[1][2] = 1;
            blockData[2][2] = 0;
            blockData[3][2] = 1;
        }
    }

    private void updateBottomIfIOrCubePiece(){
        for (int i = 0; i < 4; i++)
            if (checkBottomMost(i))
                blockData[i][2] = 1;
            else
                blockData[i][2] = 0;
    }

    private void ifT(){
        for (int i = 0; i < 4; i++)
                if(getBCol(i) != 0)
                    blockData[i][2] = 1;
    }

    private void IfHasWIngs(){
        for (int i = 0; i < 4; i++)
            if(getBCol(i) != 0)
                blockData[i][2] = 1;
    }

    public void ifStackedSides(int bottom){
        for (int i = 0; i < 4; i++)
            if (getBCol(bottom) != getBCol(i) && getBRow(i) == 0)
                blockData[i][2] = 1;
    }

    public void newPiece(int x, int y) {
        Random rand = new Random();
        switch(rand.nextInt(7)){
            case 0: //I
                blocks = new Block [4];
                    for (int i = 0; i < 4; i++)
                        blocks[i] = new Block("\033[48;2;20;240;240m \033[0m");
                blockData = new int[][] {
                    {-1, 0, 0},
                    {0, 0, 0},
                    {1, 0, 0},
                    {2, 0, 0},
                    {x, y, 0, 1}
                };
                break;
            case 1: // Cube
                blocks = new Block [4];
                    for (int i = 0; i < 4; i++)
                        blocks[i] = new Block("\033[48;2;234;236;35m \033[0m");
                blockData = new int[][] {
                    {-1, 0, 0},
                    {0, 0, 0},
                    {-1, 1, 0},
                    {0, 1, 0},
                    {x, y, 1, 1}
                };
                break;
            case 2: // T
                blocks = new Block [4];
                    for (int i = 0; i < 4; i++)
                        blocks[i] = new Block("\033[48;2;180;0;158m \033[0m");
                blockData = new int[][] {
                    {-1, 0, 0},
                    {0, 0, 0},
                    {1, 0, 0},
                    {0, 1, 0},
                    {x, y, 2, 1}
                };
                break;
            case 3: // L
                blocks = new Block [4];
                    for (int i = 0; i < 4; i++)
                        blocks[i] = new Block("\033[48;2;252;127;0m \033[0m");
                blockData = new int[][] {
                    {-1, 0, 0},
                    {0, 0, 0},
                    {1, 0, 0},
                    {1, -1, 0},
                    {x, y, 3, 1}
                };
                break;
            case 4: // J
                blocks = new Block [4];
                    for (int i = 0; i < 4; i++)
                        blocks[i] = new Block("\033[48;2;88;51;255m \033[0m");
                blockData = new int[][] {
                    {-1, -1, 0},
                    {-1, 0, 0},
                    {0, 0, 0},
                    {1, 0, 0},
                    {x, y, 4, 1}
                };
                break;
            
            case 5: // Z
                blocks = new Block [4];
                    for (int i = 0; i < 4; i++)
                        blocks[i] = new Block("\033[48;2;252;57;31m \033[0m");
                blockData = new int[][] {
                    {-1, 0, 0},
                    {0, 0, 0},
                    {0, 1, 0},
                    {1, 1, 0},
                    {x, y, 5, 1}
                };
                break;
            case 6: // S
                blocks = new Block [4];
                    for (int i = 0; i < 4; i++)
                        blocks[i] = new Block("\033[48;2;49;231;34m \033[0m");
                blockData = new int[][] {
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
