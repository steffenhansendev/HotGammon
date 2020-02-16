package hotgammon.domain;

import java.util.Map;

public class AlphaMonMoveStrategy implements MoveStrategy {
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
        return 0;
    }
}