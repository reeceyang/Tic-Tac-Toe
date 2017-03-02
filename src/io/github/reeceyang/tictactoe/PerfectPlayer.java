package io.github.reeceyang.tictactoe;

public class PerfectPlayer {

    private Piece me;
    private Board board;
    private int[] choice;

    public PerfectPlayer(Piece me, Board board) {
        this.me = me;
        this.board = board;
    }

    public int minimax(Board board) {
        if (board.isEndPosition()) {
            return board.score();
        }
        int[] scores = new int[board.getNonEmptyPieces()];
        int[][] moves = new int[board.getNonEmptyPieces()][2];

        Piece mover = board.getTurnPiece();
        int iteration = 0;
        for (int[] move : board.getAvailableMoves()) {
            Board possibleBoard = board.getState(move[0], move[1], mover);
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

    public int[] getChoice() {
        return choice;
    }

    public Piece getMe() {
        return me;
    }
}
