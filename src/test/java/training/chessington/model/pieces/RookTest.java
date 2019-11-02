package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class RookTest {

    @Test
    public void rookCanMoveVertically() {
        Board board = Board.empty();
        Rook rook = new Rook(PlayerColour.WHITE);
        Coordinates position = new Coordinates(4, 4);
        board.placePiece(position, rook);

        List<Move> moves = rook.getAllowedMoves(position, board);

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
    public void rookCanMoveHorizontally() {
        Board board = Board.empty();
        Rook rook = new Rook(PlayerColour.WHITE);
        Coordinates position = new Coordinates(4, 4);
        board.placePiece(position, rook);

        List<Move> moves = rook.getAllowedMoves(position, board);

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
}
