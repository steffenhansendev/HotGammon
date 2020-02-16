package hotgammon.domain;

public class BetaMonMoveStrategy implements MoveStrategy {
    public boolean validateMoveAndUpdateDiceValuesLeft(Location from, Location to, Game game) {
        int signedDistanceOfMove = Location.distance(from, to);
        boolean isBackwards =
                game.getPlayerInTurn() == Color.BLACK && signedDistanceOfMove < 0 || game.getPlayerInTurn() == Color.RED && signedDistanceOfMove > 0;
        if(isBackwards) {
            return false;
        }
        int absoluteDistanceOfMove = Math.abs(signedDistanceOfMove);
        int indexOfValidDice = -1;
        for(int i = 0; i < game.diceValuesLeft().length; i++) {
            if(game.diceValuesLeft()[i] == absoluteDistanceOfMove) {
                indexOfValidDice = i;
            }
        }
        boolean isDistanceNotAvailable = indexOfValidDice == -1;
        if(isDistanceNotAvailable) {
            return false;
        }
        if(game.getNumberOfMovesLeft() == 1) {
            game.setDiceValuesLeft(new int[0]);
        } else {
            int indexOfRemainingDice = indexOfValidDice + 1;
            indexOfRemainingDice = indexOfRemainingDice % game.diceValuesLeft().length;
            game.setDiceValuesLeft(new int[]{game.diceValuesLeft()[indexOfRemainingDice]});
        }
        return true;
    }
}
