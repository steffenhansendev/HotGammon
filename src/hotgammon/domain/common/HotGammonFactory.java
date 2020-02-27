package hotgammon.domain.common;

public interface HotGammonFactory {
    MoveStrategy createMoveStrategy();

    WinningStrategy createWinningStrategy();

    RollStrategy createRollStrategy();
}
