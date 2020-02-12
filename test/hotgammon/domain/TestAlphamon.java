package hotgammon.domain;
import org.junit.*;
import static org.junit.Assert.*;
/** Testing skeleton for AlphaMon.
 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/
public class TestAlphamon {
  private Game game;
  
  @Before
  public void setup() {
    game = new GameImpl();
  }

  @Test
  public void noPlayerShouldBeInTurnInitially() {
    game.newGame();
    assertEquals("No player can be in turn before the game has begun.", Color.NONE, game.getPlayerInTurn());
  }

  @Test
  public void _1stRollShouldYield_1_2() {
    game.newGame();//[1, 2]
    game.nextTurn();
    assertArrayEquals("First roll must yield [1,2].", new int[]{1, 2}, game.diceThrown());
  }

  @Test
  public void _2ndRollShouldYield_3_4() {
    game.newGame();
    game.nextTurn();  //[1, 2]
    game.nextTurn();  //[3, 4]
    assertArrayEquals("Second roll must yield [3,4].", new int[]{3, 4}, game.diceThrown());
  }

  @Test
  public void _3rdRollShouldYield_5_6() {
    game.newGame();
    game.nextTurn();  //[1, 2]
    game.nextTurn();  //[3, 4]
    game.nextTurn();  //[5, 6]
    assertArrayEquals("Third roll must yield [5,6].", new int[]{5, 6}, game.diceThrown());
  }

  @Test
  public void _4thRollShouldYield_1_2() {
    game.newGame();
    game.nextTurn();  //[1, 2]
    game.nextTurn();  //[3, 4]
    game.nextTurn();  //[5, 6]
    game.nextTurn();  //[1, 2]
    assertArrayEquals("Third roll must yield [5,6].", new int[]{1, 2}, game.diceThrown());
  }

  @Test
  public void blackPlayerShouldHave1stTurn() {
    game.newGame();
    game.nextTurn();  //Black
    assertEquals("Black player must have 1st turn.", Color.BLACK, game.getPlayerInTurn());
  }

  @Test
  public void redPlayerShouldHave2ndTurn() {
    game.newGame();
    game.nextTurn();  //Black
    game.nextTurn();  //Red
    assertEquals("Red player must have 2nd turn.", Color.RED, game.getPlayerInTurn());
  }

  @Test
  public void blackPlayerShouldHave3rdTurn() {
    game.newGame();
    game.nextTurn();  //Black
    game.nextTurn();  //Red
    game.nextTurn();  //Black
    assertEquals("Black player must have 3rd turn.", Color.BLACK, game.getPlayerInTurn());
  }

  @Test
  public void thereShouldBeNoWinnerInitially() {
    game.newGame();
    assertEquals("No player can have won the game until after 6th turn", Color.NONE, game.winner());
  }

  @Test
  public void redShouldHaveWonAfter6thTurn() {
    game.newGame();
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
    game.newGame();
    game.nextTurn(); //1st turn
    game.nextTurn(); //2nd turn
    game.nextTurn(); //3rd turn
    game.nextTurn(); //4th turn
    game.nextTurn(); //5th turn
    game.nextTurn(); //6th turn
    assertEquals("Red player should have won the game after 6th turn", Color.NONE, game.winner());
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

  @Test
  public void diceValuesLeftShouldBeSortedInGrowingOrderAndCoincideWithDiceThrownInEveryTurn() {
    game.newGame();
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
  public void movingBlackCheckerFromR12toR11ShouldBeRejected() {
    game.newGame();
    game.nextTurn();  //[1, 2] & Black
    assertFalse("Moving black checker from R12 to R11 must be rejected because the destination is further away from black bear off", game.move(Location.R12, Location.R11));
  }

  @Test
  public void movingRedCheckerFromB12ToB9ShouldBeRejected() {
    game.newGame();
    game.nextTurn();  //[1, 2] & Black
    game.nextTurn();  //[3, 4] & Red
    assertFalse("Moving red checker from B12 to R9 must be rejected because the destination is further away from red bear off", game.move(Location.B12, Location.B9));
  }

  @Test
  public void movingBlackCheckerFromR1toR1ShouldBeRejected() {
    game.newGame();
    game.nextTurn();  //[1, 2] & Black
    assertFalse("Moving black checker from R1 to R1 must be rejected because destination is the same as origin",
            game.move(Location.R1, Location.R1));
  }

  @Test
  public void movingRedCheckerFromB1toB1ShouldBeRejected() {
    game.newGame();
    game.nextTurn();  //[1, 2] & Black
    game.nextTurn();  //[3, 4] & Red
    assertFalse("Moving black checker from B1 to B1 must be rejected because destination is the same as origin",
            game.move(Location.B1, Location.B1));
  }
}