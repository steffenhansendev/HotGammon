package hotgammon.domain.variance.winning;

import hotgammon.domain.common.Color;
import hotgammon.domain.common.Game;
import hotgammon.domain.common.Location;
import hotgammon.domain.common.WinningStrategy;

public class FirstOffBearerWinningStrategy implements WinningStrategy {
    public Color getWinner(Game game) {
        Color winner = Color.NONE;
        winner = game.getCount(Location.B_BEAR_OFF) > 14 ? Color.BLACK : winner;
        winner = game.getCount(Location.R_BEAR_OFF) > 14 ? Color.RED : winner;
        return winner;
    }
}
