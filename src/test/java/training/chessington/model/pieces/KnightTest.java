package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static training.chessington.model.PlayerColour.BLACK;
import static training.chessington.model.PlayerColour.WHITE;
import static training.chessington.model.TestHelpers.surroundPiece;

public class KnightTest {
    @Test
    public void knightCanMoveInLShape() {
        Board board = Board.empty();
        Coordinates knightPosition = new Coordinates(4,4);
        Knight knight = new Knight(WHITE);
        board.placePiece(knightPosition, knight);

        List<Move> moves = knight.getAllowedMoves(knightPosition, board);

        assertThat(moves).containsOnlyOnce(
                new Move(knightPosition, new Coordinates(6, 5)),
                new Move(knightPosition, new Coordinates(6, 3)),
                new Move(knightPosition, new Coordinates(2, 5)),
                new Move(knightPosition, new Coordinates(2, 3)),
                new Move(knightPosition, new Coordinates(3, 2)),
                new Move(knightPosition, new Coordinates(3, 6)),
                new Move(knightPosition, new Coordinates(5, 2)),
                new Move(knightPosition, new Coordinates(5, 6))
        );
    }

    @Test
    public void knightIsNotBlockedByPieces() {
        Board board = Board.empty();
        Coordinates knightPosition = new Coordinates(4,4);
        Knight knight = new Knight(WHITE);
        board.placePiece(knightPosition, knight);
        surroundPiece(board, knightPosition, null);

        List<Move> moves = knight.getAllowedMoves(knightPosition, board);

        assertThat(moves).hasSize(8);
    }

    @Test
    public void knightCannotMoveToSquareOccupiedByFriendlyPiece() {
        Board board = Board.empty();
        Coordinates knightPosition = new Coordinates(4,4);
        Knight knight = new Knight(WHITE);
        board.placePiece(knightPosition, knight);
        board.placePiece(new Coordinates(6, 5), new Pawn(WHITE));

        List<Move> moves = knight.getAllowedMoves(knightPosition, board);

        assertThat(moves).doesNotContain(new Move(knightPosition, new Coordinates(6, 5)));
        assertThat(moves).hasSize(7);
    }

    @Test
    public void knightCanTakeEnemyPieces() {
        Board board = Board.empty();
        Coordinates knightPosition = new Coordinates(4,4);
        Knight knight = new Knight(WHITE);
        board.placePiece(knightPosition, knight);
        board.placePiece(new Coordinates(6, 5), new Pawn(BLACK));

        List<Move> moves = knight.getAllowedMoves(knightPosition, board);

        assertThat(moves).contains(new Move(knightPosition, new Coordinates(6, 5)));
        assertThat(moves).hasSize(8);
    }

    @Test
    public void knightCannotLeaveBoard() {
        Board board = Board.empty();
        Coordinates knightPosition = new Coordinates(0,0);
        Knight knight = new Knight(WHITE);
        board.placePiece(knightPosition, knight);

        List<Move> moves = knight.getAllowedMoves(knightPosition, board);

        assertThat(moves).containsOnlyOnce(
                new Move(knightPosition, new Coordinates(2, 1)),
                new Move(knightPosition, new Coordinates(1, 2))
        );
    }
}
