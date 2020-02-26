package hotgammon.domain.common;

public interface MoveStrategy {
    public boolean resolveHit(Location from, Location to, Game game);
    public int getIndexOfValidForValidMove(Location from, Location to, Game game);
}
