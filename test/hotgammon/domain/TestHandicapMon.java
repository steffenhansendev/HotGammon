package hotgammon.domain;

import hotgammon.domain.common.Color;
import hotgammon.domain.common.Game;
import hotgammon.domain.common.GameImpl;
import hotgammon.domain.common.Location;
import hotgammon.domain.variance.HandicapMonFactory;
import hotgammon.domain.variance.move.AlphaMonMoveStrategy;
import hotgammon.domain.variance.move.BetaMonMoveStrategy;
import hotgammon.domain.variance.move.HandicapMoveStrategy;
import hotgammon.domain.variance.roll.FixedRollStrategy;
import hotgammon.domain.variance.winning.RedWinsAfter6thTurnWinningStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestHandicapMon {

    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new HandicapMonFactory());
        game.newGame();
    }

    @Test
    public void alphaMonRulesShouldApplyToBlack() {
        game.nextTurn();    //[1, 2] => Black
        assertTrue("Moving black checker from R1 to R5 must be allowed under AlphaMon rules", game.move(Location.R1, Location.R5));
    }

    @Test
    public void BetaMonRulesShouldApplyToBlack() {
        game.nextTurn();    //[1, 2] => Black
        game.nextTurn();    //[3, 4] => Red
        assertFalse("Moving red checker from R8 to R2 must be rejected under BetaMon rules", game.move(Location.R8, Location.R2));
        assertTrue("Moving red checker from R8 to R5 must be allowed under BetaMon rules", game.move(Location.R8, Location.R5));
        assertEquals("Dice value left must be 4 after 3 has been used", 4, game.diceValuesLeft()[0]);
    }
}