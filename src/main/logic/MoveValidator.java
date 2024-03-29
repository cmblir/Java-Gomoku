package main.logic;

import main.board.Board;

public class MoveValidator {
    public static boolean isValidMove(Board board, int x, int y) {
        int size = board.getSize();
        char[][] gameBoard = board.getBoard();
        return x >= 0 && x < size && y >= 0 && y < size && gameBoard[x][y] == '-';
    }
}
