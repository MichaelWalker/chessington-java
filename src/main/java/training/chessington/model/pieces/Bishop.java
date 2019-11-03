package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends AbstractPiece {
    public Bishop(PlayerColour colour) {
        super(PieceType.BISHOP, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> moves = new ArrayList<>();

        moves.addAll(movesInDirection(from, board, Direction.NORTH_WEST));
        moves.addAll(movesInDirection(from, board, Direction.NORTH_EAST));
        moves.addAll(movesInDirection(from, board, Direction.SOUTH_EAST));
        moves.addAll(movesInDirection(from, board, Direction.SOUTH_WEST));

        return moves;
    }
}
