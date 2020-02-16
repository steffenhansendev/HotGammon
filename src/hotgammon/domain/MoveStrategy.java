package hotgammon.domain;

public interface MoveStrategy {
    public boolean validateMoveWithIndexOfValidDice(Location from, Location to, Game game);
}
