package co.id.cakap.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateHelper {

    public static SimpleDateFormat backend_sdf, backend_sdf_notime;
    public static SimpleDateFormat dateFormatSlash;

    static {
        backend_sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

        backend_sdf_notime = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        dateFormatSlash = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    }

    public static String formatBackend(String strDate)  {
        try {
            Date date = dateFormatSlash.parse(strDate);
            return dateFormatSlash.format(date);
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
