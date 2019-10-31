package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        return new ArrayList<>(forwardsMoves(from, board));
    }

    private boolean pieceIsUnmoved(Coordinates currentPosition) {
        if (colour == PlayerColour.WHITE) {
            return currentPosition.getRow() == 6;
        }
        return currentPosition.getRow() == 1;
    }

    private List<Move> forwardsMoves(Coordinates from, Board board) {
        List<Move> moves = new ArrayList<>();
        int direction = colour == PlayerColour.WHITE ? -1 : 1;
        int maxNumberOfMoves = pieceIsUnmoved(from) ? 2 : 1;

        for (int count = direction; Math.abs(count) <= maxNumberOfMoves; count += direction) {
            Coordinates nextSquare = from.plus(count, 0);
            if (board.get(nextSquare) != null) {
                return moves;
            }
            moves.add(new Move(from, nextSquare));
        }

        return moves;
    }
}
