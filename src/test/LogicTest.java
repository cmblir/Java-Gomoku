package test;

import main.board.BasicBoard;
import main.board.Board;
import main.logic.DrawChecker;
import main.logic.MoveValidator;
import main.logic.StonePlacer;
import main.logic.VictoryChecker;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class LogicTest {
    @Test
    public void testMoveValidator() {
        // 보드 좌표 돌확인 테스트
        Board board = new BasicBoard(15);
        board.getBoard()[0][0] = 'X';
        assertFalse(MoveValidator.isValidMove(board, 0, 0));
        assertTrue(MoveValidator.isValidMove(board, 1, 1));
    }

    @Test
    public void testStonePlacer() {
        // 보드 좌표 돌두기 테스트
        Board board = new BasicBoard(15);
        StonePlacer.placeStone(board, 0, 0, 'X');
        assertEquals('X', board.getBoard()[0][0]);
    }

    @Test
    public void testCheckHorizontal() {
        // 수평 승리 조건 테스트
        Board board1 = new BasicBoard(5);
        board1.placeStone(0, 0, 'X');
        board1.placeStone(0, 1, 'X');
        board1.placeStone(0, 2, 'X');
        board1.placeStone(0, 3, 'X');
        board1.placeStone(0, 4, 'X');
        assertTrue(VictoryChecker.checkHorizontal(board1, 0, 4, 'X'));
    }

    @Test
    public void testCheckVertical() {
        // 수직 승리 조건 테스트
        Board board1 = new BasicBoard(5);
        board1.placeStone(0, 0, 'X');
        board1.placeStone(1, 0, 'X');
        board1.placeStone(2, 0, 'X');
        board1.placeStone(3, 0, 'X');
        board1.placeStone(4, 0, 'X');
        assertTrue(VictoryChecker.checkVertical(board1, 4, 0, 'X'));
    }

    @Test
    public void testCheckDiagonal1() {
        // 좌측 대각 승리 조건 테스트
        Board board1 = new BasicBoard(5);
        board1.placeStone(0, 0, 'X');
        board1.placeStone(1, 1, 'X');
        board1.placeStone(2, 2, 'X');
        board1.placeStone(3, 3, 'X');
        board1.placeStone(4, 4, 'X');
        assertTrue(VictoryChecker.checkDiagonal1(board1, 4, 4, 'X'));
    }

    @Test
    public void testCheckDiagonal2() {
        // 우측 대각 승리 조건 테스트
        Board board1 = new BasicBoard(5);
        board1.placeStone(0, 4, 'X');
        board1.placeStone(1, 3, 'X');
        board1.placeStone(2, 2, 'X');
        board1.placeStone(3, 1, 'X');
        board1.placeStone(4, 0, 'X');
        assertTrue(VictoryChecker.checkDiagonal2(board1, 4, 0, 'X'));
    }

    @Test
    public void testVictory() {
        // 전체 승리 조건 테스트
        Board board = new BasicBoard(15);
        board.placeStone(7, 7, 'X');
        board.placeStone(7, 8, 'X');
        board.placeStone(8, 8, 'X');
        board.placeStone(9, 9, 'X');
        board.placeStone(10, 10, 'X');
        board.placeStone(11, 11, 'X');
        assertTrue(VictoryChecker.checkVictory(board, 11, 11, 'X'));
    }

    @Test
    public void testDrawChecker() {
        // 무승부가 아닌 경우
        Board board1 = new BasicBoard(5);
        assertFalse(DrawChecker.isDraw(board1));

        // 모든 공간이 돌로 채워진 경우
        Board board2 = new BasicBoard(3);
        board2.placeStone(0, 0, 'X');
        board2.placeStone(0, 1, 'O');
        board2.placeStone(0, 2, 'X');
        board2.placeStone(1, 0, 'X');
        board2.placeStone(1, 1, 'O');
        board2.placeStone(1, 2, 'O');
        board2.placeStone(2, 0, 'O');
        board2.placeStone(2, 1, 'X');
        board2.placeStone(2, 2, 'O');
        assertTrue(DrawChecker.isDraw(board2));
    }
}
