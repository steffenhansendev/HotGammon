package hotgammon.domain.common;

import java.util.HashMap;
import java.util.Map;

public class GameImpl implements Game {
    private int[] diceRolled;
    private Color playerInTurn;
    private int turnCount = 0;
    private Color playerInVictory;
    private Map<Location, Integer> checkerCount = new HashMap<Location, Integer>();
    private Map<Location, Color> checkerColor = new HashMap<Location, Color>();
    private int[] diceValuesLeft = new int[0];
    private MoveStrategy moveStrategy;
    private DiceStrategy diceStrategy;
    private boolean hasRedInnerTableBeenFilled = false;
    private boolean hasBlackInnerTableBeenFilled = false;

    public GameImpl(MoveStrategy moveStrategy, DiceStrategy diceStrategy) {
        this.moveStrategy = moveStrategy;
        this.diceStrategy = diceStrategy;
        diceRolled = new int[2];
        turnCount = 0;
    }

    public void newGame() {
        diceRolled[0] = -1;
        diceRolled[1] = -1;
        playerInTurn = Color.NONE;
        playerInVictory = Color.NONE;
        initializeCheckers();
    }

    public void nextTurn() {
        rollTheDice();
        changePlayerInTurn();
        turnCount += 1;
        if (turnCount > 6) {
            playerInVictory = Color.RED;
        }
    }

    public boolean move(Location from, Location to) {

        boolean checkerIsNotOwnedByPlayerInTurn = playerInTurn != checkerColor.get(from);
        if (checkerIsNotOwnedByPlayerInTurn) {
            return false;
        }

        boolean numberOfMovesLeftHasBeenExhausted = getNumberOfMovesLeft() < 1;
        if (numberOfMovesLeftHasBeenExhausted) {
            return false;
        }

        int indexOfValidDice = moveStrategy.getIndexOfValidForValidMove(from, to, this);

        boolean isMoveValid = indexOfValidDice != -1;
        if (!isMoveValid) {
            return false;
        }

        boolean isMoveHit = checkerColor.get(from) != checkerColor.get(to) && checkerColor.get(to) != Color.NONE && (int) checkerCount.get(to) > 0;

        boolean hasCheckersBeenMoved = false;

        if (isMoveHit) {
            hasCheckersBeenMoved = moveStrategy.resolveHit(from, to, this);
        } else {
            checkerCount.put(from, (int) checkerCount.get(from) - 1);
            checkerCount.put(to, (int) checkerCount.get(to) + 1);
            checkerColor.put(to, checkerColor.get(from));
            hasCheckersBeenMoved = true;
        }

        if (hasCheckersBeenMoved) {
            diceStrategy.updateDiceValuesLeft(indexOfValidDice, this);
            return true;
        }

        return false;
    }

    public Color getPlayerInTurn() {
        return playerInTurn;
    }

    public int getNumberOfMovesLeft() {
        return diceValuesLeft.length;
    }

    public int[] diceThrown() {
        return diceRolled;
    }

    public int[] diceValuesLeft() {
        return diceValuesLeft;
    }

    public Color winner() {
        return playerInVictory;
    }

    public Color getColor(Location location) {
        return (Color) checkerColor.get(location);
    }

    public int getCount(Location location) {
        return (int) checkerCount.get(location);
    }

    public void setDiceValuesLeft(int[] diceValuesLeft) {
        this.diceValuesLeft = diceValuesLeft;
    }

    public Map<Location, Integer> getCheckerCount() {
        return checkerCount;
    }

    public Map<Location, Color> getCheckerColor() {
        return checkerColor;
    }

    public boolean getHasBlackInnerTableBeenFilled() {
        return hasBlackInnerTableBeenFilled;
    }

    public boolean getHasRedInnerTableBeenFilled() {
        return hasRedInnerTableBeenFilled;
    }

    private void rollTheDice() {
        switch (diceRolled[0]) {
            case -1:
            case 5:
                diceRolled[0] = 1;
                diceRolled[1] = 2;
                break;
            case 1:
                diceRolled[0] = 3;
                diceRolled[1] = 4;
                break;
            case 3:
                diceRolled[0] = 5;
                diceRolled[1] = 6;
                break;
            default:
        }
        diceValuesLeft = new int[]{diceRolled[1], diceRolled[0]};
    }

    private void changePlayerInTurn() {
        switch (playerInTurn) {
            case NONE:
            case RED:
                playerInTurn = Color.BLACK;
                break;
            case BLACK:
                playerInTurn = Color.RED;
                break;
            default:
        }
    }

    private void introduceCheckersAt(Location location, int count, Color color) {
        checkerCount.put(location, count);
        checkerColor.put(location, color);
    }

    private void initializeCheckers() {
        for (Location l : Location.values()) {
            checkerCount.put(l, 0);
            checkerColor.put(l, Color.NONE);
        }
        introduceCheckersAt(Location.B_BAR, 0, Color.BLACK);
        introduceCheckersAt(Location.R_BAR, 0, Color.RED);
        introduceCheckersAt(Location.B12, 5, Color.RED);
        introduceCheckersAt(Location.B8, 3, Color.BLACK);
        introduceCheckersAt(Location.B6, 5, Color.BLACK);
        introduceCheckersAt(Location.B1, 2, Color.RED);
        introduceCheckersAt(Location.R12, 5, Color.BLACK);
        introduceCheckersAt(Location.R8, 3, Color.RED);
        introduceCheckersAt(Location.R6, 5, Color.RED);
        introduceCheckersAt(Location.R1, 2, Color.BLACK);
    }
}