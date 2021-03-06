package hotgammon.domain;

import hotgammon.domain.common.Color;
import hotgammon.domain.common.Game;
import hotgammon.domain.common.GameImpl;
import hotgammon.domain.common.Location;
import hotgammon.domain.variance.AlphaMonFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestAlphamon {
  private Game game;
  
  @Before
  public void setup() {
    game = new GameImpl(new AlphaMonFactory());
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
  public void _1stRollShouldYield_1_2() {
    game.nextTurn();  //[1, 2] => Black
    assertArrayEquals("First roll must yield [1,2].", new int[]{1, 2}, game.diceThrown());
  }

  @Test
  public void _2ndRollShouldYield_3_4() {
    game.nextTurn();  //[1, 2] => Black
    game.nextTurn();  //[3, 4] => Red
    assertArrayEquals("Second roll must yield [3,4].", new int[]{3, 4}, game.diceThrown());
  }

  @Test
  public void _3rdRollShouldYield_5_6() {
    game.nextTurn();  //[1, 2] => Black
    game.nextTurn();  //[3, 4] => Red
    game.nextTurn();  //[5, 6] => Black
    assertArrayEquals("Third roll must yield [5,6].", new int[]{5, 6}, game.diceThrown());
  }

  @Test
  public void _4thRollShouldYield_1_2() {
    game.nextTurn();  //[1, 2] => Black
    game.nextTurn();  //[3, 4] => Red
    game.nextTurn();  //[5, 6] => Black
    game.nextTurn();  //[1, 2] => Red
    assertArrayEquals("Fourth roll must yield [1,2].", new int[]{1, 2}, game.diceThrown());
  }

  @Test
  public void blackPlayerShouldHave1stTurn() {
    game.nextTurn();  //[1, 2] => Black
    assertEquals("Black player must have 1st turn.", Color.BLACK, game.getPlayerInTurn());
  }

  @Test
  public void redPlayerShouldHave2ndTurn() {
    game.nextTurn();  //[1, 2] => Black
    game.nextTurn();  //[3, 4] => Red
    assertEquals("Red player must have 2nd turn.", Color.RED, game.getPlayerInTurn());
  }

  @Test
  public void blackPlayerShouldHave3rdTurn() {
    game.nextTurn();  //[1, 2] => Black
    game.nextTurn();  //[3, 4] => Red
    game.nextTurn();  //[5, 6] => Black
    assertEquals("Black player must have 3rd turn.", Color.BLACK, game.getPlayerInTurn());
  }

  @Test
  public void redShouldHaveWonAfter6thTurn() {
    game.nextTurn(); //1st turn
    game.nextTurn(); //2nd turn
    game.nextTurn(); //3rd turn
    game.nextTurn(); //4th turn
    game.nextTurn(); //5th turn
    game.nextTurn(); //6th turn
    game.nextTurn(); //7th turn
    assertEquals("No player can have won the game until after 6th turn", Color.RED, game.winner());
  }

  @Test
  public void thereShouldBeNoWinnerAfter5thTurn() {
    game.nextTurn(); //1st turn
    game.nextTurn(); //2nd turn
    game.nextTurn(); //3rd turn
    game.nextTurn(); //4th turn
    game.nextTurn(); //5th turn
    game.nextTurn(); //6th turn
    assertEquals("Red player should have won the game after 6th turn", Color.NONE, game.winner());
  }

  @Test
  public void blackPlayerMovingCheckerFromR1toR7ShouldBeValidIn1stTurn() {
    game.nextTurn();  //[1, 2] => Black
    assertEquals("Number of moves left must be 2 before black exhausts any moves", 2, game.getNumberOfMovesLeft());
    assertTrue("It must be valid for black to move a checker from R1 to R2 in first turn", game.move(Location.R1, Location.R7));
    assertEquals("Number of checkers on R7 must be 1 after black moves a checker here", 1, game.getCount(Location.R7));
    assertEquals("Color of checker(s) on R7 must be black after black moves a checker here", Color.BLACK, game.getColor(Location.R7));
    assertEquals("Number of checkers on R1 must be 1 after black moves a checker from here", 1, game.getCount(Location.R1));
    assertEquals("Color of checker(s) on R1 must be black after black moves a checker from here", Color.BLACK, game.getColor(Location.R1));
    assertEquals("Number of moves left must be 1 after black has exhausted 1 of 2 moves", 1, game.getNumberOfMovesLeft());
  }

  @Test
  public void blackPlayerMovingCheckerFromR1toR2ShouldBeValidIn3rdTurn() {
    game.nextTurn();  //[1, 2] => Black
    game.nextTurn();  //[3, 4] => Red
    game.nextTurn();  //[5, 6] => Black
    assertEquals("Number of moves left must be 2 before black exhausts any moves", 2, game.getNumberOfMovesLeft());
    assertTrue("It must be valid for black to move a checker from R1 to R2 in third turn", game.move(Location.R1, Location.R2));
    assertEquals("Number of checkers on R2 must be 1 after black moves a checker here", 1, game.getCount(Location.R2));
    assertEquals("Color of checker(s) on R2 must be black after black moves a checker here", Color.BLACK, game.getColor(Location.R2));
    assertEquals("Number of checkers on R1 must be 1 after black moves a checker from here", 1, game.getCount(Location.R1));
    assertEquals("Color of checker(s) on R1 must be black after black moves a checker from here", Color.BLACK, game.getColor(Location.R1));
    assertEquals("Number of moves left must be 1 after black has exhausted 1 of 2 moves", 1, game.getNumberOfMovesLeft());
  }

  @Test
  public void blackPlayerMovingCheckerFromR12toR2ShouldBeValidIn1stTurn() {
    game.nextTurn();  //[1, 2] => Black
    assertEquals("Number of moves left must be 2 before black exhausts any moves", 2, game.getNumberOfMovesLeft());
    assertTrue("It must be valid for black to move a checker from R12 to R2 in first turn", game.move(Location.R12, Location.R2));
    assertEquals("Number of checkers on R7 must be 1 after black moves a checker here", 1, game.getCount(Location.R2));
    assertEquals("Color of checker(s) on R7 must be black after black moves a checker here", Color.BLACK, game.getColor(Location.R2));
    assertEquals("Number of checkers on R1 must be 4 after black moves a checker from here", 4, game.getCount(Location.R12));
    assertEquals("Color of checker(s) on R1 must be black after black moves a checker from here", Color.BLACK, game.getColor(Location.R12));
    assertEquals("Number of moves left must be 1 after black has exhausted 1 of 2 moves", 1, game.getNumberOfMovesLeft());
  }
}