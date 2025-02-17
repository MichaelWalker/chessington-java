package training.chessington.model;

import training.chessington.model.pieces.Direction;

import java.util.Objects;

public final class Coordinates {
    private final int row;
    private final int col;

    public Coordinates(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return row == that.row &&
                col == that.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return String.format("row %d, column %d", row, col);
    }

    public Coordinates plus(int rowDiff, int colDiff) {
        return new Coordinates(row + rowDiff, col + colDiff);
    }

    public Coordinates step(Direction direction) {
        return steps(direction, 1);
    }

    public Coordinates steps(Direction direction, int times) {
        return plus(direction.changeInRow * times, direction.changeInCol * times);
    }

    public Coordinates duplicate() {
        return new Coordinates(row, col);
    }
}
