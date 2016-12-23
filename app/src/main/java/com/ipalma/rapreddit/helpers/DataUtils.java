package com.ipalma.rapreddit.helpers;

import com.ipalma.rapreddit.RRApplication;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DataUtils {

    public static final String DATE_TIME_FORMAT_PATTERN = "dd/MM/yyyy";

    /**
     * Converts Epoch seconds to Date with the format dd/MM/yyyy.
     * @param seconds to convert
     * @return formatted date string
     */
    public static String getFormattedDate(long seconds) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT_PATTERN, Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(seconds*1000);

        return formatter.format(calendar.getTime());
    }

    /**
     * Adds thousand separators to the provided number.
     * @param number to format
     * @return formatted number string
     */
    public static String getFormattedNumber(long number) {
        return NumberFormat.getNumberInstance(Locale.getDefault()).format(number);
    }

    public static String getString(int resId) {
        return RRApplication.getAppContext().getString(resId);
    }
}
