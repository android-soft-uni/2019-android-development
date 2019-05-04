package ui;

public class UserInput {

    public static final int ACTION_OPEN = 0;
    public static final int ACTION_FLAG = 1;

    private int selectedCellX;
    private int selectedCellY;
    private int selectedAction;

    public int getSelectedCellX() {
        return selectedCellX;
    }

    public int getSelectedCellY() {
        return selectedCellY;
    }

    public int getSelectedAction() {
        return selectedAction;
    }

    UserInput(int selectedAction, int selectedCellX, int selectedCellY) {
        this.selectedAction = selectedAction;
        this.selectedCellX = selectedCellX;
        this.selectedCellY = selectedCellY;
    }
}
