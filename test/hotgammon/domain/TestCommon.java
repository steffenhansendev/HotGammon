package hotgammon.domain;

import org.junit.Before;

public class TestCommon {
    private Game game;
    @Before
    public void setup() {
        game = new GameImpl(new AlphaMonMoveStrategy());
    }
}
