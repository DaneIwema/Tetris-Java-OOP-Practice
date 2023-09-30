package Tetris;

public class Container {
    private Tetrominoe [][] display;

    public Container() {
        display = new Tetrominoe[24][12];
        for (int i = 0; i < 24; i++){
            for (int j = 0; j < 12; j++){
                if (j == 0|| j == 11)
                    display[i][j] = new outSidePiece();
                else if (i == 23)
                    display[i][j] = new outSidePiece();
                else
                    display[i][j] = new fillerPiece();
            }
        }
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        for (int i = 2; i < 24; i++){
            for (int j = 0; j < 12; j++){
                str.append(display[i][j].getColor());
            }
            str.append("\n");
        }
        return str.toString();
    }

    public void upDatePiece(Tetrominoe piece){
        for (int i = 0; i < 4; i++){
            switch(piece.getRotation()[i]){
                case 1:
                    display[piece.getY()-1][piece.getX()-1] = piece;
                    break;
                case 2:
                    display[piece.getY()-1][piece.getX()] = piece;
                    break;
                case 3:
                    display[piece.getY()-1][piece.getX()+1] = piece;
                    break;
                case 4:
                    display[piece.getY()][piece.getX()-1] = piece;
                    break;
                case 5:
                    display[piece.getY()][piece.getX()] = piece;
                    break;
                case 6:
                    display[piece.getY()][piece.getX()+1] = piece;
                    break;
                case 7:
                    display[piece.getY()][piece.getX()-2] = piece;
                    break;
                case 8:
                    display[piece.getY()-1][piece.getX()-1] = piece;
                    break;
                case 9:
                    display[piece.getY()-1][piece.getX()] = piece;
                    break;
                case 10:
                    display[piece.getY()-1][piece.getX()+1] = piece;
                    break;
                case 11:
                    display[piece.getY()-2][piece.getX()] = piece;
                    break;
            }
        }
    }
}
