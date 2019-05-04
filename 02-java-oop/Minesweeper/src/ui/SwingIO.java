package ui;

import core.Game;

import javax.swing.*;

public class SwingIO extends JFrame implements IODevice {

    @Override
    public void showBoard(Game minesweeperGame) {
//        add(new JButton())
    }

    @Override
    public UserInput getUserInput(int gameWidth, int gameHeight) {
        return null;
    }

    @Override
    public void showGameResult(int result) {

    }
}
