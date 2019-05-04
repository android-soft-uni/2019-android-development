import softuni_demo.Circle;
import softuni_demo.shelter.Shelter;

public class App {

    private static final int HEIGHT = 10;
    private static final int WIDTH = 10;
    private static final int MINES_COUNT = 10;

    public static void main(String[] args) {
        IODevice ioDevice = new SwingIO();
        Game minesweeperGame  = new Game(WIDTH, HEIGHT, MINES_COUNT, ioDevice);
        minesweeperGame.start();
    }

}
