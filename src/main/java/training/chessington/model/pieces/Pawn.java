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
        int direction = colour == PlayerColour.WHITE ? -1 : 1;

        List<Move> moves = new ArrayList<>();
        moves.add(new Move(from, from.plus(direction, 0)));

        if (pieceHasBeenMoved(from)) {
            moves.add(new Move(from, from.plus(2*direction, 0)));
        }

        return moves;
    }
    
    private boolean pieceHasBeenMoved(Coordinates currentPosition) {
        if (colour == PlayerColour.WHITE) {
            return currentPosition.getRow() == 6;
        }
        return currentPosition.getRow() == 1;
    }
}
