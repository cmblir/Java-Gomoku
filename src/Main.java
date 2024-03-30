import main.board.BasicBoard;
import main.board.ResizableBoard;

import java.util.InputMismatchException;
import java.util.Scanner;

import static main.logic.MoveValidator.isValidMove;
import static main.logic.StonePlacer.placeStone;
import static main.logic.VictoryChecker.checkVictory;
import static main.logic.DrawChecker.isDraw;

public class Main {
    public static void main(String[] args) {

        System.out.println("오목 게임을 시작합니다.");
        Scanner scanner = new Scanner(System.in);
        int concaveSize = 0;
        int resizedConcaveSize = 0;

        // 오목판은 정수형만 받도록
        while (true) {
            try {
                concaveSize = Integer.parseInt(concaveSize());
            } catch (NumberFormatException e) {
                System.out.println("크기를 잘못 입력하였습니다.");
                continue;
            }
            break;
        }

        BasicBoard board = new BasicBoard(concaveSize);

        System.out.println("게임을 그만하고 싶을 경우 \"그만\"을 입력하세요.");
        System.out.println("게임을 재시작하고 싶을 경우 \"재시작\"을 입력하세요.");
        System.out.println("오목판 크기를 변경하고 싶으면 \"변경\"을 입력하세요.");
        System.out.println("흑돌부터 시작합니다. 돌을 놔둘 좌표를 입력하세요.");
        System.out.println("흑돌은 'O', 백돌은 'X'이며, 좌표 입력 예시는 5,3 입니다.");
        int count = 1;
        int x = 0; // x 좌표값
        int y = 0; // y 좌표값
        boolean error = false; // 에러발생여부
        boolean resize = false; // 리사이즈여부
        boolean restart = false; // 재시작여부
        char stone = 'O';

        // 게임 시작
        while (true) {
            if (error) {
                System.out.println("다시 좌표를 입력하세요.");
                error = false;
            } else if (resize) {
                // 오목판은 정수형만 받도록
                while (true) {
                    try {
                        resizedConcaveSize = Integer.parseInt(resizeConcaveSize());
                    } catch (NumberFormatException e) {
                        System.out.println("크기를 잘못 입력하였습니다.");
                        continue;
                    }
                    break;
                }
                ResizableBoard resizedBoard = new ResizableBoard(resizedConcaveSize);
                board = resizedBoard;
                resize = false;
                continue;
            } else if (restart) {
                board.resetBoard();
                restart = false;
                continue;
            } else {
                if (count % 2 == 0) {
                    System.out.println("백돌 차례입니다. 좌표를 입력하세요.");
                    stone = 'X';
                } else {
                    System.out.println("흑돌 차례입니다. 좌표를 입력하세요.");
                    stone = 'O';
                }
            }
            String inputed = scanner.nextLine();
            if (inputed.equals("그만")) {
                System.out.println(stone == 'X' ? "백돌이 그만두었습니다." : "흑돌이 그만두었습니다.");
                System.out.println(stone == 'X' ? "흑돌이 승리하였습니다." : "백돌이 승리하였습니다.");
                break;
            }
            if (inputed.equals("변경")) {
                resize = true;
                continue;
            }
            if (inputed.equals("재시작")) {
                restart = true;
                continue;
            }
            try {
                String[] coordinate = inputed.split(",");
                x = Integer.parseInt(coordinate[0]);
                y = Integer.parseInt(coordinate[1]);
            } catch (NumberFormatException e) {
                System.out.println("잘못된 좌표를 입력했습니다.");
                error = true;
                continue;
            }

            if (isValidMove(board, x, y)) {
                placeStone(board, x, y, stone);
            } else {
                System.out.println("유효하지 않은 위치입니다.");
                error = true;
                continue;
            }

            if (checkVictory(board, x, y, stone)) break;
            if (isDraw(board)) break;

            board.displayBoard();
            count++;
        }
    }

    private static String concaveSize() throws NumberFormatException {
        System.out.println("원하는 오목판의 크기를 입력하세요.");
        Scanner scanner = new Scanner(System.in);
        String number = scanner.next();
        return number;
    }

    private static String resizeConcaveSize() throws NumberFormatException {
        System.out.println("변경할 오목판의 크기를 입력하세요.");
        Scanner scanner = new Scanner(System.in);
        String number = scanner.next();
        return number;
    }
}