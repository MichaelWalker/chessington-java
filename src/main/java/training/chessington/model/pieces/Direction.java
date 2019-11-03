package training.chessington.model.pieces;

public enum Direction {
    NORTH(-1, 0),
    NORTH_EAST(-1, 1),
    EAST(0, 1),
    SOUTH_EAST(1, 1),
    SOUTH(1, 0),
    SOUTH_WEST(1, -1),
    WEST(0, -1),
    NORTH_WEST(-1, -1);

    public int changeInRow;
    public int changeInCol;

    Direction(int changeInRow, int changeInCol) {
        this.changeInRow = changeInRow;
        this.changeInCol = changeInCol;
    }
}
