package Tetris;

public class Container {
    Node head;
    Node tail;
    Node curRow;
    Piece [] pieces;
    Block [][] piecesData;
    int rowsCleared;
    boolean pieceHeld;

    public class Node {
        protected Node next; 
        protected Node prev;
        protected Block [] row;

        protected Node(Node p){
            prev = p;
            row = new Block [10];
        }
    }

    public Container() {
        head = new Node(null);
        Node curP = head;
        for (int i = 1; i < 22; i++){
            curP.next = new Node(curP);
            curP = curP.next;
        }
        tail = curP;
        pieces = new Piece [] {null, new Piece(), new Piece()};
        curRow = tail.prev.prev;
        pieceHeld = false;
        updateDisplay();
    }

    private Node navigate(int target, Node curP){
        if (target == 0)
            return curP;
        if (target < 0)
            return navigate(target + 1, curP.next);
        return navigate(target - 1, curP.prev);
    }
    
    private void clearDisplay(){
        for (int i = 0; i < 4; i++){
            Node curP = navigate(pieces[1].getTY(i), curRow);
            curP.row[pieces[1].getX() + pieces[1].getTX(i)] = null;
        }
    }

    private void updateDisplay(){
        for (int i = 0; i < 4; i++){
            Node curP = navigate(pieces[1].getTY(i), curRow);
            curP.row[pieces[1].getX() + pieces[1].getTX(i)] = pieces[1].getTetrominoe(i);
        }
    }

    public void savePiece(){
        if(!pieceHeld){
            clearDisplay();
            pieces[1].setRotation(1);
            pieces[0] = pieces[1];
            pieces[1] = pieces[2];
            pieces[2] = new Piece();
            curRow = tail.prev.prev;
            pieceHeld = true;
        }
        else {
            int [] coord = new int [] {pieces[1].getX(), pieces[1].getY()};
            Piece temp = pieces[0];
            pieces[0].setX(coord[0]);
            pieces[0].setY(coord[1]);
            pieces[0] = pieces[1];
            clearDisplay();
            pieces[1].setRotation(1);
            pieces[1] = temp;
            
        }
         
        updateDisplay();
    }

    public void moveRight(){
        clearDisplay();
        pieces[1].moveRight();
        while (checkRightCollisionAfterMove()){
            pieces[1].moveLeft();
        }
        updateDisplay();
    }

    public void moveLeft(){
        clearDisplay();
        pieces[1].moveLeft();
        while (checkLeftCollisionAfterMove()){
            pieces[1].moveRight();
        }
        updateDisplay();
    }

    public void moveDown(){
        if (checkBottomCollision()){
            curRow = tail.prev.prev;
            pieces[1] = pieces[2];
            pieces[2] = new Piece();
            updateDisplay();
            checkRowCompletion();
        }
        else {
            clearDisplay();
            curRow = curRow.prev;
            pieces[1].moveDown();
            updateDisplay();
        }
    }

    public void rotate(){
        clearDisplay();
        pieces[1].rotate();
        while (checkCollisions()){
            pieces[1].rotate();
        }
        updateDisplay();
    }

    private boolean checkLeftCollisionAfterMove(){
        for (int i = 0; i < 4; i++){
            if (pieces[1].getX() + pieces[1].getTX(i) < 0)
                return true;
            Node curP = navigate(pieces[1].getTY(i), curRow);
            if (curP.row[pieces[1].getX() + pieces[1].getTX(i)] != null)
                return true;
        }
        return false;
    }

    private boolean checkRightCollisionAfterMove(){
        for (int i = 0; i < 4; i++){
            if (pieces[1].getX() + pieces[1].getTX(i) > 9)
                return true;
            Node curP = navigate(pieces[1].getTY(i), curRow);
            if (curP.row[pieces[1].getX() + pieces[1].getTX(i)] != null)
                return true;
        }
        return false;
    }

    private boolean checkCollisions(){
        for (int i = 0; i < 4; i++){
            if (pieces[1].getX() + pieces[1].getTX(i) < 0)
                pieces[1].moveRight();
            if (pieces[1].getX() + pieces[1].getTX(i) > 9)
                pieces[1].moveLeft();
            while (pieces[1].getY() + pieces[1].getTY(i) > 21){
                pieces[1].moveUp();
                curRow = curRow.next;
            }
            Node curP = navigate(pieces[1].getTY(i), curRow);
            if (pieces[1].checkBottom(i) && curP.row[pieces[1].getX() + pieces[1].getTX(i)] != null)
                return true;
        }
        return false;
    }

    private boolean checkBottomCollision(){
        for (int i = 0; i < 4; i++){
            if (pieces[1].checkBottom(i) && pieces[1].getY() + pieces[1].getTY(i) == 21)
                return true;
            Node curP = navigate(pieces[1].getTY(i), curRow);
            if (pieces[1].checkBottom(i) && curP.prev.row[pieces[1].getX() + pieces[1].getTX(i)] != null)
                return true;
        }
        return false;
    }

    private void checkRowCompletion(){
        boolean [] fullRows = new boolean[4];
        for (int i = 0; i < 4; i++){
            if (fullRows[pieces[i].getY() + 2] == false)
                fullRows[pieces[i].getY() + 2] = true;
        }
        clearLine(navigate(pieces[1].getTY(i), curRow));
    }

