package hotgammon.domain.common;

public interface HotGammonFactory {
    public MoveStrategy createMoveStrategy();
    public WinningStrategy createWinningStrategy();
    public RollStrategy createRollStrategy();
}
