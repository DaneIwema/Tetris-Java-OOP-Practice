package Tetris;

public class Container {
    Tetrominoe [][] container;
    Piece piece;

    public Container() {
        container = new Tetrominoe [20][10];
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
        updateDisplay();
    }

    public void moveLeft(){
        clearDisplay();
        piece.moveLeft();
        updateDisplay();
    }

    public void moveDown(){
        if (checkPlacement()){
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
        updateDisplay();
    }

    private boolean checkPlacement(){
        for (int i = 0; i < 4; i++){
            if (piece.getY() + piece.getTY(i) == 19)
                return true;
            else if(container[piece.getY() + piece.getTY(i)][piece.getX() + piece.getTX(i)] == null)
                return true;
        }
        return false;
    }

    public void printCheck(){
        for (int i = 0; i < 4; i++){
            if (piece.getY() + piece.getTY(i) == 19)
                System.out.print(1);
        }
    }

    public void checkLine(){

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
