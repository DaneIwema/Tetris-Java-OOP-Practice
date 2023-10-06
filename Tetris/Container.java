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
            if (piece.CheckIfBottomPiece(i) && 
                container[piece.getY() + piece.getTY(i)][piece.getX() + piece.getTX(i)] != null)
                return true;
        }
        return false;
    }

    private boolean checkBottomCollision(){
        for (int i = 0; i < 4; i++){
            if (piece.getY() + piece.getTY(i) == 19)
                return true;
            if (piece.CheckIfBottomPiece(i) && 
                container[piece.getY() + piece.getTY(i) + 1][piece.getX() + piece.getTX(i)] != null)
                return true;
        }
        return false;
    }

    private void updatePiece(){
        for (int i = 0; i < 4; i++){
            if (piece.CheckIfBottomPiece(i))
                piece.getTetrominoe(i).setColor("1");
            else
                piece.getTetrominoe(i).setColor("0");
        }
    }

    public String toString(){
        updatePiece();
        StringBuilder str = new StringBuilder();
        str.append("\033[2J");
        str.append("\033[H");
        str.append(" 0123456789\n");
        for (int i = 0; i < 20; i++){
            str.append("\033[48;2;129;131;131m*\033[0m");
            for (int j = 0; j < 10; j++){
                if (container[i][j] == null)
                    str.append("\033[48;2;203;204;205m \033[0m");
                else
                    str.append(container[i][j].getColor());
            }
            str.append("\033[48;2;129;131;131m*\033[0m");
            str.append(i);
            // if (i == 3) {
            //         str.append("   ");
            //         str.append(checkBottomCollision());
            //         str.append(" ");
            //         str.append(checkBottomCollisionAfterMove());
            //     }
            if (i == 4){
                str.append("   (");
                str.append(piece.getX() + piece.getTX(0));
                str.append(", ");
                str.append(piece.getY() + piece.getTY(0));
                str.append("), (");
                str.append(piece.getX() + piece.getTX(1));
                str.append(", ");
                str.append(piece.getY() + piece.getTY(1));
                str.append("), (");
                str.append(piece.getX() + piece.getTX(2));
                str.append(", ");
                str.append(piece.getY() + piece.getTY(2));
                str.append("), (");
                str.append(piece.getX() + piece.getTX(3));
                str.append(", ");
                str.append(piece.getY() + piece.getTY(3));
                str.append(")");
            }
            str.append("\n");
        }
        str.append("\033[48;2;129;131;131m************\033[0m");
        return str.toString();
    }
}
