package hotgammon.domain.variants;

import hotgammon.domain.common.Game;
import hotgammon.domain.common.Location;
import hotgammon.domain.common.MoveStrategy;

public class AlphaMonMoveStrategy implements MoveStrategy {

    public boolean isMoveValid(Location from, Location to, Game game) {
        if(game.getNumberOfMovesLeft() < 1) {
            return false;
        }
        return true;
    }

    public boolean resolveHit(Location from, Location to, Game game) {
        return false;
    }

    public void updateDice(Location from, Location to, Game game) {
        switch(game.getNumberOfMovesLeft()) {
            case 0:
            case 1:
                game.setDiceValuesLeft(new int[0]);
                break;
            case 2:
                game.setDiceValuesLeft(new int[1]);
        }
    }
}