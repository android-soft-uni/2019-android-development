package core;

import java.util.Random;

public class BoardUtils {

    public static int[][] createFilledArray(int gameBoardWidth, int gameBoardHeight, int cellValue) {
        int[][] filledArray = new int[gameBoardWidth][gameBoardHeight];
        for (int x = 0; x < gameBoardWidth; x++) {
            for (int y = 0; y < gameBoardHeight; y++) {
                filledArray[x][y] = cellValue;
            }
        }
        return filledArray;
    }

    public static int[][] generateValueBoard(int gameBoardWidth, int gameBoardHeight, int minesCount) {
        int[][] arrayWithMines = getArrayWithMines(gameBoardWidth, gameBoardHeight, minesCount);
        for (int x = 0; x < gameBoardWidth; x++) {
            for (int y = 0; y < gameBoardHeight; y++) {
                if (arrayWithMines[x][y] != Board.CELL_HAS_MINE) {
                    arrayWithMines[x][y] = getMinesCountNearby(x, y, arrayWithMines);
                }
            }
        }
        return arrayWithMines;
    }

    private static int getMinesCountNearby(int x, int y, int[][] arrayWithMines) {
        int mineCount = 0;
        int boardWidth = arrayWithMines.length;
        int boardHeight = arrayWithMines[0].length;
        if (x - 1 >= 0 && y + 1 < boardHeight && arrayWithMines[x - 1][y + 1] == Board.CELL_HAS_MINE) mineCount++;
        if (x >= 0 && y + 1 < boardHeight && arrayWithMines[x][y + 1] == Board.CELL_HAS_MINE) mineCount++;
        if (x + 1 < boardWidth && y + 1 < boardHeight && arrayWithMines[x + 1][y + 1] == Board.CELL_HAS_MINE) mineCount++;
        if (x + 1 < boardWidth && y < boardHeight && arrayWithMines[x + 1][y] == Board.CELL_HAS_MINE) mineCount++;
        if (x + 1 < boardWidth && y - 1 >= 0 && arrayWithMines[x + 1][y - 1] == Board.CELL_HAS_MINE) mineCount++;
        if (x < boardWidth && y - 1 >= 0 && arrayWithMines[x][y - 1] == Board.CELL_HAS_MINE) mineCount++;
        if (x - 1 >= 0 && y - 1 >= 0 && arrayWithMines[x - 1][y - 1] == Board.CELL_HAS_MINE) mineCount++;
        if (x - 1 >= 0 && y >= 0 && arrayWithMines[x - 1][y] == Board.CELL_HAS_MINE) mineCount++;
        return mineCount;
    }

    private static int[][] getArrayWithMines(int gameBoardWidth, int gameBoardHeight, int minesCount) {
        int[][] arrayWithMines = new int[gameBoardWidth][gameBoardHeight];
        int placedMines = 0;
        while (placedMines != minesCount) {
            int mineX = new Random().nextInt(gameBoardWidth);
            int mineY = new Random().nextInt(gameBoardHeight);
            if (arrayWithMines[mineX][mineY] != Board.CELL_HAS_MINE) {
                arrayWithMines[mineX][mineY] = Board.CELL_HAS_MINE;
                placedMines++;
            }
        }
        return arrayWithMines;
    }

    public static boolean hasMineOpened(int[][] visibleBoard) {
        for (int x = 0; x < visibleBoard.length; x++) {
            for (int y = 0; y < visibleBoard[0].length; y++) {
                if (visibleBoard[x][y] == Board.CELL_HAS_MINE) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean areAllMinesFlagged(int[][] valueBoard, int[][] visibleBoard) {
        for (int x = 0; x < visibleBoard.length; x++) {
            for (int y = 0; y < visibleBoard[0].length; y++) {
                if (valueBoard[x][y] == Board.CELL_HAS_MINE && visibleBoard[x][y] != Board.CELL_IS_FLAGGED) {
                    return false;
                }
            }
        }
        return true;
    }
}
