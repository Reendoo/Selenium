package sk.peterrendek.advanced.tests;
import org.junit.jupiter.api.*;
import sk.peterrendek.advanced.base.TestBase;

public class AppleTest extends TestBase {

    @Test
    void reposeDesignTest() {
      getDriver().get("http://www.nike.sk");
    }


}
