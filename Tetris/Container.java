package Tetris;

public class Container {
    Tetrominoe [][] container;
    Piece piece;
    Piece pieceTwo;

    public Container() {
        container = new Tetrominoe [24][10];
        piece = new Piece(5, 2, 5);
        pieceTwo = new Piece(5, 8, 6);
        updateDisplay();
    }
    
    private void clearDisplay(){
        for (int i = 0; i < 4; i++){
            container[piece.getY() + piece.getTY(i)][piece.getX() + piece.getTX(i)] = null;
        }
        for (int i = 0; i < 4; i++){
            container[pieceTwo.getY() + pieceTwo.getTY(i)][pieceTwo.getX() + pieceTwo.getTX(i)] = null;
        }
    }

    private void updateDisplay(){
        for (int i = 0; i < 4; i++){
            container[piece.getY() + piece.getTY(i)][piece.getX() + piece.getTX(i)] = piece.getTetrominoe(i);
        }
        for (int i = 0; i < 4; i++){
            container[pieceTwo.getY() + pieceTwo.getTY(i)][pieceTwo.getX() + pieceTwo.getTX(i)] = pieceTwo.getTetrominoe(i);
        }
    }

    public void moveRight(){
        clearDisplay();
        piece.moveRight();
        while (checkRightCollisionAfterMove()){
            piece.moveLeft();
        }
        updateDisplay();
    }

    public void moveLeft(){
        clearDisplay();
        piece.moveLeft();
        while (checkLeftCollisionAfterMove()){
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
        pieceTwo.rotate();
        while (checkRightCollisionAfterMove()){
            piece.moveLeft();
        }
        while (checkLeftCollisionAfterMove()){
            piece.moveRight();
        }
        while (checkBottomCollisionAfterMove()){
            piece.moveUp();
        }
        updateDisplay();
    }

    private boolean checkLeftCollisionAfterMove(){
        for (int i = 0; i < 4; i++){
            if (piece.getX() + piece.getTX(i) < 0)
                return true;
                if (piece.checkLeftMost(i) && container[piece.getY() + piece.getTY(i)][piece.getX() + piece.getTX(i)] != null)
                return false;
        }
        return false;
    }

    private boolean checkRightCollisionAfterMove(){
        for (int i = 0; i < 4; i++){
            if (piece.getX() + piece.getTX(i) > 9)
                return true;
            if (piece.checkRightMost(i) && container[piece.getY() + piece.getTY(i)][piece.getX() + piece.getTX(i)] != null)
                return true;
        }
        return false;
    }

    private boolean checkBottomCollisionAfterMove(){
        for (int i = 0; i < 4; i++){
            if (piece.getY() + piece.getTY(i) > 19)
                return true;
            if (piece.checkBottom(i) && 
                container[piece.getY() + piece.getTY(i)][piece.getX() + piece.getTX(i)] != null)
                return true;
        }
        return false;
    }

    private boolean checkBottomCollision(){
        for (int i = 0; i < 4; i++){
            if (piece.getY() + piece.getTY(i) == 19)
                return true;
            if (piece.checkBottom(i) && 
                container[piece.getY() + piece.getTY(i) + 1][piece.getX() + piece.getTX(i)] != null)
                return true;
        }
        return false;
    }

    private void updatePiece(){
        for (int i = 0; i < 4; i++){
            if (piece.checkBottom(i))
                piece.getTetrominoe(i).setColor("1");
            else
                piece.getTetrominoe(i).setColor("0");
        }
        for (int i = 0; i < 4; i++){
            if (pieceTwo.checkBottom(i))
                pieceTwo.getTetrominoe(i).setColor("1");
            else
                pieceTwo.getTetrominoe(i).setColor("0");
        }
    }

    public String toString(){
        updatePiece();
        StringBuilder str = new StringBuilder();
        str.append("\033[2J");
        str.append("\033[H");
        for (int i = 3; i < 20; i++){
            str.append("\033[48;2;129;131;131m*\033[0m");
            for (int j = 0; j < 10; j++){
                if (container[i][j] == null)
                    str.append("\033[48;2;203;204;205m \033[0m");
                else
                    str.append(container[i][j].getColor());
            }
            str.append("\033[48;2;129;131;131m*\033[0m");
            if (i == 5){
                str.append("   ");
                str.append(piece.getRotation());
            }
            str.append("\n");
        }
        str.append("\033[48;2;129;131;131m************\033[0m");
        return str.toString();
    }
}
