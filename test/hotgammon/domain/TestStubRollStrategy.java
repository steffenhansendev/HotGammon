package hotgammon.domain;

import hotgammon.domain.common.Game;
import hotgammon.domain.common.RollStrategy;

public class TestStubRollStrategy implements RollStrategy {

    private int firstRoll;
    private int secondRoll;

    public TestStubRollStrategy(int firstRoll, int secondRoll) {
        this.firstRoll = firstRoll;
        this.secondRoll = secondRoll;
    }

    public int[] rollTheDice(Game game) {
        return new int[]{firstRoll, secondRoll};
    }
}
