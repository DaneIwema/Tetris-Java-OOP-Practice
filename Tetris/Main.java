package Tetris;

import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException{
        System.out.print("\033[2J");
        WindowsRawMode window = new WindowsRawMode();
        window.enableRawMode();
        Container display = new Container();
        System.out.println(display.toString());
        boolean piecePlaced = true;
        Tetrominoe currPiece = newPiece();
        while (true){
            if(piecePlaced){
                piecePlaced = false;
                currPiece = newPiece();
            }
            int key = System.in.read();
            if (key == 97)
                currPiece.moveLeft();
            if (key == 100)
                currPiece.moveRight();
            if (key == 119)
                currPiece.rotate();
            if (key == 115)
                currPiece.drop();
            display.upDatePiece(currPiece);
            System.out.print(display.toString());   
        }


    //     int [][][] rotation = {{{4, 5, 6, 7},{2, 5, 9, 11},{4, 5, 6, 7},{2, 5, 9, 11}}, {{2, 3, 5, 9},{4, 5, 6, 10},{2, 5, 8, 9},{1, 4, 5, 6}},
    // {{1, 2, 5, 9},{3, 4, 5, 6},{2, 5, 9, 10},{4, 5, 6, 8}}, {{2, 3, 4, 5},{2, 5, 6, 10},{2, 3, 4, 5},{2, 5, 6, 10}}, 
    // {{1, 2, 5, 6},{3, 5, 6, 9},{1, 2, 5, 6},{3, 5, 6, 9}}, {{1, 2, 4, 5},{1, 2, 4, 5},{1, 2, 4, 5},{1, 2, 4, 5}}, {{2, 4, 5, 6},{2, 5, 6, 9},{4, 5, 6, 9},{2, 4, 5, 9}}};
    //     System.out.println(rotation[6][2][0]);
    //     System.out.println(rotation[6][2][1]);
    //     System.out.println(rotation[6][2][2]);
    //     System.out.println(rotation[6][2][3]);
    }

    public static Tetrominoe newPiece() {
        Random rand = new Random();
        switch(rand.nextInt(6)){
            case 0:
                return new TPiece();
            case 1:
                return new IPiece();
            case 2:
                return new LPiece();
            case 3:
                return new JPiece();
            case 4:
                return new ZPiece();
            case 5:
                return new SPiece();
        }
        return new CubePiece();
    }
}