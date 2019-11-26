package co.id.cakap.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateHelper {

    public static SimpleDateFormat frontend_sdf_simple;
    public static SimpleDateFormat backend_sdf, backend_sdf_notime;
    public static SimpleDateFormat timeForPhotoName;
    public static SimpleDateFormat dateFormatCashout;
    public static SimpleDateFormat dateFormatSlash;

    static {
        frontend_sdf_simple = new SimpleDateFormat("dd MMM yyyy", Locale.US);
        frontend_sdf_simple.setTimeZone(TimeZone.getTimeZone("GMT+7"));

        backend_sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        backend_sdf.setTimeZone(TimeZone.getTimeZone("GMT+7"));

        backend_sdf_notime = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        backend_sdf_notime.setTimeZone(TimeZone.getTimeZone("GMT+7"));

        timeForPhotoName = new SimpleDateFormat("ddMMyyyyHHmmss", Locale.US);
        timeForPhotoName.setTimeZone(TimeZone.getTimeZone("GMT+7"));

        dateFormatCashout = new SimpleDateFormat("dd MM yyyy", Locale.US);
        dateFormatCashout.setTimeZone(TimeZone.getTimeZone("GMT+7"));

        dateFormatSlash = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    }

    public static String formatBackendSlash(String strDate)  {
        try {
            Date date = dateFormatSlash.parse(strDate);
            return dateFormatSlash.format(date);
        } catch (Exception e) {
            System.out.println("* date parse error " + e.getMessage());
            return "";
        }
    }

    public static String formatBackendDateNoTime(String strDate)  {
        try {
            Date date = frontend_sdf_simple.parse(strDate);
            return backend_sdf_notime.format(date);
        } catch (Exception e) {
            System.out.println("* date parse error " + e.getMessage());
            return "";
        }
    }

    public static String formatCashout(String strDate)  {
        try {
            Date date = backend_sdf_notime.parse(strDate);
            return dateFormatCashout.format(date);
        } catch (Exception e) {
            System.out.println("* date parse error " + e.getMessage());
            return "";
        }
    }

    public static String formatFinishedDetail(String strDate)  {
        try {
            Date date = backend_sdf.parse(strDate);
            return dateFormatCashout.format(date);
        } catch (Exception e) {
            System.out.println("* date parse error " + e.getMessage());
            return "";
        }
    }

    public static String formatFrontEndNoTime(String strDate)  {
        try {
            Date date = backend_sdf_notime.parse(strDate);
            return frontend_sdf_simple.format(date);
        } catch (Exception e) {
            System.out.println("* date parse error " + e.getMessage());
            return "";
        }
    }

    public static String getTimeNow() {
        Calendar c = Calendar.getInstance();
        return dateFormatSlash.format(c.getTime());
    }

    public static String getTimeNowBackEnd() {
        Calendar c = Calendar.getInstance();
        return backend_sdf.format(c.getTime());
    }
}
