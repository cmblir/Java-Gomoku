package main.algorithm;

import java.util.ArrayList;

import static main.algorithm.evaluate.*;

public class AlphaBetaUpgrade implements dictionary{
    int[][][] directions = {
            {{-1, 0}, {1, 0}},
            {{0, -1}, {0, 1}},
            {{-1, 1}, {1, -1}},
            {{-1, -1}, {1, 1}}
    };

    int i;
    int j;
    int N;

    int state;

    public boolean hasNeighbor(char[][] board, int size) {
        for (int[][] axis : directions) {
            for (int[] direction : axis) {
                int xDirection = direction[0];
                int yDirection = direction[1];

                if (xDirection != 0 && j + xDirection < 0
                        || j + xDirection >= size) break;
                if (yDirection != 0 && i + yDirection < 0
                        || i + yDirection >= size) break;
                if (board[i + yDirection][j + xDirection] != empty) return true;
                if (xDirection != 0 && j + xDirection * 2 < 0
                        || j + xDirection * 2 >= size) break;
                if (yDirection != 0 && i + yDirection * 2 < 0
                        || i + yDirection * 2 >= size) break;
                if (board[i + yDirection * 2][j + xDirection * 2] != empty) return true;
            }
        }
        return false;
    }
    
    public int directionCount(int[][] board, int size, int xDirection, int yDirection) {
        int count = 0;
        for (int step = 1; step < 5; step++) {
            if (xDirection != 0 && (j + xDirection * step < 0 
                    || j + xDirection * step >= size)) break;
            if (yDirection != 0 && (i + yDirection * step < 0 
                    || i + yDirection * step >= size)) break;
            if (board[i + yDirection * step][j + xDirection * step] == state) {
                count ++;
            } else {
                break;
            }
        }
        return count;
    }

    public ArrayList<Integer> directionPattern(int[][] board, int size, int xDirection, int yDirection) {
        ArrayList<Integer> pattern = new ArrayList<>();
        for (int step = -1; step < 5; step++) {
            if (xDirection != 0 && (j + xDirection * step < 0
                    || j + xDirection * step >= size)) break;
            if (yDirection != 0 && (i + yDirection * step < 0
                    || i + yDirection * step >= size)) break;

            pattern.add(board[i + yDirection * step][j + xDirection * step]);
        }
        return pattern;
    }

    public boolean hasCheckmate(int[][] board, int size) {
        for (int[][] axis : directions) {
            int axisCount = 1;
            for (int[] direction : axis) {
                int xDirection = direction[0];
                int yDirection = direction[1];
                axisCount += directionCount(board, size, xDirection, yDirection);
                if (axisCount >= 5) return true;
            }
        }
        return false;
    }

    public boolean hasCheck(int[][] board, int size) {
        for (int[][] axis : directions) {
            ArrayList<Integer> currentPattern = new ArrayList<>();
            for (int[] direction : axis) {
                int xDirection = direction[0];
                int yDirection = direction[1];
                currentPattern.addAll(directionPattern(board, size, xDirection, yDirection));
                if (currentPattern.size() > 2) {
                    currentPattern.set(1, state);
                }
                if (enumToString(currentPattern) == white6Patterns.get(0)) return true;
                if (enumToString(currentPattern) == black6Patterns.get(0)) return true;
                }
            }
        return false;
    }

    public static boolean opponentHasCheckmate
}

