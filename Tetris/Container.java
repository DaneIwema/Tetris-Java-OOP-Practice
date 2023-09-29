package Tetris;

import java.util.Random;

public class Container {
    private Tetrominoe [][] display;
    Random rand = new Random();
    int bufferWidth;
    int bufferHeight;
    Tetrominoe currPiece;

    public Container(int width, int height) {
        bufferWidth = (width/2)-6;
        bufferHeight = (height/2)-11;
        StringBuilder str = new StringBuilder();
        display = new Tetrominoe[12][26];
        for (int i = 0; i < 22; i++){
            for (int j = 0; j < 12; j++){
                if (j == 0 || j == 11){
                    //sides
                    str.append("\033[48;2;129;131;131m \033[0m");
                }
                else if (i == 21 ){
                    //bottom row
                    str.append("\033[48;2;129;131;131m-\033[0m");
                }
                else{
                    //middle
                    str.append("\033[48;2;203;204;205m \033[0m");
                }
            }
            str.append("\n");
        }
        System.out.print(str);
    }

    public void addPiece() {
        switch(rand.nextInt(6)){
            case 0:
                currPiece = new TPiece();
                break;
            case 1:
                currPiece = new IPiece();
                break;
            case 2:
                currPiece = new LPiece();
                break;
            case 3:
                currPiece = new JPiece();
                break;
            case 4:
                currPiece = new ZPiece();
                break;
            case 5:
                currPiece = new SPiece();
                break;
            case 6:
                currPiece = new CubePiece();
                break;
        }
    }
}
