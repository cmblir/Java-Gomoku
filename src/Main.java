import main.board.BasicBoard;

import java.util.Scanner;

import static main.logic.MoveValidator.isValidMove;
import static main.logic.StonePlacer.placeStone;
import static main.logic.VictoryChecker.checkVictory;
import static main.logic.DrawChecker.isDraw;

public class Main {
    public static void main(String[] args) {

        System.out.println("오목 게임을 시작합니다.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("원하는 오목판의 크기를 입력하세요.");
        BasicBoard board = new BasicBoard(scanner.nextInt());
        scanner.nextLine();

        System.out.println("게임을 그만하고 싶을 경우 \"그만\"을 입력하세요.");
        System.out.println("오목판 사이즈를 변경하고 싶으면 \"변경\"을 입력하세요.");
        System.out.println("흑돌부터 시작합니다. 돌을 놔둘 좌표를 입력하세요.");
        System.out.println("흑돌은 'O', 백돌은 'X'이며, 좌표 입력 예시는 5,3 과 같이 입니다.");
        int count = 0;

        while (true) {
            char stone;
            if (count % 2 == 0) {
                System.out.println("백돌 차례입니다. 좌표를 입력하세요.");
                stone = 'X';
            } else {
                System.out.println("흑돌 차례입니다. 좌표를 입력하세요.");
                stone = 'O';
            }
            String inputed = scanner.nextLine();
            if (inputed.equals("그만")) break;
            String[] temp = inputed.split(",");
            int x = Integer.parseInt(temp[0]);
            int y = Integer.parseInt(temp[1]);

            if (isValidMove(board, x, y)) {
                placeStone(board, x, y, stone);
            } else {
                System.out.println("유효하지 않은 위치입니다.");
            }

            if (checkVictory(board, x, y, stone)) break;
            if (isDraw(board)) break;

            board.displayBoard();
            count++;
        }
    }
}