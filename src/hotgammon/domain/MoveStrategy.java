package hotgammon.domain;

public interface MoveStrategy {
    public boolean validateMoveAndUpdateDiceValuesLeft(Location from, Location to, Game game);
}
