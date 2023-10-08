package Tetris;

public class Block {
    protected String color;

    public Block(String col){
        color = col;
    }

    public String getColor(){
        return color;
    }

    public void setColor(String i){
        color = i;
    }
}