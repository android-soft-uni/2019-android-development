package ui;

import core.Game;

public interface IODevice {
    void showBoard(Game minesweeperGame);

    UserInput getUserInput(int gameWidth, int gameHeight);

    void showGameResult(int result);
}
