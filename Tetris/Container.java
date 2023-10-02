package Tetris;

public class Container {
    Tetrominoe [][] container;
    Piece piece;

    public Container() {
        container = new Tetrominoe [20][10];
        piece = new Piece();
        placePiece();
    }
    
    private void clearDisplay(){
        for (int i = 0; i < 4; i++){
            container[piece.getY() + piece.getTY(i)][piece.getX() + piece.getTX(i)] = null;
        }
    }

    private void placePiece(){
        for (int i = 0; i < 4; i++){
            container[piece.getY() + piece.getTY(i)][piece.getX() + piece.getTX(i)] = piece.getTetrominoe(i);
        }
    }

    public void moveRight(){
        clearDisplay();
        piece.moveRight();
        placePiece();
    }

    public void moveLeft(){
        clearDisplay();
        piece.moveLeft();
        placePiece();
    }

    public void moveDown(){
        clearDisplay();
        piece.moveDown();
        placePiece();
    }

    public void rotate(){
        clearDisplay();
        piece.rotate();
        placePiece();
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("\033[2J");
        str.append("\033[H");
        for (int i = 0; i < 20; i++){
            str.append("\033[48;2;129;131;131m*\033[0m");
            for (int j = 0; j < 10; j++){
                if (container[i][j] == null)
                    str.append("\033[48;2;203;204;205m \033[0m");
                else
                    str.append(piece.getTetrominoe(0).getColor());
            }
            str.append("\033[48;2;129;131;131m*\033[0m");
            str.append("\n");
        }
        str.append("\033[48;2;129;131;131m************\033[0m");
        return str.toString();
    }
}
