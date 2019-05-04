package core;

import ui.IODevice;
import ui.UserInput;

public class Game {

    public static final int GAME_ACTIVE = 0;
    public static final int GAME_WON = 1;
    public static final int GAME_LOST_MINE_EXPLODED = 2;
    public static final int GAME_LOST_TIME_ELAPSED = 3;

    private static final float DEFAULT_MINE_PERCENTAGE = 0.01f * 10;
    private final Board board;
    private IODevice ioDevice;

    public Game(int gameBoardWidth, int gameBoardHeight, IODevice ioDevice) {
        this(gameBoardWidth, gameBoardHeight,
                Math.round(gameBoardWidth * gameBoardHeight * DEFAULT_MINE_PERCENTAGE),
                ioDevice);
    }

    public Game(int gameBoardWidth, int gameBoardHeight, int minesCount, IODevice ioDevice) {
        this.ioDevice = ioDevice;
        this.board = new Board(gameBoardWidth, gameBoardHeight, minesCount);
    }

    public void start() {
        do {
            ioDevice.showBoard(this);
            UserInput userInput = ioDevice.getUserInput(getBoardWidth(), getBoardHeight());
            executeMove(userInput);
        } while(isActive());
        ioDevice.showGameResult(getResult());
    }

    public int getBoardWidth() {
        return board.getWidth();
    }

    public int getBoardHeight() {
        return board.getHeight();
    }

    public int getValueAt(int columnIndex, int rowIndex) {
        return board.getVisibleValue(columnIndex, rowIndex);
    }

    private boolean isActive() {
        return getResult() == GAME_ACTIVE;
    }

    private int getResult() {
        if(board.hasMineOpened()) {
            return GAME_LOST_MINE_EXPLODED;
        } else if(isTimerPassed()) {
            return GAME_LOST_TIME_ELAPSED;
        } else if(board.areAllMinesFlagged()){
            return GAME_WON;
        }
        return GAME_ACTIVE;
    }

    private boolean isTimerPassed() {
        return false;
    }

    private void executeMove(UserInput userInput) {
        switch (userInput.getSelectedAction()) {
            case UserInput.ACTION_OPEN: board.openCell(userInput.getSelectedCellX(), userInput.getSelectedCellY()); break;
            case UserInput.ACTION_FLAG: board.flagCell(userInput.getSelectedCellX(), userInput.getSelectedCellY()); break;
            default: throw new IllegalArgumentException("Wrong user input");
        }
    }
}
