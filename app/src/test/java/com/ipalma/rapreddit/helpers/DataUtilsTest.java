package com.ipalma.rapreddit.helpers;

import org.junit.Test;

import java.text.DecimalFormatSymbols;
import java.util.Locale;

import static org.junit.Assert.*;

public class DataUtilsTest {

    @Test
    public void testGetFormattedDateFromSeconds() throws Exception {
        long timeSeconds = System.currentTimeMillis() / 1000;
        String date = DataUtils.getFormattedDate(timeSeconds);

        assertEquals(date.length(), 10);
        assertEquals(date.charAt(2), '/');
        assertEquals(date.charAt(5), '/');
    }


    @Test
    public void testGetFormattedNumber() throws Exception {
        String formatted = DataUtils.getFormattedNumber(12334186);

        char s = DecimalFormatSymbols.getInstance(Locale.getDefault()).getGroupingSeparator();
        String expected = "12"+s+"334"+s+"186";

        assertEquals(formatted, expected);
    }
}