package hotgammon.domain.common;

public interface WinningStrategy {
    Color getWinner(Game game);
}
