//Dhruv Khanna
//
/* This is the Board class that will create a new Board,
*/
/*
public class Board
{
	private String Gametype;
	private int [][]peices = new int [6][6];
	private int winlength = 4;

	public static void main(String [] args)
	{
		Board o = new Board("c4");
	}

	public Board(String gt)
	{
		int row = 0;
		int col = 0;
		Gametype = gt;
		if(Gametype == "ttt")
		{
			tictactoeconstruct();
			int ticlist[] = new int [3];
			place(row, col);
			winlength = 3;
			boolean che = checktic(row, col);
		}
		else if(Gametype == "c4")
		{
			connectfourconstruct();
			int arraylist[] = new int[7];
			winlength = 4;
			place(col);

		}
	}

	private void tictactoeconstruct()
	{
		String tic[][] = new String [6][6];
		for(int a=0; a<=2; a++)
		{
			for(int b=0; b<=2; b++)
			{
				tic[a][b] ="| ";
				System.out.print(tic[a][b]);
			}
			System.out.println();
		}
	}

	private void connectfourconstruct()
	{
		String connect[][] = new String [7][7];
		for(int a=0; a<=6; a++)
		{
			for(int b=0; b<=6; b++)
			{
				connect[a][b] = "| ";
				System.out.print(connect[a][b]);
			}
			System.out.println();
		}
	}

	public void place(int row, int col)
	{
		String a[][] = new String [9][9];
		if(a[row][col] == " ")
			a[row][col] = "*";

	}

	public void place(int col)
	{
		String b[] = new String [16];
		for(int a = col; a>=0; a--)
		{
			if(b[a] == " ")
				b[a] = "*";
		}
	}

	public boolean check()
	{
		int row = 0;
		int col = 0;
		String a[] = new String [5][5];
		if((a[row][col] == a[row+1][col+1]) && (a[row][col] == a[row-1][col-1]))
		try
		{
			a[row+1][col+1];
			a[row-1][col-1];
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			break;
		}
		if((a[row][col] == a[row+1][col]) && (a[row][col] == a[row-1][col]))
		{
				try
				{
					a[row+1][col+1];
					a[row-1][col-1];
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					break;
		}
		if((a[row][col] == a[row][col+1]) && (a[row][col] == a[row][col-1]))
		try
		{

	}

}
*/
package io.github.reeceyang.tictactoe;

public class Board {
    private Piece[][] pieces;
    private int boardLength;
    private int turn;

    // Constructs a board filled with empty pieces and turn 1
    public Board(int boardLength) {
        this.boardLength = boardLength;
        pieces = new Piece[boardLength][boardLength];
        reset();
    }

    // Constructs a board from a string
    public Board(String string, int boardLength, int turn) {
        this.turn = turn;
        this.boardLength = boardLength;
        pieces = new Piece[boardLength][boardLength];
        string = string.replace("\n", "");
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                pieces[i][j] = new Piece(string.charAt(i * boardLength + j) + "");
            }
        }
    }

    public void place(int row, int col, Piece piece) {
        pieces[row][col] = piece;
    }

    public Board getState(int row, int col, Piece piece) {
        Board possibleBoard = new Board(this.toString(), this.boardLength, this.turn);
        possibleBoard.place(row, col, piece);
        possibleBoard.nextTurn();
        return possibleBoard;
    }

    // Resets the board and turn
    public void reset() {
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
        turn = 1;
    }

    public Piece getPiece(int row, int col) {
        return pieces[row][col];
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public Piece checkForWin() {
        int winner = 0;
        // Horizontal
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength - 1; j++) {
                if (!pieces[i][j].equals(pieces[i][j + 1])) {
                    break;
                }
                if (j + 1 == boardLength - 1) {
                    winner += pieces[i][0].getType();
                }
            }
        }
        // Vertical
        if (winner == 0) {
            for (int i = 0; i < boardLength; i++) {
                for (int j = 0; j < boardLength - 1; j++) {
                    if (!pieces[j][i].equals(pieces[j + 1][i])) {
                        break;
                    }
                    if (j + 1 == boardLength - 1) {
                        winner += pieces[0][i].getType();
                    }
                }
            }
        }
        // Diagonals
        if (winner == 0) {
            for (int i = 0; i < boardLength - 1; i++) {
                if (!pieces[i][i].equals(pieces[i + 1][i + 1])) {
                    break;
                }
                if (i + 1 == boardLength - 1) {
                    winner += pieces[0][0].getType();
                }
            }
        }
        if (winner == 0) {
            for (int i = 0; i < boardLength - 1; i++) {
                if (!pieces[i][boardLength - 1 - i].equals(pieces[i + 1][boardLength - 2 - i])) {
                    break;
                }
                if (i + 1 == boardLength - 1) {
                    winner += pieces[0][boardLength - 1].getType();
                }
            }
        }
        return new Piece(winner);
    }

    public int score() {
        Piece player = getTurnPiece();
        if (checkForWin().equals(player)) {
            return 10;
        } else if (checkForWin().equals(Piece.negate(player))) {
            return -10;
        } else {
            return 0;
        }
    }

    public Piece getTurnPiece() {
        return turn % 2 == 1 ? Piece.X : Piece.O;
    }

    // Checks if the board is filled with non-empty pieces
    public boolean isFull() {
        if (getNonEmptyPieces() != boardLength * boardLength) {
            return false;
        }
        return true;
    }

    public int getNonEmptyPieces() {
        int nonEmptyPieces = 0;
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                if (!pieces[i][j].equals(Piece.EMPTY)) {
                    nonEmptyPieces++;
                }
            }
        }
        return nonEmptyPieces;
    }

    public int[][] getAvailableMoves() {
        // availableMoves's structure
        // Move Row Col
        //  0    i   j
        int[][] availableMoves = new int[boardLength * boardLength - getNonEmptyPieces()][2];
        int index = 0;
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                if (pieces[i][j].equals(Piece.EMPTY)) {
                    availableMoves[index][0] = i;
                    availableMoves[index][1] = j;
                    index++;
                }
            }
        }
        return availableMoves;
    }

    public boolean isEndPosition() {
        Piece winner = checkForWin();
        // If there is a winner or the board is full
        if (!winner.equals(Piece.EMPTY) || isFull()) {
            return true;
        }
        return false;
    }

    public String toString() {
        String string = "";
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                string += pieces[i][j];
            }
            string += "\n";
        }
        return string;
    }

    public int getTurn() {
        return turn;
    }

    public void nextTurn() {
        turn++;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getBoardLength() {
        return boardLength;
    }
}