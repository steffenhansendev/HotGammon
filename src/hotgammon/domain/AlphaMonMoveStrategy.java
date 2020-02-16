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
        return 0;
    }
}
