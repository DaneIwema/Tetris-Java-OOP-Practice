package Tetris;

public abstract class Tetrominoe {
    //[0] and [[1] represent the piece's x and y coordinates
    //[2] is the piece's rotation
    //[3] is the type of piece 1-7
    protected int [] pieceData;
    protected String color;

    public int [] getRotation(){
        int [][][] rotation = {{{4, 5, 6, 7},{2, 5, 9, 11},{4, 5, 6, 7},{2, 5, 9, 11}}, {{2, 3, 5, 9},{4, 5, 6, 10},{2, 5, 8, 9},{1, 4, 5, 6}},
    {{1, 2, 5, 9},{3, 4, 5, 6},{2, 5, 9, 10},{4, 5, 6, 8}}, {{2, 3, 4, 5},{2, 5, 6, 10},{2, 3, 4, 5},{2, 5, 6, 10}}, 
    {{1, 2, 5, 6},{3, 5, 6, 9},{1, 2, 5, 6},{3, 5, 6, 9}}, {{1, 2, 4, 5},{1, 2, 4, 5},{1, 2, 4, 5},{1, 2, 4, 5}}, {{2, 4, 5, 6},{2, 5, 6, 9},{4, 5, 6, 9},{2, 4, 5, 9}}};
        return rotation[pieceData[3]][pieceData[2]];
    }

    public int getX(){
        return pieceData[0];
    }

    public int getY(){
        return pieceData[1];
    }

    public String getColor(){
        return color;
    }

    public void moveRight(){
        pieceData[0]++;
    }

    public void moveLeft(){
        pieceData[0]--;
    }

    public void rotate(){
        if (pieceData[2] == 4)
            pieceData[2] = 0;
        pieceData[2]++;
    }

    public void drop(){
        pieceData[1]++;
    }

    // IPiece = new int[][] {{4, 5, 6, 7},{2, 5, 9, 11},{4, 5, 6, 7},{2, 5, 9, 11}};
    // LPiece = new int[][] {{2, 3, 5, 9},{4, 5, 6, 10},{2, 5, 8, 9},{1, 4, 5, 6}};
    // JPiece = new int[][] {{1, 2, 5, 9},{3, 4, 5, 6},{2, 5, 9, 10},{4, 5, 6, 8}};
    // ZPiece = new int[][] {{2, 3, 4, 5},{2, 5, 6, 10},{2, 3, 4, 5},{2, 5, 6, 10}};
    // SPiece = new int[][] {{1, 2, 5, 6},{3, 5, 6, 9},{1, 2, 5, 6},{3, 5, 6, 9}};
    // CubePiece = new int[][] {{1, 2, 4, 5},{1, 2, 4, 5},{1, 2, 4, 5},{1, 2, 4, 5}};
    // TPiece = new int[][] {{2, 4, 5, 6},{2, 5, 6, 9},{4, 5, 6, 9},{2, 4, 5, 9}};

}

class IPiece extends Tetrominoe {
    public IPiece (){
        super.color = "\033[48;2;20;240;240m \033[0m";
        pieceData = new int[] {2, 6, 1, 0};
    }
}

class LPiece extends Tetrominoe{
    public LPiece (){
        super.color = "\033[48;2;252;127;0m \033[0m";
        pieceData = new int[] {2, 6, 1, 1};
    }
}

class JPiece extends Tetrominoe {
    public JPiece (){
        super.color = "\033[48;2;88;51;255m \033[0m";
        pieceData = new int[] {2, 6, 1, 2};
    }
}

class ZPiece extends Tetrominoe {
    public ZPiece (){
        super.color = "\033[48;2;252;57;31m \033[0m";
        pieceData = new int[] {2, 6, 1, 3};
    }
}

class SPiece extends Tetrominoe {
    public SPiece (){
        super.color = "\033[48;2;49;231;34m \033[0m";
        pieceData = new int[] {2, 6, 1, 4};
    }
}

class CubePiece extends Tetrominoe {
    public CubePiece (){
        super.color ="\033[48;2;234;236;35m \033[0m";
        pieceData = new int[] {2, 6, 1, 5};
    }
}

class TPiece extends Tetrominoe {
    public TPiece (){
        super.color ="\033[48;2;180;0;158m \033[0m";
        pieceData = new int[] {2, 6, 1, 6};
    }
}

class fillerPiece extends Tetrominoe {
    public fillerPiece (){
        color = "\033[48;2;203;204;205m \033[0m";
    }
}

class outSidePiece extends Tetrominoe {
    public outSidePiece (){
        color = "\033[48;2;129;131;131m*\033[0m";
    }
}

class blankPiece extends Tetrominoe {
    public blankPiece (String col, int x, int y){
        color = col;
        pieceData = new int[] {x, y};
    }
}