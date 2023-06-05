package sk.peterrendek.advanced.tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Blbosti")
public class FirstParallelUnitTest{
    public static final int INT = 100;
    public static final int SLEEP= 100;

    @Test
    public void first() throws Exception{
        System.out.println("FirstParallelUnitTest first() start => " + Thread.currentThread().getName());

        System.out.println("FirstParallelUnitTest first() end => " + Thread.currentThread().getName());
        for (int i = 1; i <= INT; i++) {
//            System.out.println(i);
            Thread.sleep(SLEEP);
        }
    }

    @Test
    public void second() throws Exception{
        System.out.println("FirstParallelUnitTest second() start => " + Thread.currentThread().getName());
        Thread.sleep(500);
        System.out.println("FirstParallelUnitTest second() end => " + Thread.currentThread().getName());
        for (int i = 1; i <= INT; i++) {
//            System.out.println("    "+i);
            Thread.sleep(SLEEP);
        }
    }
}