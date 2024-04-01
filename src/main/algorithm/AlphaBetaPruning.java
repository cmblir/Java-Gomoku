package main.algorithm;

import main.board.Board;

public class AlphaBetaPruning {


    // 예시 오목 게임 보드 출력
    public void printBoard(char[][] board, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 알파-베타 가지치기를 사용하여 최적의 다음 수를 찾음
    public int[] findBestMove(char[][] board, int size, char player) {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[2];
        // 보드를 탐색하며 가능한 모든 수를 시도
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == '-') {
                    board[i][j] = player;
                    int score = alphaBeta(board, size, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                    board[i][j] = '-';
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        return bestMove;
    }

    // 알파-베타 가지치기의 재귀적 구현
    private int alphaBeta(char[][] board, int size, int depth, int alpha, int beta, boolean isMaximizing) {
        // 게임 종료 조건 또는 깊이 제한에 도달한 경우
        // 여기서는 임의로 게임 종료 조건을 추가하지 않고, 깊이가 0이 되면 점수를 반환
        if (depth == 0) {
            return evaluate(board);
        }
        // 상태를 평가하고 최선의 수를 찾음
        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (board[i][j] == '-') {
                        board[i][j] = 'X';
                        int score = alphaBeta(board, size, depth - 1, alpha, beta, false);
                        board[i][j] = ' ';
                        bestScore = Math.max(score, bestScore);
                        alpha = Math.max(alpha, bestScore);
                        if (beta <= alpha) {
                            break;
                        }
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (board[i][j] == '-') {
                        board[i][j] = 'O';
                        int score = alphaBeta(board, size, depth - 1, alpha, beta, true);
                        board[i][j] = ' ';
                        bestScore = Math.min(score, bestScore);
                        beta = Math.min(beta, bestScore);
                        if (beta <= alpha) {
                            break;
                        }
                    }
                }
            }
            return bestScore;
        }
    }

    // 보드 상태를 평가하는 함수
    private int evaluate(char[][] board) {
        // 여기서는 단순히 임의의 점수를 반환
        return 0;
    }
}
