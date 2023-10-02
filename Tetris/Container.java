package Tetris;

public class Container {
    Node head;
    Node currRow;
    Piece currPiece;
    Piece prevPiece;

    private class Node {

		private Tetrominoe [] row;
		private Node next;
		
		private Node(Node n) {
            next = n;
			row = new Tetrominoe[10];
		}
	}

    public Container() {
        head = new Node(null);
        Node curP = head;
        for (int i = 1; i < 25; i++){
            curP.next = new Node(null);
            curP = curP.next;
        }
        currPiece = new Piece();
        prevPiece = currPiece;
        currRow = head;
        placePiece(head);
    }

    public void updateDisplay(){

    }
    
    private void clearDisplay(Node pos){
        pos.row[prevPiece.getX() + prevPiece.getTetrominoe(0).getX()] = null;
        for (int i = 1; i < 4; i++){
            if(prevPiece.getTetrominoe(i).getY() > prevPiece.getTetrominoe(i - 1).getY())
                pos = pos.next;
            pos.row[prevPiece.getX() + prevPiece.getTetrominoe(i).getX()] = null;
        }
    }

    private void placePiece(Node pos){
        pos.row[currPiece.getX() + currPiece.getTetrominoe(0).getX()] = currPiece.getTetrominoe(0);
        for (int i = 1; i < 4; i++){
            if(currPiece.getTetrominoe(i).getY() > currPiece.getTetrominoe(i - 1).getY())
                pos = pos.next;
            pos.row[currPiece.getX() + currPiece.getTetrominoe(i).getX()] = currPiece.getTetrominoe(i);
        }
    }

    public void moveRight(){
        clearDisplay(currRow);
        currPiece.moveRight();
        placePiece(currRow);
    }

    public void moveLeft(){
        clearDisplay(currRow);
        currPiece.moveLeft();
        placePiece(currRow);
    }

    public void drop(){
        clearDisplay(currRow);
        currRow = currRow.next;
        placePiece(currRow);
    }

    public void rotate(){
        clearDisplay(currRow);
        currPiece.rotate();
        placePiece(currRow);
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("\033[2J");
        str.append("\033[H");
        Node currP = head;
        for (int i = 0; i < 24; i++){
            str.append("\033[48;2;129;131;131m*\033[0m");
            for (int j = 0; j < 10; j++){
                if (currP.row[j] == null)
                    str.append("\033[48;2;203;204;205m \033[0m");
                else
                    str.append(currP.row[j].getColor());
            }
            str.append("\033[48;2;129;131;131m*\033[0m");
            str.append("\n");
            currP = currP.next;
        }
        str.append("\033[48;2;129;131;131m************\033[0m");
        return str.toString();
    }
}
