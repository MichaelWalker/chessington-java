package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static training.chessington.model.PlayerColour.BLACK;
import static training.chessington.model.PlayerColour.WHITE;
import static training.chessington.model.pieces.Direction.NORTH;

public class AbstractPieceTest {


    @Test
    public void getMovesInDirectionContinuesUntilEndOfBoard() {
        Board board = Board.empty();
        Piece piece = new TestPiece(WHITE);
        Coordinates position = new Coordinates(4, 4);
        board.placePiece(position, piece);

        List<Move> moves = piece.getAllowedMoves(position, board);

        assertThat(moves).containsOnlyOnce(
                new Move(position, new Coordinates(3, 4)),
                new Move(position, new Coordinates(2, 4)),
                new Move(position, new Coordinates(1, 4)),
                new Move(position, new Coordinates(0, 4))
        );
    }

    @Test
    public void getMovesInDirectionContinuesUntilFriendlyPiece() {
        Board board = Board.empty();
        Piece piece = new TestPiece(WHITE);
        Coordinates position = new Coordinates(4, 4);
        board.placePiece(position, piece);
        board.placePiece(new Coordinates(2, 4), new Pawn(WHITE));

        List<Move> moves = piece.getAllowedMoves(position, board);

        assertThat(moves).containsOnlyOnce(
                new Move(position, new Coordinates(3, 4))
        );
    }

    @Test
    public void getMovesInDirectionTakesEnemyButCantGoThroughIt() {
        Board board = Board.empty();
        Piece piece = new TestPiece(WHITE);
        Coordinates position = new Coordinates(4, 4);
        board.placePiece(position, piece);
        board.placePiece(new Coordinates(2, 4), new Pawn(BLACK));

        List<Move> moves = piece.getAllowedMoves(position, board);

        assertThat(moves).containsOnlyOnce(
                new Move(position, new Coordinates(3, 4)),
                new Move(position, new Coordinates(2, 4))
        );
    }

    @Test
    public void getMovesInDirectionContinuesUntilMaxRange() {
        Board board = Board.empty();
        TestPiece piece = new TestPiece(WHITE);
        Coordinates position = new Coordinates(4, 4);
        board.placePiece(position, piece);

        List<Move> moves = piece.getAllowedMovesWithinRange(position, board, 2);

        assertThat(moves).containsOnlyOnce(
                new Move(position, new Coordinates(3, 4)),
                new Move(position, new Coordinates(2, 4))
        );
    }

    private static class TestPiece extends AbstractPiece {

        protected TestPiece(PlayerColour colour) {
            super(null, colour);
        }

        @Override
        public List<Move> getAllowedMoves(Coordinates from, Board board) {
            return movesInDirection(from, board, NORTH);
        }

        public List<Move> getAllowedMovesWithinRange(Coordinates from, Board board, Integer range) {
            return movesInDirection(from, board, NORTH, range);
        }
    }
}
