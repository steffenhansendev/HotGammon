package hotgammon.domain;

public interface MoveStrategy {
    public int validateMoveWithIndexOfValidDice(Location from, Location to, Game game);
}
