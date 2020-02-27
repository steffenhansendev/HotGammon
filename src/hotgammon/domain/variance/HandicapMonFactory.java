package hotgammon.domain.variance;

import hotgammon.domain.common.*;
import hotgammon.domain.variance.move.AlphaMonMoveStrategy;
import hotgammon.domain.variance.move.BetaMonMoveStrategy;
import hotgammon.domain.variance.move.HandicapMoveStrategy;
import hotgammon.domain.variance.roll.FixedRollStrategy;
import hotgammon.domain.variance.winning.RedWinsAfter6thTurnWinningStrategy;

public class HandicapMonFactory implements HotGammonFactory {
    public MoveStrategy createMoveStrategy() {
        return new HandicapMoveStrategy(new BetaMonMoveStrategy(), new AlphaMonMoveStrategy(), Color.RED);
    }

    public WinningStrategy createWinningStrategy() {
        return new RedWinsAfter6thTurnWinningStrategy();
    }

    public RollStrategy createRollStrategy() {
        return new FixedRollStrategy();
    }
}
