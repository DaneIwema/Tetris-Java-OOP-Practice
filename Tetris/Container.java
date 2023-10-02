package Tetris;

import java.util.Random;

public class Container {
    Node head;
    Tetrominoe [] currPiece;
    int [][] prevPiece;
    int column, row;

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
        for (int i = 0; i < 24; i++){
            curP.next = new Node(null);
            curP = curP.next;
        }
        newPiece();
        copyCurrPiece();
        column = 5;
        row = 3;
        curP = head;
    }

    public void updateDisplay(){
        Node currP = head;
        for (int i = 0; i < currPiece[0].getY();i++)
            currP = currP.next;
        for (int i = 1; i < 4; i++){
            if (prevPiece[i][1] > prevPiece[i-1][1])
                currP = currP.next;
            currP.row[column+prevPiece[i][0]] = null;
        }
        currP = currP.next;
        currP.row[column+prevPiece[0][0]] = currPiece[0];
        for (int i = 1; i < 4; i++){
            if (currPiece[i].getY() > currPiece[i-1].getY())
                currP = currP.next;
            currP.row[column+prevPiece[i][0]] = currPiece[i];
        }
    }

    public boolean checkRow(Node curp){
        return true;
    }

    public void newPiece() {
        Random rand = new Random();
        switch(rand.nextInt(7)){
            case 0: //I
                currPiece = new Tetrominoe [] {
                    new Tetrominoe(1, 0, -2),
                    new Tetrominoe(1, 0, -1),
                    new Tetrominoe(1, 0, 0),
                    new Tetrominoe(1, 0, 1)
                };
                break;
            case 1: // L
                currPiece = new Tetrominoe [] {
                    new Tetrominoe(2, 0, -1),
                    new Tetrominoe(2, 0, 0),
                    new Tetrominoe(2, 0, 1),
                    new Tetrominoe(2, 1, 1)
                };
                break;
            case 2: // J
                currPiece = new Tetrominoe [] {
                    new Tetrominoe(3, 0, -1),
                    new Tetrominoe(3, 0, 0),
                    new Tetrominoe(3, 0, 1),
                    new Tetrominoe(3, -1, 1)
                };
                break;
            case 3: // Z
                currPiece = new Tetrominoe [] {
                    new Tetrominoe(4, -1, 0),
                    new Tetrominoe(4, 0, 0),
                    new Tetrominoe(4, 0, 1),
                    new Tetrominoe(4, 1, 1)
                };
                break;
            case 4: // S
                currPiece = new Tetrominoe [] {
                    new Tetrominoe(5, -1, 1),
                    new Tetrominoe(5, 0, 1),
                    new Tetrominoe(5, 0, 0),
                    new Tetrominoe(5, 1, 0)
                };
                break;
            case 5: // Cube
                currPiece = new Tetrominoe [] {
                    new Tetrominoe(5, -1, 0),
                    new Tetrominoe(5, 0, 0),
                    new Tetrominoe(5, -1, -1),
                    new Tetrominoe(5, 0, 1)
                };
                break;
            case 6: // T
                currPiece = new Tetrominoe [] {
                    new Tetrominoe(5, -1, 0),
                    new Tetrominoe(5, 0, 0),
                    new Tetrominoe(5, 1, 0),
                    new Tetrominoe(5, 0, 1)
                };
                break;
        }
    }

    public void moveRight(){
        copyCurrPiece();
        for (int i = 0; i < 4; i++){
            currPiece[i].setX(currPiece[i].getX()+1);
        }
    }

    public void moveLeft(){
        copyCurrPiece();
        for (int i = 0; i < 4; i++){
            currPiece[i].setX(currPiece[i].getX()-1);
        }
    }

    public void drop(){
        copyCurrPiece();
        for (int i = 0; i < 4; i++){
            currPiece[i].setY(currPiece[i].getY()+1);
        }
    }

    public void rotate(){
        copyCurrPiece();
        for (int i = 0; i < 4; i++){
            currPiece[i].rotate();
        }
    }

    public void copyCurrPiece(){
        prevPiece =  new int [][] {
            {currPiece[0].getX(), currPiece[0].getY()},
            {currPiece[1].getX(), currPiece[1].getY()},
            {currPiece[2].getX(), currPiece[2].getY()},
            {currPiece[3].getX(), currPiece[3].getY()}
        };
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("\033[2J");
        str.append("\033[H");
        Node currP = head;
        for (int i = 0; i < 20; i++){
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
