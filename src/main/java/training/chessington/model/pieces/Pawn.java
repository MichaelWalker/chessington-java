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
        List<Move> moves = new ArrayList<>();
        moves.add(forwardOneSquare(from));

        if (pieceIsUnmoved(from)) {
            moves.add(forwardTwoSquares(from));
        }

        return moves;
    }

    private boolean pieceIsUnmoved(Coordinates currentPosition) {
        if (colour == PlayerColour.WHITE) {
            return currentPosition.getRow() == 6;
        }
        return currentPosition.getRow() == 1;
    }

    private Move forwardOneSquare(Coordinates from) {
        int rowDiff = colour == PlayerColour.WHITE ? -1 : 1;
        return new Move(from, from.plus(rowDiff, 0));
    }

    private Move forwardTwoSquares(Coordinates from) {
        int rowDiff = colour == PlayerColour.WHITE ? -2 : 2;
        return new Move(from, from.plus(rowDiff, 0));
    }
}
