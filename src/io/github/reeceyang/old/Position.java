package io.github.reeceyang.old;

public class Position {

    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public boolean equals(Object o) {
        if (o instanceof Position) {
            Position position = (Position) o;
            return position.getRow() == row && position.getCol() == col;
        }
        return false;
    }

    public String toString() {
        return row + " " + col;
    }
}
