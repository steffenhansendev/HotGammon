package hotgammon.domain.surrogates;

import hotgammon.domain.common.HotGammonFactory;
import hotgammon.domain.common.MoveStrategy;
import hotgammon.domain.common.RollStrategy;
import hotgammon.domain.common.WinningStrategy;
import hotgammon.domain.variance.EpsilonMonFactory;

public class TestEpsilonMonFactory implements HotGammonFactory {

    private HotGammonFactory epsilonMonFactory;
    int firstRoll;
    int secondRoll;

    public TestEpsilonMonFactory(int firstRoll, int secondRoll) {
        epsilonMonFactory = new EpsilonMonFactory();
        this.firstRoll = firstRoll;
        this.secondRoll = secondRoll;
    }

    public MoveStrategy createMoveStrategy() {
        return epsilonMonFactory.createMoveStrategy();
    }

    public WinningStrategy createWinningStrategy() {
        return epsilonMonFactory.createWinningStrategy();
    }

    public RollStrategy createRollStrategy() {
        return new TestStubRollStrategy(firstRoll, secondRoll);
    }
}