package training.chessington.model;

import training.chessington.model.pieces.*;

public class Board {
    public static final int SIZE = 8;

    private Piece[][] board = new Piece[SIZE][SIZE];

    private Board() {
    }

    public static Board forNewGame() {
        Board board = new Board();
        board.setBackRow(0, PlayerColour.BLACK);
        board.setBackRow(7, PlayerColour.WHITE);

        for (int col = 0; col < SIZE; col++) {
            board.board[1][col] = new Pawn(PlayerColour.BLACK);
            board.board[6][col] = new Pawn(PlayerColour.WHITE);
        }

        return board;
    }

    public static Board empty() {
        return new Board();
    }

    private void setBackRow(int rowIndex, PlayerColour colour) {
        board[rowIndex][0] = new Rook(colour);
        board[rowIndex][1] = new Knight(colour);
        board[rowIndex][2] = new Bishop(colour);
        board[rowIndex][3] = new Queen(colour);
        board[rowIndex][4] = new King(colour);
        board[rowIndex][5] = new Bishop(colour);
        board[rowIndex][6] = new Knight(colour);
        board[rowIndex][7] = new Rook(colour);
    }

    public Piece get(Coordinates coords) {
        return board[coords.getRow()][coords.getCol()];
    }

    public void move(Coordinates from, Coordinates to) {
        board[to.getRow()][to.getCol()] = board[from.getRow()][from.getCol()];
        board[from.getRow()][from.getCol()] = null;
    }

    public void placePiece(Coordinates coords, Piece piece) {
        board[coords.getRow()][coords.getCol()] = piece;
    }

    public boolean hasSquare(Coordinates coordinates) {
        return coordinates.getRow() >= 0 && coordinates.getRow() < SIZE
                && coordinates.getCol() >= 0 && coordinates.getCol() < SIZE;
    }

    public boolean isEmptyAt(Coordinates coordinates) {
        return get(coordinates) == null;
    }

    public boolean hasWhitePieceAt(Coordinates coordinates) {
        return hasPieceOfColourAt(coordinates, PlayerColour.WHITE);
    }

    public boolean hasBlackPieceAt(Coordinates coordinates) {
        return hasPieceOfColourAt(coordinates, PlayerColour.BLACK);
    }

    private boolean hasPieceOfColourAt(Coordinates coordinates, PlayerColour colour) {
        if (isEmptyAt(coordinates)) {
            return false;
        }
        return get(coordinates).getColour() == colour;
    }
}
