package hotgammon.domain.variance;

import hotgammon.domain.common.DiceStrategy;
import hotgammon.domain.common.Game;
import hotgammon.domain.common.GameImpl;
import hotgammon.domain.common.Location;

public class RealDiceStrategy implements DiceStrategy {

    private int indexOfValidDice = -1;
    private int signedDistanceOfMove = -1;

    public void updateDice(Location from, Location to, Game game) {
        signedDistanceOfMove = Location.distance(from, to);
        int absoluteDistanceOfMove = Math.abs(signedDistanceOfMove);

        for(int i = 0; i < game.diceValuesLeft().length; i++) {
            if(game.diceValuesLeft()[i] == absoluteDistanceOfMove) {
                indexOfValidDice = i;
            }
        }
        if(game.getNumberOfMovesLeft() == 1) {
            ((GameImpl) game).setDiceValuesLeft(new int[0]);
        } else {
            int indexOfRemainingDice = indexOfValidDice + 1;
            indexOfRemainingDice = indexOfRemainingDice % game.diceValuesLeft().length;
            ((GameImpl) game).setDiceValuesLeft(new int[]{game.diceValuesLeft()[indexOfRemainingDice]});
        }
    }
}
