import java.util.ArrayList;
import java.util.List;

public class GomokuAI {

    private Gomoku gomoku;
    private BoardState currentState;
    private int depth;
    private int currentI;
    private int currentJ;

    public GomokuAI(Gomoku gomoku, BoardState currentState, int depth) {
        this.gomoku = gomoku;
        this.currentState = currentState;
        this.depth = depth;
        this.currentI = -1;
        this.currentJ = -1;
    }

    public void setBoard(int i, int j, BoardState state) {
        gomoku.setChessboardState(i, j, state);
    }

    public boolean hasNeighbor(BoardState state, int i, int j) {
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 1 }, { 1, -1 } };
        for (int[] axis : directions) {
            for (int[] direction : axis) {
                int xdirection = direction[0];
                int ydirection = direction[1];

                if (xdirection != 0 && (j + xdirection < 0 || j + xdirection >= Gomoku.N)) {
                    break;
                }
                if (ydirection != 0 && (i + ydirection < 0 || i + ydirection >= Gomoku.N)) {
                    break;
                }
                if (gomoku.getChessMap()[i + ydirection][j + xdirection] != BoardState.EMPTY) {
                    return true;
                }
                if (xdirection != 0 && (j + xdirection * 2 < 0 || j + xdirection * 2 >= Gomoku.N)) {
                    break;
                }
                if (ydirection != 0 && (i + ydirection * 2 < 0 || i + ydirection * 2 >= Gomoku.N)) {
                    break;
                }
                if (gomoku.getChessMap()[i + ydirection * 2][j + xdirection * 2] != BoardState.EMPTY) {
                    return true;
                }
            }
        }
        return false;
    }

    public int directionCount(int i, int j, int xdirection, int ydirection, BoardState state) {
        int count = 0;
        for (int step = 1; step < 5; step++) {
            if (xdirection != 0 && (j + xdirection * step < 0 || j + xdirection * step >= Gomoku.N)) {
                break;
            }
            if (ydirection != 0 && (i + ydirection * step < 0 || i + ydirection * step >= Gomoku.N)) {
                break;
            }
            if (gomoku.getChessMap()[i + ydirection * step][j + xdirection * step] == state) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    public List<String> directionPattern(int i, int j, int xdirection, int ydirection, BoardState state) {
        List<String> pattern = new ArrayList<>();
        for (int step = -1; step < 5; step++) {
            if (xdirection != 0 && (j + xdirection * step < 0 || j + xdirection * step >= Gomoku.N)) {
                break;
            }
            if (ydirection != 0 && (i + ydirection * step < 0 || i + ydirection * step >= Gomoku.N)) {
                break;
            }
            pattern.add(gomoku.getChessMap()[i + ydirection * step][j + xdirection * step].toString());
        }
        return pattern;
    }

    public boolean hasCheckmate(BoardState state, int i, int j) {
        int[][][] directions = { { { -1, 0 }, { 1, 0 } }, { { 0, -1 }, { 0, 1 } }, { { -1, 1 }, { 1, -1 } },
                { { -1, -1 }, { 1, 1 } } };
        for (int[][] axis : directions) {
            int axisCount = 1;
            for (int[] direction : axis) {
                int xdirection = direction[0];
                int ydirection = direction[1];
                axisCount += directionCount(i, j, xdirection, ydirection, state);
                if (axisCount >= 5) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasCheck(BoardState state, int i, int j) {
        int[][][] directions = { { { -1, 0 }, { 1, 0 } }, { { 0, -1 }, { 0, 1 } }, { { -1, 1 }, { 1, -1 } },
                { { -1, -1 }, { 1, 1 } } };
        for (int[][] axis : directions) {
            List<String> currentPattern = new ArrayList<>();
            for (int[] direction : axis) {
                currentPattern.addAll(directionPattern(i, j, direction[0], direction[1], state));
                if (currentPattern.size() > 2) {
                    currentPattern.set(1, state.toString());
                }
                if (currentPattern.equals(Gomoku.WHITE_6PATTERNS.get(0))) {
                    return true;
                }
                if (currentPattern.equals(Gomoku.BLACK_6PATTERNS.get(0))) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean opponentHasCheckmate(BoardState state) {
        List<int[]> vectors = new ArrayList<>();
        for (int i = 0; i < Gomoku.N; i++) {
            vectors.add(gomoku.getChessMap()[i]);
        }
        for (int j = 0; j < Gomoku.N; j++) {
            int[] colVector = new int[Gomoku.N];
            for (int i = 0; i < Gomoku.N; i++) {
                colVector[i] = gomoku.getChessMap()[i][j];
            }
            vectors.add(colVector);
        }
        int[] diagonal1Vector = new int[Gomoku.N];
        for (int i = 0; i < Gomoku.N; i++) {
            diagonal1Vector[i] = gomoku.getChessMap()[i][i];
        }
        vectors.add(diagonal1Vector);
        for (int i = 1; i < Gomoku.N - 4; i++) {
            int[] diagonal2Vector1 = new int[Gomoku.N - i];
            int[] diagonal2Vector2 = new int[Gomoku.N - i];
            for (int x = 0; x < Gomoku.N - i; x++) {
                diagonal2Vector1[x] = gomoku.getChessMap()[x][x + i];
                diagonal2Vector2[x] = gomoku.getChessMap()[x + i][x];
            }
            vectors.add(diagonal2Vector1);
            vectors.add(diagonal2Vector2);
        }
        int[] diagonal3Vector = new int[Gomoku.N];
        for (int i = 4; i < Gomoku.N - 1; i++) {
            for (int x = 0; x <= i; x++) {
                diagonal3Vector[x] = gomoku.getChessMap()[i - x][x];
            }
            vectors.add(diagonal3Vector);
            int[] diagonal4Vector = new int[Gomoku.N];
            for (int x = 0; x <= i; x++) {
                diagonal4Vector[x] = gomoku.getChessMap()[Gomoku.N - x - 1][Gomoku.N - 1 - i + x];
            }
            vectors.add(diagonal4Vector);
        }
        for (int[] vector : vectors) {
            String temp = enumToString(vector);
            List<String> tempList = new ArrayList<>();
            for (int val : vector) {
                tempList.add(BoardState.values()[val].toString());
            }
            if (state == BoardState.BLACK) {
                for (List<String> pattern : Gomoku.WHITE_5PATTERNS) {
                    if (tempList.containsAll(pattern)) {
                        return true;
                    }
                }
            } else if (state == BoardState.WHITE) {
                for (List<String> pattern : Gomoku.BLACK_5PATTERNS) {
                    if (tempList.containsAll(pattern)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public List<GomokuAI> generate() {
        List<GomokuAI> frontierList = new ArrayList<>();
        for (int i = 0; i < Gomoku.N; i++) {
            for (int j = 0; j < Gomoku.N; j++) {
                if (gomoku.getChessMap()[i][j] != BoardState.EMPTY) {
                    continue;
                }
                if (!hasNeighbor(gomoku.getChessMap()[i][j], i, j)) {
                    continue;
                }
                BoardState nextState = currentState == BoardState.WHITE ? BoardState.BLACK : BoardState.WHITE;
                GomokuAI nextPlay = new GomokuAI(new Gomoku(gomoku), nextState, depth - 1);
                nextPlay.setBoard(i, j, currentState);
                frontierList.add(nextPlay);
            }
        }
        // Degree Heuristics, Sort points based on their evaluation
        List<Integer> frontierScores = new ArrayList<>();
        for (GomokuAI node : frontierList) {
            frontierScores.add(evaluatePoint(node.currentI, node.currentJ));
        }
        // Sort frontierList based on frontierScores
        return frontierList;
    }

    public int evaluate() {
        int boardScore = 0;
        List<int[]> vectors = new ArrayList<>();
        for (int i = 0; i < Gomoku.N; i++) {
            vectors.add(gomoku.getChessMap()[i]);
        }
        for (int j = 0; j < Gomoku.N; j++) {
            int[] colVector = new int[Gomoku.N];
            for (int i = 0; i < Gomoku.N; i++) {
                colVector[i] = gomoku.getChessMap()[i][j];
            }
            vectors.add(colVector);
        }
        int[] diagonal1Vector = new int[Gomoku.N];
        for (int i = 0; i < Gomoku.N; i++) {
            diagonal1Vector[i] = gomoku.getChessMap()[i][i];
        }
        vectors.add(diagonal1Vector);
        // Add other diagonal vectors
        // Calculate board score
        return boardScore;
    }

    public int evaluatePoint(int i, int j) {
        int pointScore = 0;
        List<int[]> vectors = new ArrayList<>();
        vectors.add(gomoku.getChessMap()[i]);
        // Add other vectors for the point
        // Calculate point score
        return pointScore;
    }

    public int alphaBetaPrune(GomokuAI ai, int alpha, int beta) {
        if (ai.depth <= 0) {
            int score = ai.negate();
            return score;
        }
        List<GomokuAI> generatedNodes = ai.generate();
        for (GomokuAI nextPlay : generatedNodes) {
            int tempScore = -alphaBetaPrune(nextPlay, -beta, -alpha);
            if (tempScore > beta) {
                return beta;
            }
            if (tempScore > alpha) {
                alpha = tempScore;
                ai.currentI = nextPlay.currentI;
                ai.currentJ = nextPlay.currentJ;
            }
        }
        return alpha;
    }

    public boolean firstStep() {
        gomoku.setChessboardState(7, 7, currentState);
        return true;
    }

    public boolean oneStep() {
        for (int i = 0; i < Gomoku.N; i++) {
            for (int j = 0; j < Gomoku.N; j++) {
                if (gomoku.getChessMap()[i][j] != BoardState.EMPTY) {
                    continue;
                }
                if (hasCheckmate(currentState, i, j)) {
                    gomoku.setChessboardState(i, j, currentState);
                    return true;
                }
                if (!hasNeighbor(gomoku.getChessMap()[i][j], i, j)) {
                    continue;
                }
                if (hasCheck(currentState, i, j)) {
                    if (opponentHasCheckmate(currentState)) {
                        continue;
                    } else {
                        gomoku.setChessboardState(i, j, currentState);
                        return true;
                    }
                }
            }
        }
        GomokuAI node = new GomokuAI(new Gomoku(gomoku), currentState, depth);
        int score = alphaBetaPrune(node, -10000000, 10000000);
        if (score != Integer.MIN_VALUE && score != Integer.MAX_VALUE) {
            gomoku.setChessboardState(node.currentI, node.currentJ, currentState);
            return true;
        }
        return false;
    }
}
