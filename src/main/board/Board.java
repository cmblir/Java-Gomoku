package main.board;

public interface Board {
    void displayBoard();
    void resetBoard();
    void placeStone(int x, int y, char stone);
    int getSize();
    char[][] getBoard();
}
