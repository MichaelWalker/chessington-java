package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;
import java.util.stream.Collectors;

public class Knight extends AbstractPiece {
    public Knight(PlayerColour colour) {
        super(PieceType.KNIGHT, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        return possibleSquares(from).stream()
                .filter(square -> !containsFriend(board, square))
                .map(square -> new Move(from, square))
                .collect(Collectors.toList());
    }

    private List<Coordinates> possibleSquares(Coordinates origin) {
        return List.of(
                origin.plus(2, 1),
                origin.plus(2, -1),
                origin.plus(-2, 1),
                origin.plus(-2, -1),
                origin.plus(1, 2),
                origin.plus(1, -2),
                origin.plus(-1, 2),
                origin.plus(-1, -2)
        );
    }
}
