package Tetris;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException{
        WindowsRawMode window = new WindowsRawMode();
        window.enableRawMode();
        Container display = new Container();
        
        System.out.println(display.toString());
        while (true){
            int key = System.in.read();
            switch (key){
                case 97:
                    display.moveLeft();
                case 100:
                    display.moveRight();
                case 119:
                    display.rotate();
                case 115:
                    display.drop();
            }
            display.updateDisplay();
            System.out.print(display.toString());
        }
    }
}