    private void clearLine(Node delete){
        for (int i = 0; i < 10; i ++){
            if (delete.row[i] == null)
                return;
        }

    }

    private void sideScreenUpdate(){
        piecesData = new Block[6][4];
        for (int i = 0; i < 4; i++){
            piecesData[pieces[2].getTY(i) + 1][pieces[2].getTX(i) + 1] = pieces[2].getTetrominoe(i);
        }
        if (pieces[0] != null)
            for (int i = 0; i < 4; i++){
                piecesData[pieces[0].getTY(i) + 4][pieces[0].getTX(i) + 1] = pieces[0].getTetrominoe(i);
            }
    }

    public String toString(){
        sideScreenUpdate();
        int countRow = 0;
        StringBuilder str = new StringBuilder();
        str.append("\033[2J");
        str.append("\033[H");
        Node curP = tail.prev.prev;
        while (curP != null){
            str.append("\033[48;2;129;131;131m*\033[0m");
            for (int i = 0; i < 10; i++){
                if (curP.row[i] == null)
                    str.append("\033[48;2;203;204;205m \033[0m");
                else
                    str.append(curP.row[i].getColor());
            }
            str.append("\033[48;2;129;131;131m*\033[0m");
            switch(countRow){
                case 0:
                    str.append(" \033[48;2;129;131;131m**NEXT**\033[0m");
                    break;
                case 1:
                    str.append(" \033[48;2;129;131;131m*\033[0m");
                    str.append("\033[48;2;203;204;205m \033[0m");
                    for (int i = 0; i < 4; i++){
                        if (piecesData[0][i] == null)
                            str.append("\033[48;2;203;204;205m \033[0m");
                        else
                            str.append(piecesData[0][i].getColor());
                    }
                    str.append("\033[48;2;203;204;205m \033[0m");
                    str.append("\033[48;2;129;131;131m*\033[0m");
                    break;
                case 2:
                    str.append(" \033[48;2;129;131;131m*\033[0m");
                    str.append("\033[48;2;203;204;205m \033[0m");
                    for (int i = 0; i < 4; i++){
                        if (piecesData[1][i] == null)
                            str.append("\033[48;2;203;204;205m \033[0m");
                        else
                            str.append(piecesData[1][i].getColor());
                    }
                    str.append("\033[48;2;203;204;205m \033[0m");
                    str.append("\033[48;2;129;131;131m*\033[0m");
                    break;
                case 3:
                    str.append(" \033[48;2;129;131;131m*\033[0m");
                    str.append("\033[48;2;203;204;205m \033[0m");
                    for (int i = 0; i < 4; i++){
                        if (piecesData[2][i] == null)
                            str.append("\033[48;2;203;204;205m \033[0m");
                        else
                            str.append(piecesData[2][i].getColor());
                    }
                    str.append("\033[48;2;203;204;205m \033[0m");
                    str.append("\033[48;2;129;131;131m*\033[0m");
                    break;
                case 4:
                    str.append(" \033[48;2;129;131;131m********\033[0m");
                    break;
                case 6:
                    str.append(" \033[48;2;129;131;131m**HELD**\033[0m");
                    break;
                case 7:
                    str.append(" \033[48;2;129;131;131m*\033[0m");
                    str.append("\033[48;2;203;204;205m \033[0m");
                    for (int i = 0; i < 4; i++){
                        if (piecesData[3][i] == null)
                            str.append("\033[48;2;203;204;205m \033[0m");
                        else
                            str.append(piecesData[3][i].getColor());
                    }
                    str.append("\033[48;2;203;204;205m \033[0m");
                    str.append("\033[48;2;129;131;131m*\033[0m");
                    break;
                case 8:
                    str.append(" \033[48;2;129;131;131m*\033[0m");
                    str.append("\033[48;2;203;204;205m \033[0m");
                    for (int i = 0; i < 4; i++){
                        if (piecesData[4][i] == null)
                            str.append("\033[48;2;203;204;205m \033[0m");
                        else
                            str.append(piecesData[4][i].getColor());
                    }
                    str.append("\033[48;2;203;204;205m \033[0m");
                    str.append("\033[48;2;129;131;131m*\033[0m");
                    break;
                case 9:
                    str.append(" \033[48;2;129;131;131m*\033[0m");
                    str.append("\033[48;2;203;204;205m \033[0m");
                    for (int i = 0; i < 4; i++){
                        if (piecesData[5][i] == null)
                            str.append("\033[48;2;203;204;205m \033[0m");
                        else
                            str.append(piecesData[5][i].getColor());
                    }
                    str.append("\033[48;2;203;204;205m \033[0m");
                    str.append("\033[48;2;129;131;131m*\033[0m");
                    break;
                case 10:
                    str.append(" \033[48;2;129;131;131m********\033[0m");
                    break;
                case 12:
                    str.append(" Controls:");
                    break;
                case 13:
                    str.append(" A: Move Left");
                    break;
                case 14:
                    str.append(" D: Move Right");
                    break;
                case 15:
                    str.append(" S: Move Down");
                    break;
                case 16:
                    str.append(" W: Rotate");
                    break;
                case 17:
                    str.append(" Space: Save/Swap");
                    break;
            }
            str.append("\n");
            curP = curP.prev;
            countRow++;
        }
        str.append("\033[48;2;129;131;131m************\033[0m");
        return str.toString();
    }
}
