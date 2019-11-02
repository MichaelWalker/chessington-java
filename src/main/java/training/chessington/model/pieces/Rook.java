package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Rook extends AbstractPiece {
    public Rook(PlayerColour colour) {
        super(PieceType.ROOK, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> moves = new ArrayList<>();
        moves.addAll(movesInDirection(from, board, Direction.NORTH));
        moves.addAll(movesInDirection(from, board, Direction.SOUTH));
        moves.addAll(movesInDirection(from, board, Direction.EAST));
        moves.addAll(movesInDirection(from, board, Direction.WEST));
        return moves;
    }
}
