package Tetris;

import java.util.Random;

class Piece {

    private Tetrominoe [] pieces;
    int [][] pieceData;
    
    public Piece() {
        newPiece(new int [] {3, 6});
    }

    public int getX(){
        return pieceData[4][1];
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

    public void rotate(){
        for (int i = 0; i < 4; i++){
            int column = (pieceData[i][0]*(int)Math.cos(Math.toRadians(90)))-(pieceData[i][1]*(int)Math.sin(Math.toRadians(90)));
            int row = (pieceData[i][0]*(int)Math.sin(Math.toRadians(90)))-(pieceData[i][1]*(int)Math.cos(Math.toRadians(90)));
            pieceData[i][0] = column;
            pieceData[i][1] = row;
        }
    }

    public void newPiece(int [] coord) {
        Random rand = new Random();
        switch(rand.nextInt(7)){
            case 0: //I
                pieces = new Tetrominoe [] {
                    new Tetrominoe(1),
                    new Tetrominoe(1),
                    new Tetrominoe(1),
                    new Tetrominoe(1)
                };
                pieceData = new int[][] {
                    {-1, 0},
                    {0, 0},
                    {1, 0},
                    {2, 0},
                    coord
                };
                break;
            case 1: // L
                pieces = new Tetrominoe [] {
                    new Tetrominoe(2),
                    new Tetrominoe(2),
                    new Tetrominoe(2),
                    new Tetrominoe(2)
                };
                pieceData = new int[][] {
                    {0, -1},
                    {0, 0},
                    {0, 1},
                    {1, 1},
                    coord
                };
                break;
            case 2: // J
                pieces = new Tetrominoe [] {
                    new Tetrominoe(3),
                    new Tetrominoe(3),
                    new Tetrominoe(3),
                    new Tetrominoe(3)
                };
                pieceData = new int[][] {
                    {0, -1},
                    {0, 0},
                    {-1, 1},
                    {0, 1},
                    coord
                };
                break;
            case 3: // Z
                pieces = new Tetrominoe [] {
                    new Tetrominoe(4),
                    new Tetrominoe(4),
                    new Tetrominoe(4),
                    new Tetrominoe(4)
                };
                pieceData = new int[][] {
                    {-1, 0},
                    {0, 0},
                    {0, 1},
                    {1, 1},
                    coord
                };
                break;
            case 4: // S
                pieces = new Tetrominoe [] {
                    new Tetrominoe(5),
                    new Tetrominoe(5),
                    new Tetrominoe(5),
                    new Tetrominoe(5)
                };
                pieceData = new int[][] {
                    {0, 0},
                    {1, 0},
                    {-1, 1},
                    {0, 1},
                    coord
                };
                break;
            case 5: // Cube
                pieces = new Tetrominoe [] {
                    new Tetrominoe(6),
                    new Tetrominoe(6),
                    new Tetrominoe(6),
                    new Tetrominoe(6)
                };
                pieceData = new int[][] {
                    {-1, 0},
                    {0, 0},
                    {-1, 1},
                    {0, 1},
                    coord
                };
                break;
            case 6: // T
                pieces = new Tetrominoe [] {
                    new Tetrominoe(7),
                    new Tetrominoe(7),
                    new Tetrominoe(7),
                    new Tetrominoe(7)
                };
                pieceData = new int[][] {
                    {-1, 0},
                    {0, 0},
                    {1, 0},
                    {0, 1},
                    coord
                };
                break;
        }
    }
}
