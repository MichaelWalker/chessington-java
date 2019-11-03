package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPiece implements Piece {

    protected final Piece.PieceType type;
    protected final PlayerColour colour;

    protected AbstractPiece(Piece.PieceType type, PlayerColour colour) {
        this.type = type;
        this.colour = colour;
    }

    @Override
    public Piece.PieceType getType() {
        return type;
    }

    @Override
    public PlayerColour getColour() {
        return colour;
    }

    @Override
    public String toString() {
        return colour.toString() + " " + type.toString();
    }

    protected List<Move> movesInDirection(Coordinates from, Board board, Direction direction) {
        List<Move> moves = new ArrayList<>();
        Coordinates nextSquare = from.step(direction);

        while (board.hasSquare(nextSquare)) {
            if (containsFriend(board, nextSquare)) {
                return moves;
            }

            moves.add(new Move(from, nextSquare));

            if (containsEnemy(board, nextSquare)) {
                return moves;
            }

            nextSquare = nextSquare.step(direction);
        }

        return moves;
    }

    protected boolean containsEnemy(Board board, Coordinates coordinates) {
        return colour == PlayerColour.WHITE ?
                board.hasBlackPieceAt(coordinates) :
                board.hasWhitePieceAt(coordinates);
    }

    protected boolean containsFriend(Board board, Coordinates coordinates) {
        return colour == PlayerColour.WHITE ?
                board.hasWhitePieceAt(coordinates) :
                board.hasBlackPieceAt(coordinates);
    }
}
