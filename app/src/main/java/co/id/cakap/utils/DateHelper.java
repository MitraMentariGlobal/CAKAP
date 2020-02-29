package co.id.cakap.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateHelper {

    public static SimpleDateFormat backend_sdf, backend_sdf_notime;
    public static SimpleDateFormat dateFormatFrontEnd;
    public static SimpleDateFormat monthFullNameFormat;
    public static SimpleDateFormat monthNumberFormat;

    static {
        backend_sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        backend_sdf_notime = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        dateFormatFrontEnd = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        monthFullNameFormat = new SimpleDateFormat("MMMM", Locale.US);
        monthNumberFormat = new SimpleDateFormat("MM", Locale.US);
    }

    public static String changeToFormatBackend(String oldDate)  {
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = newDateFormat.parse(oldDate);
            newDateFormat.applyPattern("yyyy-MM-dd");
            return newDateFormat.format(date);
        } catch (Exception e) {
            System.out.println("* date parse error " + e.getMessage());
            return "";
        }
    }

    public static String getTimeNow() {
        Calendar c = Calendar.getInstance();
        return dateFormatFrontEnd.format(c.getTime());
    }

    public static String getTimeNowBackEnd() {
        Calendar c = Calendar.getInstance();
        return backend_sdf_notime.format(c.getTime());
    }

    public static String getMonthNumber(String monthName) {
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(monthFullNameFormat.parse(monthName));
            return monthNumberFormat.format(cal.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }
}
