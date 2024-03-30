package main.logic;

import main.board.Board;

public class VictoryChecker {
    private static final int WINNING_LENGTH = 5;

    public static boolean checkVictory(Board board, int x, int y, char stone) {
        return checkHorizontal(board, x, y, stone) || checkVertical(board, x, y, stone) ||
                checkDiagonal1(board, x, y, stone) || checkDiagonal2(board, x, y, stone);
    }

    public static boolean checkHorizontal(Board board, int x, int y, char stone) {
        int count = 0;
        char[][] gameBoard = board.getBoard();
        // 수평좌표의 승리조건 확인
        for (int i = Math.max(0, y - WINNING_LENGTH + 1); i <= Math.min(board.getSize() - 1, y + WINNING_LENGTH - 1); i++) {
            if (gameBoard[x][i] == stone) {
                count++;
                if (count == WINNING_LENGTH) {
                    System.out.println(stone + "의 수평 승리");
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    public static boolean checkVertical(Board board, int x, int y, char stone) {
        int count = 0;
        char[][] gameBoard = board.getBoard();
        // 수직좌표의 승리조건 확인
        for (int i = Math.max(0, y - WINNING_LENGTH + 1); i <= Math.min(board.getSize() - 1, x + WINNING_LENGTH - 1); i++) {
            if (gameBoard[i][y] == stone) {
                count++;
                if (count == WINNING_LENGTH) {
                    System.out.println(stone + "의 수직 승리");
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    public static boolean checkDiagonal1(Board board, int x, int y, char stone) {
        int count = 0;
        char[][] gameBoard = board.getBoard();
        // 좌측 상단에서 우측 하단으로 대각선 승리조건 확인
        for (int i = -Math.min(x, y); i <= Math.min(board.getSize() - 1 - x, board.getSize() - 1 - y); i++) {
            if (gameBoard[x + i][y + i] == stone) {
                count++;
                if (count == WINNING_LENGTH) {
                    System.out.println(stone + "의 대각 승리");
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    // 4 0
    public static boolean checkDiagonal2(Board board, int x, int y, char stone) {
        int count = 0;
        char[][] gameBoard = board.getBoard();
        for (int i = -Math.min(x, board.getSize() - 1 - y); i <= Math.min(board.getSize() - 1 - x, y); i++) {
            if (gameBoard[x + i][y - i] == stone) {
                count++;
                if (count == WINNING_LENGTH) {
                    System.out.println(stone + "의 우측 대각 승리");
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }
}
