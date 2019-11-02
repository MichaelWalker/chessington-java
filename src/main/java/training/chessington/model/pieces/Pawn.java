package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> moves = new ArrayList<>();
        moves.addAll(forwardsMoves(from, board));
        moves.addAll(captureMoves(from, board));
        return moves;
    }

    private boolean pieceIsUnmoved(Coordinates currentPosition) {
        if (colour == PlayerColour.WHITE) {
            return currentPosition.getRow() == 6;
        }
        return currentPosition.getRow() == 1;
    }

    private List<Move> forwardsMoves(Coordinates from, Board board) {
        int maxRange = pieceIsUnmoved(from) ? 2 : 1;

        List<Move> moves = new ArrayList<>();
        Coordinates nextSquare = from.duplicate();

        for (int distance = 1; distance <= maxRange; distance++) {
            nextSquare = nextSquare.plus(forward(), 0);
            if (board.isInRange(nextSquare) && board.isEmptyAt(nextSquare)) {
                moves.add(new Move(from, nextSquare));
            } else {
                return moves;
            }
        }

        return moves;
    }

    private List<Move> captureMoves(Coordinates from, Board board) {
        List<Coordinates> possibleCaptureSquares = List.of(
                from.plus(forward(), 1),
                from.plus(forward(), -1)
        );

        return possibleCaptureSquares.stream()
                .filter(board::isInRange)
                .filter(square -> containsEnemy(board, square))
                .map(square -> new Move(from, square))
                .collect(Collectors.toList());
    }

    private int forward() {
        return colour == PlayerColour.WHITE ? -1 : 1;
    }

    private boolean containsEnemy(Board board, Coordinates coordinates) {
        return colour == PlayerColour.WHITE ?
                board.hasBlackPieceAt(coordinates) :
                board.hasWhitePieceAt(coordinates);
    }
}
