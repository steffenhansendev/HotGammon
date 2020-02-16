package hotgammon.domain;

import org.junit.runner.*;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                TestLocation.class,
                TestCommon.class,
                TestAlphamon.class,
                TestBetaMon.class
        }
)

public class TestAll {
}