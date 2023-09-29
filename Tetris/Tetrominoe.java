package Tetris;

public abstract class Tetrominoe {
    protected int [][] rotation;
    protected String color;
    

    public int [] getRotation(int pos){
        return rotation[pos];
    }

    public String getColor(){
        return color;
    }
}

class IPiece extends Tetrominoe {
    public IPiece (){
        super.color = "\033[48;2;20;240;240m \033[0m";
        super.rotation = new int[][] {{4, 5, 6, 7},{2, 5, 9, 11},{4, 5, 6, 7},{2, 5, 9, 11}};
    }
}

class LPiece extends Tetrominoe{
    public LPiece (){
        super.color = "\033[48;2;252;127;0m \033[0m";
        super.rotation = new int[][] {{2, 3, 5, 9},{4, 5, 6, 10},{2, 5, 8, 9},{1, 4, 5, 6}};
    }
}

class JPiece extends Tetrominoe {
    public JPiece (){
        super.color = "\033[48;2;88;51;255m \033[0m";
        super.rotation = new int[][] {{1, 2, 5, 9},{3, 4, 5, 6},{2, 5, 9, 10},{4, 5, 6, 8}};
    }
}

class ZPiece extends Tetrominoe {
    public ZPiece (){
        super.color = "\033[48;2;252;57;31m \033[0m";
        super.rotation = new int[][] {{2, 3, 4, 5},{2, 5, 6, 10},{2, 3, 4, 5},{2, 5, 6, 10}};
    }
}

class SPiece extends Tetrominoe {
    public SPiece (){
        super.color = "\033[48;2;49;231;34m \033[0m";
        super.rotation = new int[][] {{1, 2, 5, 6},{3, 5, 6, 9},{1, 2, 5, 6},{3, 5, 6, 9}};
    }
}

class CubePiece extends Tetrominoe {
    public CubePiece (){
        super.color ="\033[48;2;234;236;35m \033[0m";
        super.rotation = new int[][] {{1, 2, 4, 5},{1, 2, 4, 5},{1, 2, 4, 5},{1, 2, 4, 5}};
    }
}

class TPiece extends Tetrominoe {
    public TPiece (){
        super.color ="\033[48;2;180;0;158m \033[0m";
        super.rotation = new int[][] {{2, 4, 5, 6},{2, 5, 6, 9},{4, 5, 6, 9},{2, 4, 5, 9}};
    }
}

class blankPiece extends Tetrominoe {
    public blankPiece (String col){
        color = col;
    }
}