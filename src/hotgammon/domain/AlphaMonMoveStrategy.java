package hotgammon.domain;

public class AlphaMonMoveStrategy implements MoveStrategy {
    public int validateMoveWithIndexOfValidDice(Location from, Location to, Game game) {
        if(game.diceValuesLeft().length == 0) {
            return -1;
        }
        return 0;
    }
}