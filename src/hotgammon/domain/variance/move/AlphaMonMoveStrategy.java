package hotgammon.domain.variance.move;

import hotgammon.domain.common.Game;
import hotgammon.domain.common.Location;
import hotgammon.domain.common.MoveStrategy;

public class AlphaMonMoveStrategy implements MoveStrategy {

    public boolean resolveHit(Location from, Location to, Game game) {
        return false;
    }

    public int getIndexOfValidForValidMove(Location from, Location to, Game game) {
        return 0;
    }
}