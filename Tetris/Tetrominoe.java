package Tetris;

public class Tetrominoe {
    protected int [] pieceData;
    protected String color;

    public Tetrominoe(int type){
        switch(type){
            case 1:
                color = "\033[48;2;20;240;240m \033[0m"; //I
                break;
            case 2:
                color = "\033[48;2;252;127;0m \033[0m"; //L
                break;
            case 3:
                color = "\033[48;2;88;51;255m \033[0m"; //J
                break;
            case 4:
                color = "\033[48;2;252;57;31m \033[0m"; //Z
                break;
            case 5:
                color = "\033[48;2;49;231;34m \033[0m"; //S
                break;
            case 6:
                color = "\033[48;2;234;236;35m \033[0m"; //Cube
                break;
            case 7:
                color = "\033[48;2;180;0;158m \033[0m"; //T
                break;
        }
    }

    public String getColor(){
        return color;
    }
}