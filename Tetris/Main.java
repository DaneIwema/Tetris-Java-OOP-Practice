package Tetris;

public class Main {
    public static void main(String[] args){
        System.out.print("\033[2J");
        WindowsRawMode window = new WindowsRawMode();
        window.enableRawMode();
        Container display = new Container(window.getWindowHeight(), window.getWindowWidth());
        while (true){
            
        }
    }
}