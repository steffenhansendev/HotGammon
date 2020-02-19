package hotgammon.domain.variants;

import hotgammon.domain.common.*;

import java.sql.Blob;
import java.util.Map;

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
        int numberOfCheckersAtTo = game.getCount(to);
        boolean isToBlot = numberOfCheckersAtTo < 2;
        if(isToBlot) {
            Map<Location, Integer> checkerCount = ((GameImpl) game).getCheckerCount();
            Map<Location, Color> checkerColor = ((GameImpl) game).getCheckerColor();
            Color colorOfBlot = game.getColor(to);
            Color colorOfHitter = colorOfBlot == Color.BLACK ? Color.RED : Color.BLACK;
            Location locationOfBlotsBar = colorOfBlot == Color.BLACK ? Location.B_BAR : Location.R_BAR;
            checkerCount.put(locationOfBlotsBar, checkerCount.get(locationOfBlotsBar) + 1);
            checkerColor.put(to, colorOfHitter);
            checkerCount.put(from, checkerCount.get(from) - 1 );
            return true;
        }
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
