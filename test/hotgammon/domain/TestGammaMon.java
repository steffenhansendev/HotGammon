package hotgammon.domain;

import hotgammon.domain.common.Game;
import hotgammon.domain.common.GameImpl;
import hotgammon.domain.common.Location;
import hotgammon.domain.variance.GammaMonMoveStrategy;
import hotgammon.domain.variance.MoveFreelyDiceStrategy;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestGammaMon {

    private Game game = new GameImpl(new GammaMonMoveStrategy(), new MoveFreelyDiceStrategy());

    @Test
    public void bearingOffBlackCheckerShouldBeAllowed() {
        game.newGame();
        game.nextTurn();    //[1, 2] => Black
        assertTrue("Bearing off black checker must be allowed", game.move(Location.B6, Location.B_BEAR_OFF));
    }

    @Test
    public void bearingOffRedCheckerShouldBeAllowed() {
        game.newGame();
        game.nextTurn();    //[1, 2] => Black
        game.nextTurn();    //[1, 2] => Red
        assertTrue("Bearing off black checker must be allowed", game.move(Location.R6, Location.R_BEAR_OFF));
    }
}
