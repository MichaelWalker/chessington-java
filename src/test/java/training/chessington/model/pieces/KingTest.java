package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static training.chessington.model.PlayerColour.*;
import static training.chessington.model.TestHelpers.surroundPiece;
import static training.chessington.model.pieces.Direction.NORTH;
import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class KingTest {
    @Test
    public void kingCanMoveSingleSquareInAnyDirection() {
        Board board = Board.empty();
        King king = new King(WHITE);
        Coordinates position = new Coordinates(4, 4);
        board.placePiece(position, king);

        List<Move> moves = king.getAllowedMoves(position, board);

        assertThat(moves).containsOnlyOnce(
                new Move(position, new Coordinates(5, 4)),
                new Move(position, new Coordinates(5, 5)),
                new Move(position, new Coordinates(4, 5)),
                new Move(position, new Coordinates(3, 5)),
                new Move(position, new Coordinates(3, 4)),
                new Move(position, new Coordinates(3, 3)),
                new Move(position, new Coordinates(4, 3)),
                new Move(position, new Coordinates(5, 3))
        );
    }

    @Test
    public void kingCantMoveThroughPiecesOfTheSameColour() {
        Board board = Board.empty();
        King king = new King(WHITE);
        Coordinates kingPosition = new Coordinates(4, 4);
        board.placePiece(kingPosition, king);
        surroundPiece(board, kingPosition, NORTH);

        List<Move> moves = king.getAllowedMoves(kingPosition, board);

        assertThat(moves).containsOnly(new Move(kingPosition, kingPosition.step(NORTH)));
    }

    @Test
    public void kingCanTakeEnemyPieceButNotMoveThroughIt() {
        Board board = Board.empty();
        King king = new King(WHITE);
        Coordinates kingPosition = new Coordinates(4, 4);
        board.placePiece(kingPosition, king);
        surroundPiece(board, kingPosition, NORTH);

        // place another piece 2 steps in front.
        board.placePiece(kingPosition.step(Direction.NORTH), new Pawn(PlayerColour.BLACK));

        List<Move> moves = king.getAllowedMoves(kingPosition, board);

        assertThat(moves).containsOnly(new Move(kingPosition, kingPosition.step(Direction.NORTH)));
    }
}
