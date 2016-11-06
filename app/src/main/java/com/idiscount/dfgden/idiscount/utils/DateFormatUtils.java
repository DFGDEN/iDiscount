package com.idiscount.dfgden.idiscount.utils;

import android.content.Context;

import com.idiscount.dfgden.idiscount.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateFormatUtils {


    private static long convertTimeDayFormat(Context context, String time) throws ParseException {
        String resultDate = null;
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", context.getResources().getConfiguration().locale);
        Date parseDate = dateFormat.parse(time);
        return parseDate.getTime();
    }

    public static String getPubDate(Context context,String date) {
        long deltaTime = 0;
        try {
            deltaTime = System.currentTimeMillis() - convertTimeDayFormat(context, date);

        long week = deltaTime / 604800000;
        long day = deltaTime / 86400000;
        long hour = deltaTime / 3600000;
        long min = deltaTime / 60000;

        if (week == 1) {
            return context.getString(R.string.timeutils_weekago) ;
        }
        if (week > 11 && week%10 == 1) {
            return String.format(context.getString(R.string.timeutils_21weeksago), week);
        }
        if (week >= 2) {
            return String.format(context.getString(R.string.timeutils_weeksago), week);
        }
        if (day == 1) {
            return context.getString(R.string.timeutils_dayago);
        }
        if (day > 11 &&day%10 == 1) {
            return String.format(context.getString(R.string.timeutils_21daysago), day);
        }
        if (day >= 2) {
            return String.format(context.getString(R.string.timeutils_daysago), day);
        }
        if (hour == 1) {
            return context.getString(R.string.timeutils_hourago);
        }
        if (hour > 11 &&hour%10 == 1) {
            return String.format(context.getString(R.string.timeutils_21hoursago), hour) ;
        }
        if (hour >= 2) {
            return String.format(context.getString(R.string.timeutils_hoursago), hour);
        }
        if (min == 1) {
            return context.getString(R.string.timeutils_minago);
        }
        if (min > 11 && min%10 == 1) {
            return String.format(context.getString(R.string.timeutils_21minutsago), min);
        }
        if (min >= 2) {
            return String.format(context.getString(R.string.timeutils_minutsago), min);
        }
        return context.getString(R.string.timeutils_rightnow);
        } catch (ParseException e) {
            return "";
        }

    }

}
