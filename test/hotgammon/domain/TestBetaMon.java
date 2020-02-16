package hotgammon.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class TestBetaMon {
    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new BetaMonMoveStrategy());
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
