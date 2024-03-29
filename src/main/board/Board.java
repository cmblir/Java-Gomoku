package main.board;

public interface Board {
    void displayBoard();
    void resetBoard();
    int getSize();
    char[][] getBoard();
}
