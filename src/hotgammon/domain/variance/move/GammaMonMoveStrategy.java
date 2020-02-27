package hotgammon.domain.variance.move;

import hotgammon.domain.common.Game;
import hotgammon.domain.common.GameImpl;
import hotgammon.domain.common.Location;
import hotgammon.domain.common.MoveStrategy;

public class GammaMonMoveStrategy implements MoveStrategy {

    public boolean isMoveValid(Location from, Location to, Game game) {
        return true;
    }

    public boolean resolveHit(Location from, Location to, Game game) {
        return false;
    }

    public int getIndexOfValidForValidMove(Location from, Location to, Game game) {
        return 0;
    }

    public void updateDice(Location from, Location to, Game game) {
        switch (game.getNumberOfMovesLeft()) {
            case 0:
            case 1:
                ((GameImpl) game).setDiceValuesLeft(new int[0]);
                break;
            case 2:
                ((GameImpl) game).setDiceValuesLeft(new int[1]);
        }
    }
}
