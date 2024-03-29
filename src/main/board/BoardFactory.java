package main.board;

public class BoardFactory{
    public Board createBoard(int size) {
        return new BasicBoard(size);
    }
}
