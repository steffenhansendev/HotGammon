package hotgammon.domain;

import java.util.Map;

public class BetaMonMoveStrategy implements MoveStrategy {
    public int validateMoveWithIndexOfValidDice(Location from, Location to, Game game) {
        boolean isToOccupiedByOpponent =
                game.getColor(from) != game.getColor(to) &&
                        game.getColor(to) != Color.NONE;
        if(isToOccupiedByOpponent) {
            return -1;
        }
        boolean checkerIsNotOwnedByPlayerInTurn = game.getPlayerInTurn() != game.getColor(from);
        if(checkerIsNotOwnedByPlayerInTurn){
            return -1;
        }
        int signedDistanceOfMove = Location.distance(from, to);
        boolean isBackwards =
                game.getPlayerInTurn() == Color.BLACK && signedDistanceOfMove < 0 || game.getPlayerInTurn() == Color.RED && signedDistanceOfMove > 0;
        if(isBackwards) {
            return -1;
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
            return -1;
        }
        return indexOfValidDice;    }
}
