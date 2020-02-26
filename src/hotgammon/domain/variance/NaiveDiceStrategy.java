package hotgammon.domain.variance;

import hotgammon.domain.common.DiceStrategy;
import hotgammon.domain.common.Game;
import hotgammon.domain.common.GameImpl;
import hotgammon.domain.common.Location;

public class NaiveDiceStrategy implements DiceStrategy {
    public void updateDice(Location from, Location to, Game game) {
        switch(game.getNumberOfMovesLeft()) {
            case 0:
            case 1:
                ((GameImpl) game).setDiceValuesLeft(new int[0]);
                break;
            case 2:
                ((GameImpl) game).setDiceValuesLeft(new int[1]);
        }
    }
}
