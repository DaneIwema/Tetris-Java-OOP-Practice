package Tetris;

public class Tetrominoe {
    protected String color;

    public Tetrominoe(String col){
        color = col;
    }

    public String getColor(){
        return color;
    }

    public void setColor(String i){
        color = i;
    }
}