package training.chessington.model;

import org.junit.Test;
import training.chessington.model.pieces.Piece;

import static training.chessington.model.pieces.Piece.PieceType.PAWN;
import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class BoardTest {
    @Test
    public void newBoardHasWhitePiecesAtBottom() {
        // Arrange
        Board board = Board.forNewGame();

        // Act
        Piece piece = board.get(new Coordinates(7, 0));

        // Assert
        assertThat(piece).isColour(PlayerColour.WHITE);
    }

    @Test
    public void newBoardHasBlackPiecesAtTop() {
        // Arrange
        Board board = Board.forNewGame();

        // Act
        Piece piece = board.get(new Coordinates(0, 0));

        // Assert
        assertThat(piece).isColour(PlayerColour.BLACK);
    }

    @Test
    public void canMovePiecesOnBoard() {
        // Arrange
        Board board = Board.forNewGame();

        Coordinates from = new Coordinates(6, 0);
        Coordinates to = new Coordinates(4, 4);

        // Act
        board.move(from, to);

        // Assert
        assertThat(board.get(from)).isNull();
        assertThat(board.get(to)).isColour(PlayerColour.WHITE).isPiece(PAWN);
    }

    @Test
    public void testIsOutOfRange() {
        Board board = Board.empty();

        assertThat(board.isInRange(new Coordinates(-1, 0))).isFalse();
        assertThat(board.isInRange(new Coordinates(0, -1))).isFalse();
        assertThat(board.isInRange(new Coordinates(-1, -1))).isFalse();
        assertThat(board.isInRange(new Coordinates(8, 7))).isFalse();
        assertThat(board.isInRange(new Coordinates(7, 8))).isFalse();
        assertThat(board.isInRange(new Coordinates(8, 8))).isFalse();
    }

    @Test
    public void testIsInRange() {
        Board board = Board.empty();

        assertThat(board.isInRange(new Coordinates(0, 0))).isTrue();
        assertThat(board.isInRange(new Coordinates(0, 7))).isTrue();
        assertThat(board.isInRange(new Coordinates(7, 0))).isTrue();
        assertThat(board.isInRange(new Coordinates(7, 7))).isTrue();
        assertThat(board.isInRange(new Coordinates(4, 4))).isTrue();
    }
}