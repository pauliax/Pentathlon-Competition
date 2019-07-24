package lt.paulius.pentathloncompetition.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {

    public static String convertDateToString(Date date, String format) {
        if (date == null) {
            return  null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    // Dates must be in mm:ss:S format
    public static int getDateDiffInMillis(String date1, String date2) {
        int millis1 = getMillis(date1);
        int millis2 = getMillis(date2);
        return millis1 - millis2;
    }

    public static Date addSeconds(Date date, int seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, seconds);
        return cal.getTime();
    }

    // Date must be in mm:ss:S format
    public static int getMillis(String date) {
        String minutes = date.substring(0, 2);
        String seconds = date.substring(3, 5);
        String millis = date.substring(6, 7);

        int minutesInt = Integer.parseInt(minutes);
        int secondsInt = Integer.parseInt(seconds);
        int millisInt = Integer.parseInt(millis);

        return (minutesInt * 60 * 1000) + (secondsInt * 1000) + (millisInt * 100);
    }

    // Dates must be in mm:ss:S format
    public static String addDates(String date1, String date2) {
        int millis1 = getMillis(date1);
        int millis2 = getMillis(date2);

        StringBuilder builder = new StringBuilder();

        int totalMillis = millis1 + millis2;

        if (totalMillis < 0) {
            builder.append("-");
            totalMillis *= -1;
        }

        int minutes = totalMillis / 1000 / 60;

        if (minutes < 10) {
            builder.append("0");
        }

        builder.append(minutes);
        totalMillis -= minutes * 60 * 1000;

        builder.append(":");

        int seconds = totalMillis / 1000;

        if (seconds < 10) {
            builder.append("0");
        }

        builder.append(seconds);
        totalMillis -= seconds * 1000;

        builder.append(".");

        int millis = totalMillis / 100;
        builder.append(millis);

        return builder.toString();
    }
}
