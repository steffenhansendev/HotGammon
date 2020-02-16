package hotgammon.domain;
import hotgammon.domain.common.Color;
import hotgammon.domain.common.Game;
import hotgammon.domain.common.GameImpl;
import hotgammon.domain.variants.AlphaMonMoveStrategy;
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
    game = new GameImpl(new AlphaMonMoveStrategy());
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
}