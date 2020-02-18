package hotgammon.domain.variants;

import hotgammon.domain.common.*;

public class BetaMonMoveStrategy implements MoveStrategy {
    private int signedDistanceOfMove = -1;
    private int indexOfValidDice = -1;

    public boolean isMoveValid(Location from, Location to, Game game) {
        signedDistanceOfMove = Location.distance(from, to);
        boolean isBackwards =
                game.getPlayerInTurn() == Color.BLACK && signedDistanceOfMove < 0 || game.getPlayerInTurn() == Color.RED && signedDistanceOfMove > 0;
        if(isBackwards) {
            return false;
        }
        int absoluteDistanceOfMove = Math.abs(signedDistanceOfMove);
        indexOfValidDice = -1;
        for(int i = 0; i < game.diceValuesLeft().length; i++) {
            if(game.diceValuesLeft()[i] == absoluteDistanceOfMove) {
                indexOfValidDice = i;
            }
        }
        boolean isDistanceNotAvailable = indexOfValidDice == -1;
        if(isDistanceNotAvailable) {
            return false;
        }
        return true;
    }

    public boolean resolveHit(Location from, Location to, Game game) {
        return false;
    }

    public void updateDice(Location from, Location to, Game game) {
        if(game.getNumberOfMovesLeft() == 1) {
            ((GameImpl) game).setDiceValuesLeft(new int[0]);
        } else {
            int indexOfRemainingDice = indexOfValidDice + 1;
            indexOfRemainingDice = indexOfRemainingDice % game.diceValuesLeft().length;
            ((GameImpl) game).setDiceValuesLeft(new int[]{game.diceValuesLeft()[indexOfRemainingDice]});
        }
    }
}
