package ui;

import core.Board;
import core.Game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleIO implements IODevice {

    @Override
    public void showBoard(Game minesweeperGame) {
        for (int rowIndex = 0; rowIndex < minesweeperGame.getBoardWidth(); rowIndex++) {
            System.out.print(rowIndex + " |");
            for (int columnIndex = 0; columnIndex < minesweeperGame.getBoardHeight(); columnIndex++) {
                System.out.print(parseCellValue(minesweeperGame.getValueAt(columnIndex, rowIndex)) + "|");
            }
            System.out.println();
        }
        System.out.println("Y");
    }

    @Override
    public UserInput getUserInput(int gameWidth, int gameHeight) {
        System.out.print("Enter cell X. ");
        int selectedCellX = getNumberFromUserWithBounds(0, gameWidth);
        System.out.print("Enter cell Y. ");
        int selectedCellY = getNumberFromUserWithBounds(0, gameHeight);
        System.out.print("Enter action (0-pen or F-1-ag). ");
        int selectedAction = getNumberFromUserWithBounds(0, 2);
        return new UserInput(selectedAction, selectedCellX, selectedCellY);
    }

    @Override
    public void showGameResult(int gameResult) {
        switch (gameResult) {
            case Game.GAME_ACTIVE: System.out.print("Game is still active. Some kind of error has occurred."); break;
            case Game.GAME_WON: System.out.print("Game is won. Congratulations."); break;
            case Game.GAME_LOST_MINE_EXPLODED: System.out.print("You lost. You stepped on a mine."); break;
            case Game.GAME_LOST_TIME_ELAPSED: System.out.print("You lost. You are out of time."); break;
            default: System.out.print("Game is in unknown state.");
        }
    }

    private char parseCellValue(int cellValue) {
        switch (cellValue) {
            case Board.CELL_IS_CLOSED: return '■';
            case Board.CELL_HAS_MINE: return 'X';
            case Board.CELL_IS_FLAGGED: return 'F';
            default: return (char) (cellValue + 48);
        }
    }

    /**
     * Get a number from the user's keyboard
     * @param minNumber lower limit, included
     * @param maxNumber highest number, excluded
     * @return number entered from the user
     */
    private int getNumberFromUserWithBounds(int minNumber, int maxNumber) {
        int number;
        do {
            System.out.print("Number should be valid. ");
            number = getNumberFromUserSafely();
        } while (number < minNumber || number >= maxNumber);
        return number;
    }

    private int getNumberFromUserSafely() {
        int number;
        try {
            number = new Scanner(System.in).nextInt();
        } catch (InputMismatchException exception) {
            System.out.print("Enter only number. ");
            return getNumberFromUserSafely();
        }
        return number;
    }
}
