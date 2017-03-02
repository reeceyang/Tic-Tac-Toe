/*//Ben Schroeder
//
//Piece Class

package U7A3;

public class Piece
{
	private String pieceType;

	public Piece(String player)
	{
		if(player.equals("p1"))
			pieceType = "X";
		else if(player.equals("p2"))
			pieceType = "O";
	}

	public String getPiece()
	{
		return pieceType;
	}
}
*/
package io.github.reeceyang.tictactoe;

public class Piece {
    private int type;
    public static final Piece EMPTY = new Piece(0);
    public static final Piece X = new Piece(1);
    public static final Piece O = new Piece(2);

    public Piece(int type) {
        this.type = type;
    }

    public Piece(String type) {
        this.type = stringToType(type);
    }

    public int getType() {
        return type;
    }

    public String toString() {
        return typeToString(type);
    }

    public boolean equals(Object object) {
        // Pieces are equal if they have the same type
        Piece piece = (Piece) object;
        return piece.getType() == type;
    }

    public static Piece negate(Piece piece) {
        if (piece.equals(Piece.X)) {
            return Piece.O;
        } else if (piece.equals(O)){
            return Piece.X;
        }
        return Piece.EMPTY;
    }

    public static int stringToType(String string) {
        switch (string) {
            case " ":
                return 0;
            case "X":
                return 1;
            case "O":
                return 2;
            default:
                return 0;
        }
    }

    public static String typeToString(int type) {
        switch (type) {
            case 0:
                return " ";
            case 1:
                return "X";
            case 2:
                return "O";
            default:
                return "Invalid Type";
        }
    }
}