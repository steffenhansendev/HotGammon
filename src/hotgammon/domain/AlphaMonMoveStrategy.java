package hotgammon.domain;

import java.util.Map;

public class AlphaMonMoveStrategy implements MoveStrategy {
    public int validateMoveWithIndexOfValidDice(Location from, Location to, Map<Location, Color> checkerColor, Map<Location, Integer> checkerCount, Color playerInTurn, int[] diceValuesLeft) {
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
