package hotgammon.domain;

import hotgammon.domain.common.Game;
import hotgammon.domain.common.GameImpl;
import hotgammon.domain.common.Location;
import hotgammon.domain.variants.AlphaMonMoveStrategy;
import hotgammon.domain.variants.GammaMonMoveStrategy;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class TestGammaMon {

    private Game game = new GameImpl(new GammaMonMoveStrategy());

    @Test
    public void bearingOffBlackCheckerShouldBeAllowed() {
        game.newGame();
        game.nextTurn();    //[1, 2] => Black
        assertTrue("Bearing off black checker must be allowed", game.move(Location.B6, Location.B_BEAR_OFF));
    }
}
