package main.logic;

import main.board.Board;

public class StonePlacer {
    public static void placeStone(Board board, int x, int y, char stone) {
        char[][] gameBoard = board.getBoard();
        gameBoard[x][y] = stone;
    }
}
