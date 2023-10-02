package Tetris;

import java.util.Random;

class Piece {

    private Tetrominoe [] pieces;
    private int [] order;
    int column;
    
    public Piece() {
        pieces = newPiece();
        column = 5;
        order = new int[] {0, 1, 2, 3};
    }

    public int getX(){
        return column;
    }

    public Tetrominoe getTetrominoe(int slot){
        return pieces[slot];
    }

    public String getColor(int slot){
        return pieces[slot].getColor();
    }

    public void moveRight(){
        column++;
    }

    public void moveLeft(){
        column--;
    }

    public void rotate(){
        for (int i = 0; i < 4; i++)
        pieces[i].rotate();
    }

    public int [] getOrder(){
        return order;
    }

    public int [] reOrder(){
        int [] newOrder = new int [4]; 
        for (int i = 0; i < 4; i++){
            
        }
        return order;
    }

    public boolean checkCollision(){

        return true;
    }

    public Tetrominoe [] newPiece() {
        Random rand = new Random();
        switch(rand.nextInt(7)){
            case 0: //I
                return new Tetrominoe [] {
                    new Tetrominoe(1, -1, 0),
                    new Tetrominoe(1, 0, 0),
                    new Tetrominoe(1, 1, 0),
                    new Tetrominoe(1, 2, 0)
                };
            case 1: // L
                return new Tetrominoe [] {
                    new Tetrominoe(2, 0, -1),
                    new Tetrominoe(2, 0, 0),
                    new Tetrominoe(2, 0, 1),
                    new Tetrominoe(2, 1, 1)
                };
            case 2: // J
                return new Tetrominoe [] {
                    new Tetrominoe(3, 0, -1),
                    new Tetrominoe(3, 0, 0),
                    new Tetrominoe(3, -1, 1),
                    new Tetrominoe(3, 0, 1)
                };
            case 3: // Z
                return new Tetrominoe [] {
                    new Tetrominoe(4, -1, 0),
                    new Tetrominoe(4, 0, 0),
                    new Tetrominoe(4, 0, 1),
                    new Tetrominoe(4, 1, 1)
                };
            case 4: // S
                return new Tetrominoe [] {
                    new Tetrominoe(5, 0, 0),
                    new Tetrominoe(5, 1, 0),
                    new Tetrominoe(5, -1, 1),
                    new Tetrominoe(5, 0, 1)
                };
            case 5: // Cube
                return new Tetrominoe [] {
                    new Tetrominoe(6, -1, 0),
                    new Tetrominoe(6, 0, 0),
                    new Tetrominoe(6, -1, 1),
                    new Tetrominoe(6, 0, 1)
                };
        } // T
        return new Tetrominoe [] {
            new Tetrominoe(7, -1, 0),
            new Tetrominoe(7, 0, 0),
            new Tetrominoe(7, 1, 0),
            new Tetrominoe(7, 0, 1)
        };
    }
}
