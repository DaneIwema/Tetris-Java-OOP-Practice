package Tetris;

import java.util.Random;

class Piece {

    private Tetrominoe [] pieces;
    int [] coord;
    int [][] pieceCoord;
    
    public Piece() {
        coord = new int [] {3, 6};
        newPiece();
    }

    public int getX(){
        return coord[1];
    }

    public int getY(){
        return coord[0];
    }

    public int getTX(int slot){
        return pieceCoord[slot][0];
    }

    public int getTY(int slot){
        return pieceCoord[slot][1];
    }

    public Tetrominoe getTetrominoe(int slot){
        return pieces[slot];
    }

    public void moveRight(){
        coord[1] = coord[1] + 1;
    }

    public void moveLeft(){
        coord[1] = coord[1] - 1;
    }

    public void moveDown(){
        coord[0] = coord[0] + 1;
    }

    public void rotate(){
        for (int i = 0; i < 4; i++){
            int column = (pieceCoord[i][0]*(int)Math.cos(Math.toRadians(90)))-(pieceCoord[i][1]*(int)Math.sin(Math.toRadians(90)));
            int row = (pieceCoord[i][0]*(int)Math.sin(Math.toRadians(90)))-(pieceCoord[i][1]*(int)Math.cos(Math.toRadians(90)));
            pieceCoord[i][0] = column;
            pieceCoord[i][1] = row;
        }
    }

    public void newPiece() {
        Random rand = new Random();
        switch(rand.nextInt(7)){
            case 0: //I
                pieces = new Tetrominoe [] {
                    new Tetrominoe(1),
                    new Tetrominoe(1),
                    new Tetrominoe(1),
                    new Tetrominoe(1)
                };
                pieceCoord = new int[][] {
                    {-1, 0},
                    {0, 0},
                    {1, 0},
                    {2, 0}
                };
                break;
            case 1: // L
                pieces = new Tetrominoe [] {
                    new Tetrominoe(2),
                    new Tetrominoe(2),
                    new Tetrominoe(2),
                    new Tetrominoe(2)
                };
                pieceCoord = new int[][] {
                    {0, -1},
                    {0, 0},
                    {0, 1},
                    {1, 1}
                };
                break;
            case 2: // J
                pieces = new Tetrominoe [] {
                    new Tetrominoe(3),
                    new Tetrominoe(3),
                    new Tetrominoe(3),
                    new Tetrominoe(3)
                };
                pieceCoord = new int[][] {
                    {0, -1},
                    {0, 0},
                    {-1, 1},
                    {0, 1}
                };
                break;
            case 3: // Z
                pieces = new Tetrominoe [] {
                    new Tetrominoe(4),
                    new Tetrominoe(4),
                    new Tetrominoe(4),
                    new Tetrominoe(4)
                };
                pieceCoord = new int[][] {
                    {-1, 0},
                    {0, 0},
                    {0, 1},
                    {1, 1}
                };
                break;
            case 4: // S
                pieces = new Tetrominoe [] {
                    new Tetrominoe(5),
                    new Tetrominoe(5),
                    new Tetrominoe(5),
                    new Tetrominoe(5)
                };
                pieceCoord = new int[][] {
                    {0, 0},
                    {1, 0},
                    {-1, 1},
                    {0, 1}
                };
                break;
            case 5: // Cube
                pieces = new Tetrominoe [] {
                    new Tetrominoe(6),
                    new Tetrominoe(6),
                    new Tetrominoe(6),
                    new Tetrominoe(6)
                };
                pieceCoord = new int[][] {
                    {-1, 0},
                    {0, 0},
                    {-1, 1},
                    {0, 1}
                };
                break;
            case 6: // T
                pieces = new Tetrominoe [] {
                    new Tetrominoe(7),
                    new Tetrominoe(7),
                    new Tetrominoe(7),
                    new Tetrominoe(7)
                };
                pieceCoord = new int[][] {
                    {-1, 0},
                    {0, 0},
                    {1, 0},
                    {0, 1}
                };
                break;
        }
    }
}
