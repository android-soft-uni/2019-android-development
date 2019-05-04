package core;

public class Board {

    public static final int CELL_IS_CLOSED = -1;
    public static final int CELL_HAS_MINE = -2;
    public static final int CELL_IS_FLAGGED = -3;

    /**
     * Board with all the values - mines and numbers
     */
    private int[][] valueBoard;
    /**
     * Board with opened from the user values
     */
    private int[][] visibleBoard;

    Board(int gameBoardWidth, int gameBoardHeight, int minesCount) {
        valueBoard = BoardUtils.generateValueBoard(gameBoardWidth, gameBoardHeight, minesCount);
        visibleBoard = BoardUtils.createFilledArray(gameBoardWidth, gameBoardHeight, CELL_IS_CLOSED);
    }

    Board() {
        valueBoard = BoardUtils.generateValueBoard(5, 5, 5);
        visibleBoard = BoardUtils.createFilledArray(5, 5, CELL_IS_CLOSED);
    }

    public int getWidth() {
        return valueBoard.length;
    }

    public int getHeight() {
        return valueBoard[0].length;
    }

    public int getVisibleValue(int columnIndex, int rowIndex) {
        return visibleBoard[columnIndex][rowIndex];
    }

    public void openCell(int x, int y) {
        if(x < 0 || x >= visibleBoard.length || y < 0 || y >= visibleBoard[0].length) return;
        if(visibleBoard[x][y] != Board.CELL_IS_CLOSED && visibleBoard[x][y] != Board.CELL_IS_FLAGGED) return;

        visibleBoard[x][y] = valueBoard[x][y];
        if(visibleBoard[x][y] == 0) {
            openCell(x - 1, y);
            openCell(x - 1, y - 1);
            openCell(x - 1, y + 1);
            openCell(x, y - 1);
            openCell(x, y + 1);
            openCell(x + 1, y - 1);
            openCell(x + 1, y);
            openCell(x + 1, y + 1);
        }
    }

    public void flagCell(int x, int y) {
        visibleBoard[x][y] = CELL_IS_FLAGGED;
    }

    public boolean hasMineOpened() {
        return BoardUtils.hasMineOpened(visibleBoard);
    }

    public boolean areAllMinesFlagged() {
        return BoardUtils.areAllMinesFlagged(valueBoard, visibleBoard);
    }


}
