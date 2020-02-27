package hotgammon.domain.variance;

import hotgammon.domain.common.Game;
import hotgammon.domain.common.RollStrategy;

public class FixedRollStrategy implements RollStrategy {
    public int[] rollTheDice(Game game) {
        int[] diceRolled = game.diceThrown();
        switch (diceRolled[0]) {
            case -1:
            case 5:
                diceRolled[0] = 1;
                diceRolled[1] = 2;
                break;
            case 1:
                diceRolled[0] = 3;
                diceRolled[1] = 4;
                break;
            case 3:
                diceRolled[0] = 5;
                diceRolled[1] = 6;
                break;
            default:
        }
        return diceRolled;
    }
}
