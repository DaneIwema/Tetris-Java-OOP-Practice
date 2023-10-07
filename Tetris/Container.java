package Tetris;

public class Container {
    Node head;
    Node tail;
    Node curRow;
    Piece piece;
    int rowsCleared;

    public class Node {
        protected Node next; 
        protected Node prev;
        protected int rowId;
        protected Tetrominoe [] row;

        protected Node(int id, Node p){
            prev = p;
            rowId = id;
            row = new Tetrominoe [10];
        }
    }

    public Container() {
        head = new Node(0, null);
        Node curP = head;
        for (int i = 1; i < 21; i++){
            curP.next = new Node(i, curP);
                curP = curP.next;
        }
        tail = new Node(21, curP);
        curP.next = tail;
        piece = new Piece();
        curRow = head;
        updateDisplay();
    }

    private Node navigate(int target, Node curP){
        if (curP.rowId == target)
            return curP;
        if (curP.rowId < target)
            return navigate(target, curP.next);
        return navigate(target, curP.prev);
    }
    
    private void clearDisplay(){
        for (int i = 0; i < 4; i++){
            Node curP = navigate(piece.getY() + piece.getTY(i), curRow);
            curP.row[piece.getX() + piece.getTX(i)] = null;
        }
    }

    private void updateDisplay(){
        for (int i = 0; i < 4; i++){
            Node curP = navigate(piece.getY() + piece.getTY(i), curRow);
            curP.row[piece.getX() + piece.getTX(i)] = piece.getTetrominoe(i);
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
            Node curP = navigate(piece.getY() + piece.getTY(i), curRow);
            if (curP.row[piece.getX() + piece.getTX(i)] != null)
                return true;
        }
        return false;
    }

    private boolean checkRightCollisionAfterMove(){
        for (int i = 0; i < 4; i++){
            if (piece.getX() + piece.getTX(i) > 9)
                return true;
            Node curP = navigate(piece.getY() + piece.getTY(i), curRow);
            if (curP.row[piece.getX() + piece.getTX(i)] != null)
                return true;
        }
        return false;
    }

    private boolean checkBottomCollisionAfterMove(){
        for (int i = 0; i < 4; i++){
            if (piece.getY() + piece.getTY(i) > 19)
                return true;
            if (piece.checkBottom(i) && curRow.row[piece.getX() + piece.getTX(i)] != null)
                return true;
        }
        return false;
    }

    private boolean checkBottomCollision(){
        for (int i = 0; i < 4; i++){
            if (piece.checkBottom(i) && piece.getY() + piece.getTY(i) == 21)
                return true;
            Node curP = navigate(piece.getY() + piece.getTY(i), curRow);
            if (piece.checkBottom(i) && curP.next.row[piece.getX() + piece.getTX(i)] != null)
                return true;
        }
        return false;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("\033[2J");
        str.append("\033[H");

        Node curP = head;
        curP = curP.next;
        curP = curP.next;
        while (curP != null){
            str.append("\033[48;2;129;131;131m*\033[0m");
            for (int i = 0; i < 10; i++){
                if (curP.row[i] == null)
                    str.append("\033[48;2;203;204;205m \033[0m");
                else
                    str.append(curP.row[i].getColor());
            }
            str.append("\033[48;2;129;131;131m*\033[0m");
            if (curP.rowId == 6){
                str.append("    ");
                str.append(checkLeftCollisionAfterMove());
                str.append("     ");
                str.append(checkBottomCollision());
                str.append("     ");
                str.append(checkRightCollisionAfterMove());
            }
            str.append("\n");
            curP = curP.next;
        }
        str.append("\033[48;2;129;131;131m************\033[0m");
        return str.toString();
    }
}
