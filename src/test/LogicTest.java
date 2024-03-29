package test;

import main.board.BasicBoard;
import main.board.Board;
import main.logic.MoveValidator;
import main.logic.StonePlacer;
import org.junit.Test;

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
}
