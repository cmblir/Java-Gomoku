package main.board;

public class ResizableBoard extends BasicBoard {
    public ResizableBoard(int size) {
        super(size);
    }

    // 오목판 크기 변경 메서드
    public void resize(int newSize) {
        if (newSize <= 0) {
            throw new IllegalArgumentException("오목판 크기는 1 이상이어야 합니다.");
        }
        this.size = newSize;
        this.board = new char[size][size];
        initializeBoard();
    }
}