package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static training.chessington.model.pieces.Direction.*;
import static training.chessington.model.pieces.Direction.WEST;

public class Bishop extends AbstractPiece {
    public Bishop(PlayerColour colour) {
        super(PieceType.BISHOP, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        return List.of(NORTH_WEST, NORTH_EAST, SOUTH_WEST, SOUTH_EAST).stream()
                .flatMap(direction -> movesInDirection(from, board, direction).stream())
                .collect(Collectors.toList());
    }
}
