package hotgammon.domain.common;

public interface WinningStrategy {
    public Color getWinner(Game game);
}
