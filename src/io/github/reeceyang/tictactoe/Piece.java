package io.github.reeceyang.tictactoe;

public enum Piece {
    EMPTY(" "),
    X("X"),
    O("O");
    private String displayName;

    Piece(String displayName) {
        this.displayName = displayName;
    }

    public Piece negate() {
        if (this.equals(X)) {
            return O;
        }
        if (this.equals(O)) {
            return X;
        }
        return EMPTY;
    }

    public String toString() {
        return displayName;
    }
}