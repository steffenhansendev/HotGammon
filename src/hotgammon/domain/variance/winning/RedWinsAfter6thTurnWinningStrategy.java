package hotgammon.domain.variance.winning;

import hotgammon.domain.common.Color;
import hotgammon.domain.common.Game;
import hotgammon.domain.common.GameImpl;
import hotgammon.domain.common.WinningStrategy;

public class RedWinsAfter6thTurnWinningStrategy implements WinningStrategy {

    public Color getWinner(Game game) {
        if (((GameImpl) game).getTurnCount() > 6) {
            return Color.RED;
        }
        return Color.NONE;
    }
}
