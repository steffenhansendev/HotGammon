package hotgammon.domain.variance;

import hotgammon.domain.common.Game;
import hotgammon.domain.common.GameImpl;
import hotgammon.domain.common.Location;
import hotgammon.domain.common.MoveStrategy;

public class AlphaMonMoveStrategy implements MoveStrategy {

    public boolean isMoveValid(Location from, Location to, Game game) {
        return true;
    }

    public boolean resolveHit(Location from, Location to, Game game) {
        return false;
    }

}