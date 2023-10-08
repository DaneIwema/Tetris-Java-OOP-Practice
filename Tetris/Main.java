package Tetris;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException{
        WindowsRawMode window = new WindowsRawMode();
        window.enableRawMode();
        Container display = new Container();
        while (true){
            System.out.print(display.toString());
            int key = System.in.read();
            switch (key){
                case 97:
                    display.moveLeft();
                    break;
                case 100:
                    display.moveRight();
                    break;
                case 119:
                    display.rotate();
                    break;
                case 115:
                    display.moveDown();
                    break;
                case 32:
                    display.savePiece();
                    break;
            }
        }
    }
}