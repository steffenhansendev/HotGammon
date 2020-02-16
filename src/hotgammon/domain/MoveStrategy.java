package hotgammon.domain;

import java.util.Map;

public interface MoveStrategy {
    public int validateMoveWithIndexOfValidDice(Location from, Location to, Game game);
}
