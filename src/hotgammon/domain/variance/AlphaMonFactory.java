package hotgammon.domain.variance;

import hotgammon.domain.common.HotGammonFactory;
import hotgammon.domain.common.MoveStrategy;
import hotgammon.domain.common.RollStrategy;
import hotgammon.domain.common.WinningStrategy;
import hotgammon.domain.variance.move.AlphaMonMoveStrategy;
import hotgammon.domain.variance.roll.FixedRollStrategy;
import hotgammon.domain.variance.winning.RedWinsAfter6thTurnWinningStrategy;

public class AlphaMonFactory implements HotGammonFactory {
    public MoveStrategy createMoveStrategy() {
        return new AlphaMonMoveStrategy();
    }

    public WinningStrategy createWinningStrategy() {
        return new RedWinsAfter6thTurnWinningStrategy();
    }

    public RollStrategy createRollStrategy() {
        return new FixedRollStrategy();
    }
}
