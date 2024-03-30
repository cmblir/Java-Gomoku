package main.board;

public class StoneMove {
    public void placeStone(Board board, int x, int y, char stone) {
        char[][] gameBoard = board.getBoard();
        gameBoard[x][y] = stone;
    }
}
