package training.chessington.model.pieces;

public enum Direction {
    NORTH(-1, 0),
    SOUTH(1, 0),
    EAST(0, 1),
    WEST(0, -1);

    public int changeInRow;
    public int changeInCol;

    Direction(int changeInRow, int changeInCol) {
        this.changeInRow = changeInRow;
        this.changeInCol = changeInCol;
    }
}
