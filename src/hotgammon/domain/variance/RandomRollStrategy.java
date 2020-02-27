package hotgammon.domain.variance;

import hotgammon.domain.common.Game;
import hotgammon.domain.common.RollStrategy;

import java.util.Random;

public class RandomRollStrategy implements RollStrategy {

    public int[] rollTheDice(Game game) {
        Random random = new Random();
        int firstRoll = random.nextInt(5) + 1;
        int secondRoll = random.nextInt(5) + 1;
        int[] diceRolled = new int[]{firstRoll, secondRoll};

        //For manual testing
        /*
        System.out.println("First roll is: " + diceRolled[0]);
        System.out.println("Second roll is: " + diceRolled[1]);
        */

        return diceRolled;
    }
}
