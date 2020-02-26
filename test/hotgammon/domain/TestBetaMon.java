package hotgammon.domain;

import hotgammon.domain.common.Color;
import hotgammon.domain.common.Game;
import hotgammon.domain.common.GameImpl;
import hotgammon.domain.common.Location;
import hotgammon.domain.variance.BetaMonMoveStrategy;
import hotgammon.domain.variance.FirstOffBearerWinningStrategy;
import hotgammon.domain.variance.MoveByRollDiceStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class TestBetaMon {
    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new BetaMonMoveStrategy(), new MoveByRollDiceStrategy(), new FirstOffBearerWinningStrategy());
        game.newGame();
    }

    @Test
    public void noPlayerShouldBeInTurnInitially() {
        assertEquals("No player can be in turn before the game has begun.", Color.NONE, game.getPlayerInTurn());
    }

    @Test
    public void thereShouldBeNoWinnerInitially() {
        assertEquals("No player can have won before the game has begun", Color.NONE, game.winner());
    }

    @Test
    public void R1ShouldHave2BlackCheckersInitially() {
        assertEquals("Number of checkers on R1 must be 2 initially",2, game.getCount(Location.R1));
        assertEquals("Color of checker(s) on R1 must be black initially", Color.BLACK, game.getColor(Location.R1));
    }

    @Test
    public void B2ShouldHave0CheckersInitially() {
        assertEquals("Number of checkers on B2 must be 0 initially", 0, game.getCount(Location.B2));
        assertEquals("There can be no checkers on B2 initially", Color.NONE, game.getColor(Location.B2));
    }

    @Test
    public void R11ShouldHave0CheckersInitially() {
        assertEquals("Number of checkers on R11 must be 0 initially", 0, game.getCount(Location.R11));
        assertEquals("There can be no checkers on R11 initially", Color.NONE, game.getColor(Location.R11));
    }

    @Test
    public void B12ShouldHave5RedCheckersInitially() {
        assertEquals("Number of checkers on B12 must be 5 initially", 5, game.getCount(Location.B12));
        assertEquals("Color of checker(s) on B12 must be red initially", Color.RED, game.getColor(Location.B12));
    }

    @Test
    public void B8ShouldHave3BlackCheckersInitially() {
        assertEquals("Number of checkers on B8 must be 3 initially", 3, game.getCount(Location.B8));
        assertEquals("Color of checker(s) on B8 must be black initially", Color.BLACK, game.getColor(Location.B8));
    }

    @Test
    public void B6ShouldHave5BlackCheckersInitially() {
        assertEquals("Number of checkers on B6 must be 5 initially", 5, game.getCount(Location.B6));
        assertEquals("Color of checker(s) on B8 must be black initially", Color.BLACK, game.getColor(Location.B6));
    }

    @Test
    public void B1ShouldHave2RedCheckersInitially() {
        assertEquals("Number of checkers on B1 must be 2 initially", 2, game.getCount(Location.B1));
        assertEquals("Color of checker(s) on B1 must be red initially", Color.RED, game.getColor(Location.B1));
    }

    @Test
    public void R12ShouldHave5BlackCheckersInitially() {
        assertEquals("Number of checkers on R12 must be 5 initially", 5, game.getCount(Location.R12));
        assertEquals("Color of checker(s) on R12 must be black initially", Color.BLACK, game.getColor(Location.R12));
    }

    @Test
    public void R8ShouldHave3RedCheckersInitially() {
        assertEquals("Number of checkers on R8 must be 3 initially", 3, game.getCount(Location.R8));
        assertEquals("Color of checker(s) on R8 must be red initially", Color.RED, game.getColor(Location.R8));
    }

    @Test
    public void R6ShouldHave5RedCheckersInitially() {
        assertEquals("Number of checkers on R6 must be 5 initially", 5, game.getCount(Location.R6));
        assertEquals("Color of checker(s) on R6 must be red initially", Color.RED, game.getColor(Location.R6));
    }

    @Test
    public void blackPlayerMovingCheckerFromR1toR2ShouldBeValidIn1stTurn() {
        game.nextTurn();  //[1, 2] => Black
        assertEquals("Number of moves left must be 2 before black exhausts any moves", 2, game.getNumberOfMovesLeft());
        assertTrue("It must be valid for black to move a checker from R1 to R2 in first turn", game.move(Location.R1, Location.R2));
        assertEquals("Number of checkers on R2 must be 1 after black moves a checker here", 1, game.getCount(Location.R2));
        assertEquals("Color of checker(s) on R2 must be black after black moves a checker here", Color.BLACK, game.getColor(Location.R2));
        assertEquals("Number of checkers on R1 must be 1 after black moves a checker from here", 1, game.getCount(Location.R1));
        assertEquals("Color of checker(s) on R1 must be black after black moves a checker from here", Color.BLACK, game.getColor(Location.R1));
        assertEquals("Number of moves left must be 1 after black has exhausted 1 of 2 moves", 1, game.getNumberOfMovesLeft());
    }

    @Test
    public void movingBlackCheckerFromR1ToR6ShouldBeRejectedBecauseThisLocationIsRed() {
        game.nextTurn();  //[1, 2] => Black
        game.nextTurn();  //[3, 4] => Red
        game.nextTurn();  //[5, 6] => Black
        assertFalse("Moving black checker from R1 to R6 must be rejected because R6 is red", game.move(Location.R1, Location.R6));
    }

    @Test
    public void movingRedCheckerShouldBeRejectedWhenBlackIsInTurn() {
        game.nextTurn();  //[1, 2] => Black
        assertFalse("Moving red checker from B1 to B2 must be rejected because black is in turn", game.move(Location.B1, Location.B2));
    }

    @Test
    public void movingBlackCheckerShouldBeRejectedWhenRedIsInTurn() {
        game.nextTurn();  //[1, 2] => Black
        game.nextTurn();  //[3, 4] => Red
        assertFalse("Moving black checker from R1 to R4 must be rejected because red is in turn", game.move(Location.R1, Location.R2));
    }

    @Test
    public void movingBlackCheckerShouldBeRejectedWhenMovesHaveBeenExhausted() {
        game.nextTurn();  //[1, 2] => Black
        game.move(Location.R1, Location.R3);
        game.move(Location.R3, Location.R4);
        assertEquals("Number of moves left must be 0 after moving black checker(s) twice", 0, game.getNumberOfMovesLeft());
        assertFalse("Moving black checker from R4 to R5 must be rejected because this checker has already been moved twice", game.move(Location.R4, Location.R5));
    }

    @Test
    public void totalNumberOfCheckersShouldBe30() {
        game.nextTurn();  //[1, 2] => Black
        int totalNumberOfCheckers = 0;
        for(Location l : Location.values()) {
            totalNumberOfCheckers += game.getCount(l);
        }
        assertEquals("Total number of checkers must be 30 initially", 30, totalNumberOfCheckers);
        game.move(Location.R1, Location.R2);
        game.move(Location.R1, Location.R3);
        assertEquals("Total number of checkers must be 30 black moves has been exhausted", 30, totalNumberOfCheckers);
    }

    @Test
    public void movesLeftShouldBe0AfterMoving2CheckersUsingSmallestDiceValueFirstIn1stTurn() {
        game.nextTurn();  //[1, 2] => Black
        assertTrue("It must be valid for black to move a checker from R1 to R2 in first turn", game.move(Location.R1, Location.R2));
        assertTrue("It must be valid for black to move a checker from R1 to R3 in first turn", game.move(Location.R1, Location.R3));
        assertEquals("Number of moves left must be 0 when black has exhausted the moves", 0, game.getNumberOfMovesLeft());
    }

    @Test
    public void movesLeftShouldBe0AfterMoving2CheckersUsingLargestDiceValueFirstIn1stTurn() {
        game.nextTurn();  //[1, 2] => Black
        assertTrue("It must be valid for black to move a checker from R1 to R3 in first turn", game.move(Location.R1, Location.R3));
        assertTrue("It must be valid for black to move a checker from R1 to R2 in first turn", game.move(Location.R1, Location.R2));
        assertEquals("Number of moves left must be 0 when black has exhausted the moves", 0, game.getNumberOfMovesLeft());
    }

    @Test
    public void diceValuesLeftShouldBeSortedInGrowingOrderAndCoincideWithDiceThrownInEveryTurn() {
        //1st turn
        game.nextTurn();
        int dice0 = game.diceValuesLeft()[0];
        int dice1 = game.diceValuesLeft()[1];
        assertTrue("Dice with lower index must have higher value",dice0 > dice1);
        assertEquals("1st dice in diceValuesLeft must coincide with 2nd dice in diceThrown", dice0, game.diceThrown()[1]);
        assertEquals("2nd dice in diceValuesLeft must coincide with 1st dice in diceThrown", dice1, game.diceThrown()[0]);

        //2nd turn
        game.nextTurn();
        dice0 = game.diceValuesLeft()[0];
        dice1 = game.diceValuesLeft()[1];
        assertTrue("Dice with lower index must have higher value",dice0 > dice1);
        assertEquals("1st dice in diceValuesLeft must coincide with 2nd dice in diceThrown", dice0, game.diceThrown()[1]);
        assertEquals("2nd dice in diceValuesLeft must coincide with 1st dice in diceThrown", dice1, game.diceThrown()[0]);

        //3rd turn
        game.nextTurn();
        dice0 = game.diceValuesLeft()[0];
        dice1 = game.diceValuesLeft()[1];
        assertTrue("Dice with lower index must have higher value",dice0 > dice1);
        assertEquals("1st dice in diceValuesLeft must coincide with 2nd dice in diceThrown", dice0, game.diceThrown()[1]);
        assertEquals("2nd dice in diceValuesLeft must coincide with 1st dice in diceThrown", dice1, game.diceThrown()[0]);

        //4th turn
        game.nextTurn();
        dice0 = game.diceValuesLeft()[0];
        dice1 = game.diceValuesLeft()[1];
        assertTrue("Dice with lower index must have higher value",dice0 > dice1);
        assertEquals("1st dice in diceValuesLeft must coincide with 2nd dice in diceThrown", dice0, game.diceThrown()[1]);
        assertEquals("2nd dice in diceValuesLeft must coincide with 1st dice in diceThrown", dice1, game.diceThrown()[0]);

        //5th turn
        game.nextTurn();
        dice0 = game.diceValuesLeft()[0];
        dice1 = game.diceValuesLeft()[1];
        assertTrue("Dice with lower index must have higher value",dice0 > dice1);
        assertEquals("1st dice in diceValuesLeft must coincide with 2nd dice in diceThrown", dice0, game.diceThrown()[1]);
        assertEquals("2nd dice in diceValuesLeft must coincide with 1st dice in diceThrown", dice1, game.diceThrown()[0]);

        //6th turn
        game.nextTurn();
        dice0 = game.diceValuesLeft()[0];
        dice1 = game.diceValuesLeft()[1];
        assertTrue("Dice with lower index must have higher value",dice0 > dice1);
        assertEquals("1st dice in diceValuesLeft must coincide with 2nd dice in diceThrown", dice0, game.diceThrown()[1]);
        assertEquals("2nd dice in diceValuesLeft must coincide with 1st dice in diceThrown", dice1, game.diceThrown()[0]);
    }

    @Test
    public void movingBlackCheckerFromR12toR11ShouldBeRejected() {
        game.nextTurn();  //[1, 2] => Black
        assertFalse("Moving black checker from R12 to R11 must be rejected because the destination is further away from black bear off", game.move(Location.R12, Location.R11));
    }

    @Test
    public void movingRedCheckerFromB12ToB9ShouldBeRejected() {
        game.nextTurn();  //[1, 2] => Black
        game.nextTurn();  //[3, 4] => Red
        assertFalse("Moving red checker from B12 to R9 must be rejected because the destination is further away from red bear off", game.move(Location.B12, Location.B9));
    }

    @Test
    public void movingBlackCheckerFromR1toR1ShouldBeRejected() {
        game.nextTurn();  //[1, 2] => Black
        assertFalse("Moving black checker from R1 to R1 must be rejected because destination is the same as origin",
                game.move(Location.R1, Location.R1));
    }

    @Test
    public void movingRedCheckerFromB1toB1ShouldBeRejected() {
        game.nextTurn();  //[1, 2] => Black
        game.nextTurn();  //[3, 4] => Red
        assertFalse("Moving black checker from B1 to B1 must be rejected because destination is the same as origin",
                game.move(Location.B1, Location.B1));
    }

    @Test
    public void movingBlackCheckerFromR1toR7ShouldBeRejected() {
        game.nextTurn();  //[1, 2] => Black
        assertFalse("Moving black checker from R1 to R7 must be rejected because distance travelled is too far", game.move(Location.R1, Location.R7));
    }

    @Test
    public void movingRedCheckerToBlotBlackCheckerShouldBarBlackChecker() {
        game.nextTurn();    //[1, 2] => Black
        assertTrue(game.move(Location.R1, Location.R2));
        assertTrue(game.move(Location.R1, Location.R3));
        game.nextTurn();    //[3, 4] => Red
        assertTrue("Barring black blot at R3 with red checker from R6 must be allowed", game.move(Location.R6, Location.R3));
        assertEquals("Number of checkers at B_BAR  must be 1 after red checker has barred black", 1, game.getCount(Location.B_BAR));
        assertEquals("Color of checker(s) at R6  must be red after red checker has barred black checker here", Color.RED, game.getColor(Location.R3));
        assertEquals("Number of checkers at R6  must be 1 after red checker has barred black checker here", 1, game.getCount(Location.R3));
    }

    @Test
    public void movingBlackCheckerToBlotRedCheckerShouldBarRedChecker() {
        game.nextTurn();    //[1, 2] => Black
        assertTrue(game.move(Location.R1, Location.R2));
        assertTrue(game.move(Location.R1, Location.R3));
        game.nextTurn();    //[3, 4] => Red
        assertTrue(game.move(Location.B1, Location.B4));
        assertTrue(game.move(Location.B12, Location.R9));
        game.nextTurn();    //[5, 6] => Black
        assertTrue("Barring red blot at B1 with black checker from B6 must be allowed", game.move(Location.B6, Location.B1));
        assertEquals("Number of checkers at R_BAR  must be 1 after black checker has barred red", 1, game.getCount(Location.R_BAR));
        assertEquals("Color of checker(s) at B1  must be black after black checker has barred red checker here", Color.BLACK, game.getColor(Location.B1));
        assertEquals("Number of checkers at B1  must be 1 after black checker has barred red checker here", 1, game.getCount(Location.B1));
    }

    @Test
    public void movingBlackCheckerFromElsewhereShouldBeRejectedWhenBlackCheckersAreBarred() {
        game.nextTurn();    //[1, 2] => Black
        assertTrue(game.move(Location.R1, Location.R2));
        assertTrue(game.move(Location.R1, Location.R3));
        game.nextTurn();    //[3, 4] => Red
        assertTrue(game.move(Location.R6, Location.R3));
        assertTrue(game.move(Location.B1, Location.B5));
        game.nextTurn();    //[5, 6] => Black
        assertFalse("Moving black checker from elsewhere must be rejected because black checkers are barred", game.move(Location.R12, Location.B8));
    }

    @Test
    public void movingRedCheckerFromElsewhereShouldBeRejectedWhenRedCheckersAreBarred() {
        game.nextTurn();    //[1, 2] => Black
        assertTrue(game.move(Location.R1, Location.R2));
        assertTrue(game.move(Location.R1, Location.R3));
        game.nextTurn();    //[3, 4] => Red
        assertTrue(game.move(Location.B1, Location.B4));
        assertTrue(game.move(Location.B12, Location.R9));
        game.nextTurn();    //[5, 6] => Black
        assertTrue(game.move(Location.B6, Location.B1));
        assertTrue(game.move(Location.R12, Location.B7));
        game.nextTurn();    //[1, 2] => Red
        assertFalse("Moving red checker from elsewhere must be rejected because red checkers are barred", game.move(Location.R8, Location.B9));
    }

    @Test
    public void movingBarredBlackCheckerToRedInnerTableShouldBeAllowedWithAdequateRoll() {
        game.nextTurn();    //[1, 2] => Black
        assertTrue(game.move(Location.R1, Location.R2));
        assertTrue(game.move(Location.R1, Location.R3));
        game.nextTurn();    //[3, 4] => Red
        assertTrue(game.move(Location.R6, Location.R3));
        assertTrue(game.move(Location.B1, Location.B5));
        game.nextTurn();    //[5, 6] => Black
        assertTrue ("Moving black checker from bar to red's inner table must be allowed", game.move(Location.B_BAR, Location.R5));
    }

    @Test
    public void movingBarredRedCheckerToBlackInnerTableShouldBeAllowedWithAdequateRoll() {
        game.nextTurn();    //[1, 2] => Black
        assertTrue(game.move(Location.R1, Location.R2));
        assertTrue(game.move(Location.R1, Location.R3));
        game.nextTurn();    //[3, 4] => Red
        assertTrue(game.move(Location.B1, Location.B4));
        assertTrue(game.move(Location.B12, Location.R9));
        game.nextTurn();    //[5, 6] => Black
        assertTrue(game.move(Location.B6, Location.B1));
        assertTrue(game.move(Location.R12, Location.B7));
        game.nextTurn();    //[1, 2] => Red
        assertTrue("Moving red checker from bar to black's inner table must be allowed", game.move(Location.R_BAR, Location.B2));
    }

    @Test
    public void blackCanNotMoveCheckerToBlackBar() {
        game.nextTurn();  //[1, 2] => Black
        assertFalse("Manually moving checker to bar must be rejected", game.move(Location.R1, Location.B_BAR));
    }

    @Test
    public void blackCanNotMoveCheckerToRedBar() {
        game.nextTurn();    //[1, 2] => Black
        game.nextTurn();    //[3, 4] => Red
        game.nextTurn();    //[5, 6] => Black
        assertFalse("Manually moving checker to bar must be rejected", game.move(Location.B6, Location.R_BAR));
    }

    @Test
    public void redCanNotMoveCheckerToRedBar() {
        game.nextTurn();    //[1, 2] => Black
        game.nextTurn();    //[3, 4] => Red
        game.nextTurn();    //[5, 6] => Black
        game.nextTurn();    //[1, 2] => Red
        assertFalse("Manually moving checker to bar must be rejected", game.move(Location.B1, Location.R_BAR));
    }

    @Test
    public void redCanNotMoveCheckerToBlackBar() {
        game.nextTurn();    //[1, 2] => Black
        game.nextTurn();    //[3, 4] => Red
        game.nextTurn();    //[5, 6] => Black
        game.nextTurn();    //[1, 2] => Red
        game.nextTurn();    //[3, 4] => Black
        game.nextTurn();    //[5, 6] => Red
        assertFalse("Manually moving checker to bar must be rejected", game.move(Location.R6, Location.B_BAR));
    }

    @Test
    public void blackCanNotMoveCheckerToBlackBearOff() {
        game.nextTurn();    //[1, 2] => Black
        game.nextTurn();    //[3, 4] => Red
        game.nextTurn();    //[5, 6] => Black
        assertFalse("Bearing off black checker must be rejected because inner table has not been filled", game.move(Location.B6, Location.B_BEAR_OFF));
    }

    @Test
    public void blackCanNotMoveCheckerToRedBearOff() {
        game.nextTurn();    //[1, 2] => Black
        assertTrue(game.move(Location.B6, Location.B5));
        game.nextTurn();    //[3, 4] => Red
        game.nextTurn();    //[5, 6] => Black
        assertFalse("Bearing off black checker must be rejected because bear off is red", game.move(Location.B5, Location.B_BEAR_OFF));
    }

    @Test
    public void redCanNotMoveCheckerToRedBearOff() {
        game.nextTurn();    //[1, 2] => Black
        game.nextTurn();    //[3, 4] => Red
        assertFalse("Bearing off red checker must be rejected because inner table has not been filled", game.move(Location.B1, Location.R_BEAR_OFF));
    }

    @Test
    public void redCanNotMoveCheckerToBlackBearOff() {
        game.nextTurn();    //[1, 2] => Black
        game.nextTurn();    //[3, 4] => Red
        game.nextTurn();    //[5, 6] => Black
        game.nextTurn();    //[1, 2] => Red
        assertFalse("Bearing off black checker must be rejected because inner table has not been filled", game.move(Location.B1, Location.B_BEAR_OFF));
    }
}