package main.board;

// BasicBoard 클래스
public class BasicBoard implements Board {
    int size; // 오목판의 크기
    char[][] board; // 오목판 2차원 배열

    // 생성자
    public BasicBoard(int size) {
        this.size = size;
        board = new char[size][size];
        initializeBoard();
    }

    // 오목판 초기화 메서드
    void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '-';
            }
        }
    }

    // 오목판 현황 출력 메서드
    @Override
    public void displayBoard() {
        System.out.println("오목판 현황 : ");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 오목판 초기화 메서드
    @Override
    public void resetBoard() {
        initializeBoard();
    }

    public int getSize() {
        return size;
    }

    public char[][] getBoard() {
        return board;
    }
}