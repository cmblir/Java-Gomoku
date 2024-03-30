package main.logic;
import main.board.Board;
public class DrawChecker {
    public static boolean isDraw(Board board) {
        char[][] gameBoard = board.getBoard();
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                // 빈 공간이 있음 == 게임이 아직 끝나지 않음
                if (gameBoard[i][j] == '-') {
                    return false;
                }
            }
        }
        // 빈 공간이 없음 == 게임이 끝났으나 승패가 결정 안됨 -> 무승부
        return true;
    }
}
