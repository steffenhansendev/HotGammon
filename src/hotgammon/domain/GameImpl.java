package hotgammon.domain;

import java.util.HashMap;
import java.util.Map;

public class GameImpl implements Game {
    private int[] diceRolled;
    private Color playerInTurn;
    private int turnCount = 0;
    private Color playerInVictory;
    private Map checkerCount = new HashMap<Location, Integer>();
    private Map checkerColor = new HashMap<Location, Color>();
    private int[] diceValuesLeft = new int[0];
    private MoveStrategy moveStrategy;

    public GameImpl(MoveStrategy moveStrategy){
        this.moveStrategy = moveStrategy;
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
        if(turnCount > 6) {
            playerInVictory = Color.RED;
        }
    }

    public boolean move(Location from, Location to) {
        //Validate move for all variants
        boolean isToOccupiedByOpponent =
                checkerColor.get(from) != checkerColor.get(to) &&
                        checkerColor.get(to) != Color.NONE;
        if(isToOccupiedByOpponent) {
            return false;
        }
        boolean checkerIsNotOwnedByPlayerInTurn = playerInTurn != checkerColor.get(from);
        if(checkerIsNotOwnedByPlayerInTurn){
            return false;
        }
        //Validate move for specific variant
        int indexOfValidDice = moveStrategy.validateMoveWithIndexOfValidDice(from, to, this);
        if(indexOfValidDice == -1) {
            return false;
        }
        //Move the checker(s)
        checkerCount.put(from, (int) checkerCount.get(from) - 1);
        checkerCount.put(to, (int) checkerCount.get(to) + 1);
        checkerColor.put(to, checkerColor.get(from));
        //Update unused dice for this turn
        if(diceValuesLeft.length == 0) {
            return false;
        }
        else if(diceValuesLeft.length == 1) {
            diceValuesLeft = new int[0];
        } else {
            indexOfValidDice += 1;
            indexOfValidDice = indexOfValidDice % diceValuesLeft.length;
            diceValuesLeft = new int[]{diceValuesLeft[indexOfValidDice]};
        }
        return true;
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

    //PRIVATE DELEGATIONS
    private void rollTheDice() {
        switch(diceRolled[0]) {
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
        for(Location l : Location.values()) {
            checkerCount.put(l, 0);
            checkerColor.put(l, Color.NONE);
        }
        introduceCheckersAt(Location.B12, 5, Color.RED);
        introduceCheckersAt(Location.B8, 3, Color.BLACK);
        introduceCheckersAt(Location.B6, 5, Color.BLACK);
        introduceCheckersAt(Location.B1, 2, Color.RED);
        introduceCheckersAt(Location.R12, 5, Color.BLACK);
        introduceCheckersAt(Location.R8, 3, Color.RED);
        introduceCheckersAt(Location.R6, 5, Color.RED);
        introduceCheckersAt(Location.R1, 2, Color.BLACK);
    }

    private int validateMoveWithIndexOfValidDice(Location from, Location to) {
        boolean isToOccupiedByOpponent =
                checkerColor.get(from) != checkerColor.get(to) &&
                        checkerColor.get(to) != Color.NONE;
        if(isToOccupiedByOpponent) {
            return -1;
        }
        boolean checkerIsNotOwnedByPlayerInTurn = playerInTurn != checkerColor.get(from);
        if(checkerIsNotOwnedByPlayerInTurn){
            return -1;
        }
        int signedDistanceOfMove = Location.distance(from, to);
        boolean isBackwards =
                playerInTurn == Color.BLACK && signedDistanceOfMove < 0 || playerInTurn == Color.RED && signedDistanceOfMove > 0;
        if(isBackwards) {
            return -1;
        }
        int absoluteDistanceOfMove = Math.abs(signedDistanceOfMove);
        int indexOfValidDice = -1;
        for(int i = 0; i < diceValuesLeft.length; i++) {
            if(diceValuesLeft[i] == absoluteDistanceOfMove) {
                indexOfValidDice = i;
            }
        }
        boolean isDistanceNotAvailable = indexOfValidDice == -1;
        if(isDistanceNotAvailable) {
            return -1;
        }
        return indexOfValidDice;
    }
}