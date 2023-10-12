package Tetris;

import java.io.IOException;

public class Main extends Thread{

    public static Container display;
    public static WindowsRawMode window;

    public static void main(String[] args) throws IOException{
        window = new WindowsRawMode();
        window.enableRawMode();
        display = new Container();
        Main blockFalling = new Main();
        Main gameloop = new Main();
        gameloop.setName("1");
        blockFalling.setName("2");
        gameloop.start();
        blockFalling.start();
        while (true){
            int key = System.in.read();
            switch (key){
                case 'a':
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

    @Override
    public void run() {
        if (Thread.currentThread().getName() == "1")
            while (true){
                System.out.print(display.toString());
            }
        else if (Thread.currentThread().getName() == "2")
            while (true){
                
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                display.moveDown();
            }
        
    }
}