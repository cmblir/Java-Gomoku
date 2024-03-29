package test;

import main.board.BasicBoard;
import main.board.BoardInitializer;
import main.board.BoardStatusViewer;
import main.board.ResizableBoard;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void testBasicBoard() {
        // 보드 생성 테스트
        BasicBoard board = new BasicBoard(15);
        assertEquals(15, board.getSize());
        assertNotNull(board.getBoard());

        // 보드 뷰어 테스트
        board.resetBoard();
        assertEquals('-', board.getBoard()[0][0]);
        BoardStatusViewer viewer = new BoardStatusViewer();
        viewer.displayBoard(board);

        // 보드 크기 변경 테스트
        board = new ResizableBoard(25);
        ((ResizableBoard) board).resize(10);
        assertEquals(10, board.getSize());
    }

    @Test
    public void testBoardInitializer() {
        // 보드 초기화 테스트
        BasicBoard board = new BasicBoard(15);
        board.getBoard()[0][0] = 'X';
        board.getBoard()[1][1] = 'O';
        BoardInitializer initializer = new BoardInitializer();
        initializer.resetBoard(board);
        assertEquals('-', board.getBoard()[0][0]);
        assertEquals('-', board.getBoard()[1][1]);
    }

    @Test
    public void testResizableBoard() {
        // 보드 크기 변경 테스트
        ResizableBoard board = new ResizableBoard(10);
        assertEquals(10, board.getSize());
        assertNotNull(board.getBoard());

        board.resize(20);
        assertEquals(20, board.getSize());
        assertNotNull(board.getBoard());
    }
}
