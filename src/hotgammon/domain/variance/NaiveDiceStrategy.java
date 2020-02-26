package hotgammon.domain.variance;

import hotgammon.domain.common.DiceStrategy;
import hotgammon.domain.common.Game;
import hotgammon.domain.common.GameImpl;

public class NaiveDiceStrategy implements DiceStrategy {
    public void updateDiceValuesLeft(int indexOfValidDice, Game game) {
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
