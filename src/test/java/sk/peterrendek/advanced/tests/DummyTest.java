package sk.peterrendek.advanced.tests;
import org.junit.jupiter.api.*;
@Tag("Blbosti")
public class DummyTest {
    public static final int INT = 100;
    public static final int SLEEP_TIME = 100;
    public static final boolean  CONSOLE_PRINT = false;
    static int numberOfFailedTest;


    private int failed = 0;

    @BeforeAll
    static void beforeAll() {
        System.out.println("SetUp class");
        numberOfFailedTest = 0;
    }

    @AfterAll
    static void afterAll() {
        System.out.println(numberOfFailedTest);
    }

    @BeforeEach
    void setUp() {
        System.out.println("SetUp method");
    }

    @AfterEach
    void tearDown() {
        System.out.println("==========");
    }

//    @Tag("SmokeTest")
//    @Tag("Release")
    @Tag("Blbosti")
    @Test
    void testA() throws InterruptedException {
        if(CONSOLE_PRINT){
            System.out.println("A");
            System.out.println("static: " + numberOfFailedTest);
            failed++;
            System.out.println("nonStatic: " + failed);
            for (int i = 1; i <= INT; i++) {
                System.out.println(i);
                Thread.sleep(SLEEP_TIME);
            }
            return;
        }
        failed++;
        for (int i = 1; i <= INT; i++) {
            Thread.sleep(SLEEP_TIME);
        }
    }

    @Tag("Blbosti")
    @Test
    void testB() throws InterruptedException {
        if(CONSOLE_PRINT){
            System.out.println("B");
            numberOfFailedTest++;
            System.out.println("static: " + numberOfFailedTest);
            failed = 10;
            System.out.println("nonStatic: " + failed);
            for (int i = 1; i <= INT; i++) {
                System.out.println("    "+i);
                Thread.sleep(SLEEP_TIME);
            }
            return;
        }

        numberOfFailedTest++;
        failed = 10;
        for (int i = 1; i <= INT; i++) {
            Thread.sleep(SLEEP_TIME);
        }

    }

    @Tag("Blbosti")
    @Test
    void testC() throws InterruptedException {
        if (CONSOLE_PRINT){
            System.out.println("C");
            System.out.println(numberOfFailedTest);
            System.out.println("nonStatic: " + failed);
            for (int i = 1; i <= INT; i++) {
                System.out.println("        "+i);
                Thread.sleep(SLEEP_TIME);
            }
            return;
        }
        for (int i = 1; i <= INT; i++) {
            Thread.sleep(SLEEP_TIME);
        }
    }
}
