package hotgammon.domain.variance;

import hotgammon.domain.common.*;

import java.util.Map;

public class BetaMonMoveStrategy implements MoveStrategy {

    public int getIndexOfValidForValidMove(Location from, Location to, Game game) {

        Color colorOfPlayerInTurn = game.getPlayerInTurn();

        Location barOfPlayerInTurn = colorOfPlayerInTurn == Color.BLACK ? Location.B_BAR : Location.R_BAR;

        boolean areCheckersBarred = game.getCount(barOfPlayerInTurn) > 0;

        if(areCheckersBarred) {
            boolean isMoveFromBar = from == barOfPlayerInTurn;
            if(!isMoveFromBar){
                return -1;
            }
        }
        Location barOfOpponent = barOfPlayerInTurn == Location.B_BAR ? Location.R_BAR : Location.B_BAR;

        boolean isMoveToOpponentBar = to == barOfOpponent;

        if(isMoveToOpponentBar) {
            return -1;
        }

        boolean isMoveBearOff = to == Location.B_BEAR_OFF || to == Location.R_BEAR_OFF;


        boolean isMovePrematureBearOff = isMoveBearOff &&
                (colorOfPlayerInTurn == Color.BLACK && !((GameImpl) game).getHasBlackInnerTableBeenFilled() ||
                        colorOfPlayerInTurn == Color.RED && !((GameImpl) game).getHasRedInnerTableBeenFilled());
        if(isMovePrematureBearOff) {
            return -1;
        }

        int signedDistanceOfMove = Location.distance(from, to);

        boolean isBackwards =
                colorOfPlayerInTurn == Color.BLACK && signedDistanceOfMove < 0 || colorOfPlayerInTurn == Color.RED && signedDistanceOfMove > 0;
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
        return indexOfValidDice;
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
}
