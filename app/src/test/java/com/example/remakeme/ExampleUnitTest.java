package com.example.remakeme;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_split_and_join(){
        ArrayList<Long> events = new ArrayList<Long>();
        events.add(new Long(1));
        events.add(new Long(2));
        events.add(new Long(3));
        events.add(new Long(4));
        String result = events.toString().replaceAll("\\]", ", ]");
        assertEquals("[1, 2, 3, 4, ]", result);
        String modified = result.replaceAll("\\[|\\]|\\s| ", "");
        modified = modified.replaceAll(".$", "");
        assertEquals("1,2,3,4", modified);
        String[] stringSplited = modified.split(",");

        ArrayList<Long> final_events = new ArrayList<Long>();
        for(int i = 0; i < stringSplited.length; i++){
            try {
                final_events.add(Long.parseLong(stringSplited[i]));
                assertEquals(events.get(i), final_events.get(i));
            } catch (NumberFormatException nfe) {
                //NOTE: write something here if you need to recover from formatting errors
            };
        }
    }

    @Test
    public void test_regex_stuff(){
        String listString = "[1, 2, 3, ]";
        String modified = listString.replaceAll("\\[|\\]|\\s| ", "");
        modified = modified.replaceAll(".$", "");
        assertEquals("1,2,3", modified);
        String[] answer = modified.split(",");
        assertEquals(3, answer.length);

    }
}