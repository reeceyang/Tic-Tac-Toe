package io.github.reeceyang.old;

public class Player {

    private Piece me;
    private Board board;
    private Position choice;

    public Player(Piece me, Board board) {
        this.me = me;
        this.board = board;
    }

    public Position getChoice() {
        return choice;
    }
}
