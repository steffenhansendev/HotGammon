package hotgammon.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                TestLocation.class,
                TestAlphamon.class,
                TestBetaMon.class,
                TestGammaMon.class,
                TestHandicapMon.class
        }
)

public class TestAll {
}