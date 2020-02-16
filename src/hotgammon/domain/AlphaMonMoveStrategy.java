package hotgammon.domain;

public class AlphaMonMoveStrategy implements MoveStrategy {
    public boolean validateMoveWithIndexOfValidDice(Location from, Location to, Game game) {
        switch(game.getNumberOfMovesLeft()) {
            case 0:
                return false;
            case 1:
                game.setDiceValuesLeft(new int[0]);
                break;
            case 2:
                game.setDiceValuesLeft(new int[1]);
        }
        return true;
    }
}