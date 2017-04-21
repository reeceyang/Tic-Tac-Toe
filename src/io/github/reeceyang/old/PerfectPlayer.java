package io.github.reeceyang.old;

public class PerfectPlayer extends Player {

    private Piece me;
    private Board board;
    private Position choice;

    public PerfectPlayer(Piece me, Board board) {
        super(me, board);
    }


    public int minimax(Board board) {
        if (board.isEndPosition()) {
            return board.score();
        }
        int[] scores = new int[board.getNonEmptyPieces()];
        Position[] moves = new Position[board.getNonEmptyPieces()];

        Piece mover = board.getTurnPiece();
        int iteration = 0;
        for (Position move : board.getAvailableMoves()) {
            Board possibleBoard = board.getState(move, mover);
            scores[iteration] = minimax(possibleBoard);
            moves[iteration] = move;
        }

        if (!mover.equals(me)) {
            int maxScoreIndex = Utilities.maxIndex(scores);
            choice = moves[maxScoreIndex];
            return scores[maxScoreIndex];
        } else {
            int minScoreIndex = Utilities.minIndex(scores);
            choice = moves[minScoreIndex];
            return scores[minScoreIndex];
        }
    }

    public Position getChoice() {
        return choice;
    }

    public Piece getMe() {
        return me;
    }
}
