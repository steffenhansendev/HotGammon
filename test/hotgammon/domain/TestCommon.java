package hotgammon.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class TestCommon {
    private Game game;

    @Before
    public void setup() {
        game = new GameImpl(new AlphaMonMoveStrategy());
    }

    @Test
    public void noPlayerShouldBeInTurnInitially() {
        game.newGame();
        assertEquals("No player can be in turn before the game has begun.", Color.NONE, game.getPlayerInTurn());
    }

    @Test
    public void thereShouldBeNoWinnerInitially() {
        game.newGame();
        assertEquals("No player can have won before the game has begun", Color.NONE, game.winner());
    }

    @Test
    public void R1ShouldHave2BlackCheckersInitially() {
        game.newGame();
        assertEquals("Number of checkers on R1 must be 2 initially",2, game.getCount(Location.R1));
        assertEquals("Color of checker(s) on R1 must be black initially", Color.BLACK, game.getColor(Location.R1));
    }

    @Test
    public void B2ShouldHave0CheckersInitially() {
        game.newGame();
        assertEquals("Number of checkers on B2 must be 0 initially", 0, game.getCount(Location.B2));
        assertEquals("There can be no checkers on B2 initially", Color.NONE, game.getColor(Location.B2));
    }

    @Test
    public void R11ShouldHave0CheckersInitially() {
        game.newGame();
        assertEquals("Number of checkers on R11 must be 0 initially", 0, game.getCount(Location.R11));
        assertEquals("There can be no checkers on R11 initially", Color.NONE, game.getColor(Location.R11));
    }

    @Test
    public void B12ShouldHave5RedCheckersInitially() {
        game.newGame();
        assertEquals("Number of checkers on B12 must be 5 initially", 5, game.getCount(Location.B12));
        assertEquals("Color of checker(s) on B12 must be red initially", Color.RED, game.getColor(Location.B12));
    }

    @Test
    public void B8ShouldHave3BlackCheckersInitially() {
        game.newGame();
        assertEquals("Number of checkers on B8 must be 3 initially", 3, game.getCount(Location.B8));
        assertEquals("Color of checker(s) on B8 must be black initially", Color.BLACK, game.getColor(Location.B8));
    }

    @Test
    public void B6ShouldHave5BlackCheckersInitially() {
        game.newGame();
        assertEquals("Number of checkers on B6 must be 5 initially", 5, game.getCount(Location.B6));
        assertEquals("Color of checker(s) on B8 must be black initially", Color.BLACK, game.getColor(Location.B6));
    }

    @Test
    public void B1ShouldHave2RedCheckersInitially() {
        game.newGame();
        assertEquals("Number of checkers on B1 must be 2 initially", 2, game.getCount(Location.B1));
        assertEquals("Color of checker(s) on B1 must be red initially", Color.RED, game.getColor(Location.B1));
    }

    @Test
    public void R12ShouldHave5BlackCheckersInitially() {
        game.newGame();
        assertEquals("Number of checkers on R12 must be 5 initially", 5, game.getCount(Location.R12));
        assertEquals("Color of checker(s) on R12 must be black initially", Color.BLACK, game.getColor(Location.R12));
    }

    @Test
    public void R8ShouldHave3RedCheckersInitially() {
        game.newGame();
        assertEquals("Number of checkers on R8 must be 3 initially", 3, game.getCount(Location.R8));
        assertEquals("Color of checker(s) on R8 must be red initially", Color.RED, game.getColor(Location.R8));
    }

    @Test
    public void R6ShouldHave5RedCheckersInitially() {
        game.newGame();
        assertEquals("Number of checkers on R6 must be 5 initially", 5, game.getCount(Location.R6));
        assertEquals("Color of checker(s) on R6 must be red initially", Color.RED, game.getColor(Location.R6));
    }

    @Test
    public void blackPlayerMovingCheckerFromR1toR2ShouldBeValidIn1stTurn() {
        game.newGame();
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
        game.newGame();
        game.nextTurn();  //[1, 2] & Black
        game.nextTurn();  //[3, 4] & Red
        game.nextTurn();  //[5, 6] & Black
        assertFalse("Moving black checker from R1 to R6 must be rejected because R6 is red", game.move(Location.R1, Location.R6));
    }

    @Test
    public void movingRedCheckerShouldBeRejectedWhenBlackIsInTurn() {
        game.newGame();
        game.nextTurn();
        assertFalse("Moving red checker from B1 to B2 must be rejected because black is in turn", game.move(Location.B1, Location.B2));
    }

    @Test
    public void movingBlackCheckerShouldBeRejectedWhenRedIsInTurn() {
        game.newGame();
        game.nextTurn();  //[1, 2] & Black
        game.nextTurn();  //[3, 4] & Red
        assertFalse("Moving black checker from R1 to R4 must be rejected because red is in turn", game.move(Location.R1, Location.R2));
    }

    @Test
    public void movingBlackCheckerShouldBeRejectedWhenMovesHaveBeenExhausted() {
        game.newGame();
        game.nextTurn();  //[1, 2] & Black
        game.move(Location.R1, Location.R3);
        game.move(Location.R3, Location.R4);
        assertEquals("Number of moves left must be 0 after moving black checker(s) twice", 0, game.getNumberOfMovesLeft());
        assertFalse("Moving black checker from R4 to R5 must be rejected because this checker has already been moved twice", game.move(Location.R4, Location.R5));
    }

    @Test
    public void totalNumberOfCheckersShouldBe30() {
        game.newGame();
        game.nextTurn();  //[1, 2] & Black
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
        game.newGame();
        game.nextTurn();
        assertTrue("It must be valid for black to move a checker from R1 to R2 in first turn", game.move(Location.R1, Location.R2));
        assertTrue("It must be valid for black to move a checker from R1 to R3 in first turn", game.move(Location.R1, Location.R3));
        assertEquals("Number of moves left must be 0 when black has exhausted the moves", 0, game.getNumberOfMovesLeft());
    }

    @Test
    public void movesLeftShouldBe0AfterMoving2CheckersUsingLargestDiceValueFirstIn1stTurn() {
        game.newGame();
        game.nextTurn();
        assertTrue("It must be valid for black to move a checker from R1 to R3 in first turn", game.move(Location.R1, Location.R3));
        assertTrue("It must be valid for black to move a checker from R1 to R2 in first turn", game.move(Location.R1, Location.R2));
        assertEquals("Number of moves left must be 0 when black has exhausted the moves", 0, game.getNumberOfMovesLeft());
    }
}