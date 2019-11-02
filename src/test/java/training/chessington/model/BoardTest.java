package training.chessington.model;

import org.junit.Test;
import training.chessington.model.pieces.Pawn;
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
    public void hasSquareReturnsFalseIfCoordinateIsOutsideBoard() {
        Board board = Board.empty();

        assertThat(board.hasSquare(new Coordinates(-1, 0))).isFalse();
        assertThat(board.hasSquare(new Coordinates(0, -1))).isFalse();
        assertThat(board.hasSquare(new Coordinates(-1, -1))).isFalse();
        assertThat(board.hasSquare(new Coordinates(8, 7))).isFalse();
        assertThat(board.hasSquare(new Coordinates(7, 8))).isFalse();
        assertThat(board.hasSquare(new Coordinates(8, 8))).isFalse();
    }

    @Test
    public void hasSquareReturnsTrueIfCoordinateIsWithinBoard() {
        Board board = Board.empty();

        assertThat(board.hasSquare(new Coordinates(0, 0))).isTrue();
        assertThat(board.hasSquare(new Coordinates(0, 7))).isTrue();
        assertThat(board.hasSquare(new Coordinates(7, 0))).isTrue();
        assertThat(board.hasSquare(new Coordinates(7, 7))).isTrue();
        assertThat(board.hasSquare(new Coordinates(4, 4))).isTrue();
    }

    @Test
    public void isEmptyAtReturnsTrueIfSquareIsEmpty() {
        Board board = Board.empty();

        assertThat(board.isEmptyAt(new Coordinates(4, 4))).isTrue();
    }

    @Test
    public void isEmptyAtReturnsFalseIfSquareContainsAPiece() {
        Board board = Board.empty();
        Piece piece = new Pawn(PlayerColour.WHITE);
        Coordinates piecePosition = new Coordinates(4, 4);
        board.placePiece(piecePosition, piece);

        assertThat(board.isEmptyAt(piecePosition)).isFalse();
    }

    @Test
    public void containsWhitePieceAtReturnsFalseIfSquareIsEmpty() {
        Board board = Board.empty();
        Coordinates position = new Coordinates(4, 4);

        assertThat(board.hasWhitePieceAt(position)).isFalse();
    }

    @Test
    public void containsWhitePieceAtReturnsFalseIfSquareContainsBlackPiece() {
        Board board = Board.empty();
        Coordinates position = new Coordinates(4, 4);
        Piece piece = new Pawn(PlayerColour.BLACK);
        board.placePiece(position, piece);

        assertThat(board.hasWhitePieceAt(position)).isFalse();
    }

    @Test
    public void containsWhitePieceAtReturnsTrueIfSquareContainsWhitePiece() {
        Board board = Board.empty();
        Coordinates position = new Coordinates(4, 4);
        Piece piece = new Pawn(PlayerColour.WHITE);
        board.placePiece(position, piece);

        assertThat(board.hasWhitePieceAt(position)).isTrue();
    }

    @Test
    public void containsBlackPieceAtReturnsFalseIfSquareIsEmpty() {
        Board board = Board.empty();
        Coordinates position = new Coordinates(4, 4);

        assertThat(board.hasBlackPieceAt(position)).isFalse();
    }

    @Test
    public void containsBlackPieceAtReturnsFalseIfSquareContainsWhitePiece() {
        Board board = Board.empty();
        Coordinates position = new Coordinates(4, 4);
        Piece piece = new Pawn(PlayerColour.WHITE);
        board.placePiece(position, piece);

        assertThat(board.hasBlackPieceAt(position)).isFalse();
    }

    @Test
    public void containsBlackPieceAtReturnsTrueIfSquareContainsBlackPiece() {
        Board board = Board.empty();
        Coordinates position = new Coordinates(4, 4);
        Piece piece = new Pawn(PlayerColour.BLACK);
        board.placePiece(position, piece);

        assertThat(board.hasBlackPieceAt(position)).isTrue();
    }
}