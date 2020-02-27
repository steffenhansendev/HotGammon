package hotgammon.domain.common;

public interface MoveStrategy {
    boolean resolveHit(Location from, Location to, Game game);

    int getIndexOfValidForValidMove(Location from, Location to, Game game);
}
