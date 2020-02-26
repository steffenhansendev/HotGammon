package hotgammon.domain;

import hotgammon.domain.common.Color;
import hotgammon.domain.common.Game;
import hotgammon.domain.common.GameImpl;
import hotgammon.domain.common.Location;
import hotgammon.domain.variance.GammaMonMoveStrategy;
import hotgammon.domain.variance.MoveFreelyDiceStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestGammaMon {

    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new GammaMonMoveStrategy(), new MoveFreelyDiceStrategy());
        game.newGame();
    }

    @Test
    public void bearingOffBlackCheckerShouldBeAllowed() {
        game.nextTurn();    //[1, 2] => Black
        assertTrue("Bearing off black checker must be allowed", game.move(Location.B6, Location.B_BEAR_OFF));
    }

    @Test
    public void bearingOffRedCheckerShouldBeAllowed() {
        game.nextTurn();    //[1, 2] => Black
        game.nextTurn();    //[1, 2] => Red
        assertTrue("Bearing off black checker must be allowed", game.move(Location.R6, Location.R_BEAR_OFF));
    }

    @Test
    public void bearingOffAllBlackCheckersShouldMakeBlackWinner() {
        assertEquals("", game.winner(), Color.BLACK);
    }
}
