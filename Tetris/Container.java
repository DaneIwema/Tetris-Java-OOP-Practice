package Tetris;

public class Container {
    Tetrominoe [][] container;
    Piece piece;

    public Container() {
        container = new Tetrominoe [20][10];
        piece = new Piece(1);
        updateDisplay();
        piece = new Piece();
        updateDisplay();
    }
    
    private void clearDisplay(){
        for (int i = 0; i < 4; i++){
            container[piece.getY() + piece.getTY(i)][piece.getX() + piece.getTX(i)] = null;
        }
    }

    private void updateDisplay(){
        for (int i = 0; i < 4; i++){
            container[piece.getY() + piece.getTY(i)][piece.getX() + piece.getTX(i)] = piece.getTetrominoe(i);
        }
    }

    public void moveRight(){
        clearDisplay();
        piece.moveRight();
        while (checkRightCollision()){
            piece.moveLeft();
        }
        updateDisplay();
    }

    public void moveLeft(){
        clearDisplay();
        piece.moveLeft();
        while (checkLeftCollision()){
            piece.moveRight();
        }
        updateDisplay();
    }

    public void moveDown(){
        if (checkBottomCollision()){
            piece = new Piece();
            updateDisplay();
        }
        else {
            clearDisplay();
            piece.moveDown();
            updateDisplay();
        }
    }

    public void rotate(){
        clearDisplay();
        piece.rotate();
        while (checkBottomCollisionGreat()){
            piece.moveUp();
        }
        while (checkLeftCollision()){
            piece.moveRight();
        }
        while (checkRightCollision()){
            piece.moveLeft();
        }
        updateDisplay();
    }

    private boolean checkLeftCollision(){
        for (int i = 0; i < 4; i++){
            if (piece.getX() + piece.getTX(i) < 0)
                return true;
            if (container[piece.getY() + piece.getTY(i)][piece.getX() + piece.getTX(i)] != null)
                return true;
        }
        return false;
    }

    private boolean checkBottomCollision(){
        for (int i = 0; i < 4; i++){
            if (piece.getY() + piece.getTY(i) == 19)
                return true;
        }
        return false;
    }

    private boolean checkBottomCollisionGreat(){
        for (int i = 0; i < 4; i++){
            if (piece.getY() + piece.getTY(i) > 19)
                return true;
            if (container[piece.getY() + piece.getTY(i)][piece.getX() + piece.getTX(i)] != null)
                return true;
        }
        return false;
    }

    private boolean checkRightCollision(){
        for (int i = 0; i < 4; i++){
            if (piece.getX() + piece.getTX(i) > 9)
                return true;
            if (container[piece.getY() + piece.getTY(i)][piece.getX() + piece.getTX(i)] != null)
                return true;
        }
        return false;
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
                    str.append(container[i][j].getColor());
            }
            str.append("\033[48;2;129;131;131m*\033[0m");
            str.append("\n");
        }
        str.append("\033[48;2;129;131;131m************\033[0m");
        return str.toString();
    }
}
