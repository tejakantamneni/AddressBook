package com.jags;

import org.junit.Test;

/**
 * Created by tejakantamneni on 1/18/16.
 */
public class ExceptionTest {

    @Test
    public void testArithmatic(){
        try {
            System.out.println("try");
            int res = 1/4;
        } catch (Exception e) {
            System.out.println("exception");
            //e.printStackTrace();
            //throw e;
        }finally {
            System.out.println("finally");
        }
    }

}
