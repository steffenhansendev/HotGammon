package hotgammon.domain.common;

public interface MoveStrategy {
    public boolean isMoveValid(Location from, Location to, Game game);
    public boolean resolveHit(Location from, Location to, Game game);
    public void updateDice(Location from, Location to, Game game);
}
