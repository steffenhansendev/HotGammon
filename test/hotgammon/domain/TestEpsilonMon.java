package hotgammon.domain;

import hotgammon.domain.common.Game;
import hotgammon.domain.common.GameImpl;
import hotgammon.domain.surrogates.TestEpsilonMonFactory;
import hotgammon.domain.surrogates.TestStubRollStrategy;
import hotgammon.domain.variance.move.AlphaMonMoveStrategy;
import hotgammon.domain.variance.winning.RedWinsAfter6thTurnWinningStrategy;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestEpsilonMon {

    private Game game;

    @Before
    public void setUp() {
    }

    //For manual testing of RandomRollStrategy
    /*
    @Test
    public void rollShouldCorrespondWithDiceValuesLeft() {
        game = new GameImpl(new AlphaMonMoveStrategy(), new MoveByRollDiceStrategy(), new RedWinsAfter6thTurnWinningStrategy(), new RandomRollStrategy());
        game.newGame();
        game.nextTurn();
        int[] diceValuesLeft = game.diceValuesLeft();
        System.out.println("1st die is: " +  diceValuesLeft[0]);
        System.out.println("2nd die is: " +  diceValuesLeft[1]);
    }
    */

    @Test
    public void rolling1and6ShouldYield6and1() {
        game = new GameImpl(new AlphaMonMoveStrategy(), new RedWinsAfter6thTurnWinningStrategy(), new TestStubRollStrategy(1, 6), new TestEpsilonMonFactory(1, 6));
        game.newGame();
        game.nextTurn();
        int[] diceValuesLeft = game.diceValuesLeft();
        assertEquals("1st die must be 6 when [1, 6] has been rolled", 6, diceValuesLeft[0]);
        assertEquals("2nd die must be 1 when [1, 6] has been rolled", 1, diceValuesLeft[1]);
    }
}
