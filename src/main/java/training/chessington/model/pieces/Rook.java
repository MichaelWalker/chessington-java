package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static training.chessington.model.pieces.Direction.*;

public class Rook extends AbstractPiece {
    public Rook(PlayerColour colour) {
        super(PieceType.ROOK, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        return List.of(NORTH, SOUTH, EAST, WEST).stream()
                .flatMap(direction -> movesInDirection(from, board, direction).stream())
                .collect(Collectors.toList());
    }
}
