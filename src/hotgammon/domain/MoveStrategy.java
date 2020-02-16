package hotgammon.domain;

import java.util.Map;

public interface MoveStrategy {
    public int validateMoveWithIndexOfValidDice(Location from, Location to, Map<Location, Color> checkerColor, Map<Location, Integer> checkerCount, Color playerInTurn, int[] diceValuesLeft);
}
