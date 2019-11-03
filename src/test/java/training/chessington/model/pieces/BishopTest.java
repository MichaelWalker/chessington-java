package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static training.chessington.model.TestHelpers.surroundPiece;
import static training.chessington.model.pieces.Direction.NORTH;
import static training.chessington.model.pieces.Direction.NORTH_EAST;
import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class BishopTest {
    @Test
    public void bishopCanMoveDiagonally() {
        Board board = Board.empty();
        Bishop bishop = new Bishop(PlayerColour.WHITE);
        Coordinates position = new Coordinates(4, 4);
        board.placePiece(position, bishop);

        List<Move> moves = bishop.getAllowedMoves(position, board);

        assertThat(moves).containsOnlyOnce(
                new Move(position, new Coordinates(0, 0)),
                new Move(position, new Coordinates(1, 1)),
                new Move(position, new Coordinates(2, 2)),
                new Move(position, new Coordinates(3, 3)),
                new Move(position, new Coordinates(5, 5)),
                new Move(position, new Coordinates(6, 6)),
                new Move(position, new Coordinates(7, 7)),
                new Move(position, new Coordinates(7, 1)),
                new Move(position, new Coordinates(6, 2)),
                new Move(position, new Coordinates(5, 3)),
                new Move(position, new Coordinates(3, 5)),
                new Move(position, new Coordinates(2, 6)),
                new Move(position, new Coordinates(1, 7))
        );
    }

    @Test
    public void bishopCantMoveThroughPiecesOfTheSameColour() {
        Board board = Board.empty();
        Bishop bishop = new Bishop(PlayerColour.WHITE);
        Coordinates bishopPosition = new Coordinates(4, 4);
        board.placePiece(bishopPosition, bishop);
        surroundPiece(board, bishopPosition, NORTH_EAST);

        // place another piece 2 steps in front.
        board.placePiece(bishopPosition.steps(Direction.NORTH_EAST, 2), new Pawn(PlayerColour.WHITE));

        List<Move> moves = bishop.getAllowedMoves(bishopPosition, board);

        assertThat(moves).containsOnly(new Move(bishopPosition, bishopPosition.step(Direction.NORTH_EAST)));
    }

    @Test
    public void bishopCanTakeEnemyPieceButNotMoveThroughIt() {
        Board board = Board.empty();
        Bishop bishop = new Bishop(PlayerColour.WHITE);
        Coordinates bishopPosition = new Coordinates(4, 4);
        board.placePiece(bishopPosition, bishop);
        surroundPiece(board, bishopPosition, NORTH_EAST);

        // place another piece 2 steps in front.
        board.placePiece(bishopPosition.steps(Direction.NORTH_EAST, 2), new Pawn(PlayerColour.BLACK));

        List<Move> moves = bishop.getAllowedMoves(bishopPosition, board);

        assertThat(moves).containsOnly(
                new Move(bishopPosition, bishopPosition.steps(Direction.NORTH_EAST, 1)),
                new Move(bishopPosition, bishopPosition.steps(Direction.NORTH_EAST, 2))
        );
    }
}
