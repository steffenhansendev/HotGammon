package hotgammon.domain.variance;

import hotgammon.domain.common.DiceStrategy;
import hotgammon.domain.common.Game;
import hotgammon.domain.common.GameImpl;

public class RollMoveDiceStrategy implements DiceStrategy {
    public void updateDiceValuesLeft(int indexOfValidDice, Game game) {
        if(game.getNumberOfMovesLeft() == 1) {
            ((GameImpl) game).setDiceValuesLeft(new int[0]);
        } else {
            int indexOfRemainingDice = indexOfValidDice + 1;
            indexOfRemainingDice = indexOfRemainingDice % game.diceValuesLeft().length;
            ((GameImpl) game).setDiceValuesLeft(new int[]{game.diceValuesLeft()[indexOfRemainingDice]});
        }
    }
}
