package hotgammon.domain;

import hotgammon.domain.common.Color;
import hotgammon.domain.common.Game;
import hotgammon.domain.common.GameImpl;
import hotgammon.domain.common.Location;
import hotgammon.domain.variance.FirstOffBearerWinningStrategy;
import hotgammon.domain.variance.GammaMonMoveStrategy;
import hotgammon.domain.variance.MoveFreelyDiceStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestGammaMon {

    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new GammaMonMoveStrategy(), new MoveFreelyDiceStrategy(), new FirstOffBearerWinningStrategy());
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
        game.nextTurn();    //[3, 4] => Red
        assertTrue("Bearing off black checker must be allowed", game.move(Location.R6, Location.R_BEAR_OFF));
    }

    @Test
        public void bearingOffAllBlackCheckersShouldMakeBlackWin() {
        game.nextTurn();    //[1, 2] => Black
        assertTrue(game.move(Location.B6, Location.B_BEAR_OFF));
        assertTrue(game.move(Location.B6, Location.B_BEAR_OFF));
        game.nextTurn();    //[3, 4] => Red
        game.nextTurn();    //[5, 6] => Black
        assertTrue(game.move(Location.B6, Location.B_BEAR_OFF));
        assertTrue(game.move(Location.B6, Location.B_BEAR_OFF));
        game.nextTurn();    //[1, 2] => Red
        game.nextTurn();    //[3, 4] => Black
        assertTrue(game.move(Location.B6, Location.B_BEAR_OFF));
        assertTrue(game.move(Location.B8, Location.B_BEAR_OFF));
        game.nextTurn();    //[5, 6] => Red
        game.nextTurn();    //[1, 2] => Black
        assertTrue(game.move(Location.B8, Location.B_BEAR_OFF));
        assertTrue(game.move(Location.B8, Location.B_BEAR_OFF));
        game.nextTurn();    //[3, 4] => Red
        game.nextTurn();    //[5, 6] => Black
        assertTrue(game.move(Location.R12, Location.B_BEAR_OFF));
        assertTrue(game.move(Location.R12, Location.B_BEAR_OFF));
        game.nextTurn();    //[1, 2] => Red
        game.nextTurn();    //[3, 4] => Black
        assertTrue(game.move(Location.R12, Location.B_BEAR_OFF));
        assertTrue(game.move(Location.R12, Location.B_BEAR_OFF));
        game.nextTurn();    //[5, 6] => Red
        game.nextTurn();    //[1, 2] => Black
        assertTrue(game.move(Location.R12, Location.B_BEAR_OFF));
        assertTrue(game.move(Location.R1, Location.B_BEAR_OFF));
        game.nextTurn();    //[3, 4] => Red
        game.nextTurn();    //[5, 6] => Black
        assertTrue(game.move(Location.R1, Location.B_BEAR_OFF));
        assertEquals("Black must be victor when all black checkers has been born off", game.winner(), Color.BLACK);
    }

    @Test
        public void bearingOffAllRedCheckersShouldMakeRedWin() {
        game.nextTurn();    //[1, 2] => Black
        game.nextTurn();    //[3, 4] => Red
        assertTrue(game.move(Location.R6, Location.R_BEAR_OFF));
        assertTrue(game.move(Location.R6, Location.R_BEAR_OFF));
        game.nextTurn();    //[5, 6] => Black
        game.nextTurn();    //[1, 2] => Red
        assertTrue(game.move(Location.R6, Location.R_BEAR_OFF));
        assertTrue(game.move(Location.R6, Location.R_BEAR_OFF));
        game.nextTurn();    //[3, 4] => Black
        game.nextTurn();    //[5, 6] => Red
        assertTrue(game.move(Location.R6, Location.R_BEAR_OFF));
        assertTrue(game.move(Location.R8, Location.R_BEAR_OFF));
        game.nextTurn();    //[1, 2] => Black
        game.nextTurn();    //[3, 4] => Red
        assertTrue(game.move(Location.R8, Location.R_BEAR_OFF));
        assertTrue(game.move(Location.R8, Location.R_BEAR_OFF));
        game.nextTurn();    //[5, 6] => Black
        game.nextTurn();    //[1, 2] => Red
        assertTrue(game.move(Location.B12, Location.R_BEAR_OFF));
        assertTrue(game.move(Location.B12, Location.R_BEAR_OFF));
        game.nextTurn();    //[3, 4] => Black
        game.nextTurn();    //[5, 6] => Red
        assertTrue(game.move(Location.B12, Location.R_BEAR_OFF));
        assertTrue(game.move(Location.B12, Location.R_BEAR_OFF));
        game.nextTurn();    //[1, 2] => Black
        game.nextTurn();    //[3, 4] => Red
        assertTrue(game.move(Location.B12, Location.R_BEAR_OFF));
        assertTrue(game.move(Location.B1, Location.R_BEAR_OFF));
        game.nextTurn();    //[5, 6] => Black
        game.nextTurn();    //[1, 2] => Red
        assertTrue(game.move(Location.B1, Location.R_BEAR_OFF));
        assertEquals("Red must be victor when all red checkers has been born off", game.winner(), Color.RED);
    }
}
