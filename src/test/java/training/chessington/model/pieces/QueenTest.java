package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.*;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static training.chessington.model.TestHelpers.surroundPiece;
import static training.chessington.model.pieces.Direction.NORTH;

public class QueenTest {
    @Test
    public void queenCanMoveDiagonally() {
        Board board = Board.empty();
        Queen queen = new Queen(PlayerColour.WHITE);
        Coordinates position = new Coordinates(4, 4);
        board.placePiece(position, queen);

        List<Move> moves = queen.getAllowedMoves(position, board);

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
    public void queenCanMoveVertically() {
        Board board = Board.empty();
        Queen queen = new Queen(PlayerColour.WHITE);
        Coordinates position = new Coordinates(4, 4);
        board.placePiece(position, queen);

        List<Move> moves = queen.getAllowedMoves(position, board);

        assertThat(moves).contains(
                new Move(position, new Coordinates(0, 4)),
                new Move(position, new Coordinates(1, 4)),
                new Move(position, new Coordinates(2, 4)),
                new Move(position, new Coordinates(3, 4)),
                new Move(position, new Coordinates(5, 4)),
                new Move(position, new Coordinates(6, 4)),
                new Move(position, new Coordinates(7, 4))
        );
    }

    @Test
    public void queenCanMoveHorizontally() {
        Board board = Board.empty();
        Queen queen = new Queen(PlayerColour.WHITE);
        Coordinates position = new Coordinates(4, 4);
        board.placePiece(position, queen);

        List<Move> moves = queen.getAllowedMoves(position, board);

        assertThat(moves).contains(
                new Move(position, new Coordinates(4, 0)),
                new Move(position, new Coordinates(4, 1)),
                new Move(position, new Coordinates(4, 2)),
                new Move(position, new Coordinates(4, 3)),
                new Move(position, new Coordinates(4, 5)),
                new Move(position, new Coordinates(4, 6)),
                new Move(position, new Coordinates(4, 7))
        );
    }

    @Test
    public void queenCantMoveThroughPiecesOfTheSameColour() {
        Board board = Board.empty();
        Queen queen = new Queen(PlayerColour.WHITE);
        Coordinates queenPosition = new Coordinates(4, 4);
        board.placePiece(queenPosition, queen);
        surroundPiece(board, queenPosition, NORTH);

        // place another piece 2 steps in front.
        board.placePiece(queenPosition.steps(NORTH, 2), new Pawn(PlayerColour.WHITE));

        List<Move> moves = queen.getAllowedMoves(queenPosition, board);

        assertThat(moves).containsOnly(new Move(queenPosition, queenPosition.step(NORTH)));
    }

    @Test
    public void queenCanTakeEnemyPieceButNotMoveThroughIt() {
        Board board = Board.empty();
        Queen queen = new Queen(PlayerColour.WHITE);
        Coordinates queenPosition = new Coordinates(4, 4);
        board.placePiece(queenPosition, queen);
        surroundPiece(board, queenPosition, NORTH);

        // place another piece 2 steps in front.
        board.placePiece(queenPosition.steps(Direction.NORTH, 2), new Pawn(PlayerColour.BLACK));

        List<Move> moves = queen.getAllowedMoves(queenPosition, board);

        assertThat(moves).containsOnly(
                new Move(queenPosition, queenPosition.steps(Direction.NORTH, 1)),
                new Move(queenPosition, queenPosition.steps(Direction.NORTH, 2))
        );
    }
}
