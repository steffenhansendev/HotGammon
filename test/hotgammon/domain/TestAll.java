package hotgammon.domain;

import org.junit.runner.*;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                TestLocation.class,
                TestAlphamon.class,
                TestBetaMon.class,
                TestGammaMon.class
        }
)

public class TestAll {
}