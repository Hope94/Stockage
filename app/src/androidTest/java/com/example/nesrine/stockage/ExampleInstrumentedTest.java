package com.example.nesrine.stockage;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.nesrine.stockage", appContext.getPackageName());
    }

    @Test
    //  retourne tjr void
    public  void getUserConnected_Test(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        SharedExample sharedExample=new SharedExample(appContext);
        assertEquals(sharedExample.saveConnectUser("user 1"),true);

    }
    @Test
    //  retourne tjr void
    public  void saveConnectedUser_Test(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        SharedExample sharedExample=new SharedExample(appContext);
        assertEquals(sharedExample.getUserConnected(),"user 1");

    }
    @Test
    //  retourne tjr void
    public  void isConnected_Test(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        SharedExample sharedExample=new SharedExample(appContext);
        assertTrue(sharedExample.isConnected());
    }
    // les fontion qui retourne void on ne peut pas les tester.

}
