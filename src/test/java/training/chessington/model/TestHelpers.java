package training.chessington.model;

import training.chessington.model.pieces.Direction;
import training.chessington.model.pieces.Pawn;

public class TestHelpers {
    public static void surroundPiece(Board board, Coordinates surroundedSquare, Direction gap) {
        for (Direction direction : Direction.values()) {
            if (direction != gap) {
                board.placePiece(surroundedSquare.step(direction), new Pawn(PlayerColour.WHITE));
            }
        }
    }
}
