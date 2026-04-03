package sn.khadim.l2gl.app.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class DateUtils {
    private static final String DEFAULT_PATTERN = "dd/MM/yyyy";

    private DateUtils() {
    }

    public static Calendar createCalendar(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        calendar.set(year, month - 1, day, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.getTime();
        return calendar;
    }

    public static int getAge(Calendar date) {
        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - date.get(Calendar.YEAR);
        int currentMonth = today.get(Calendar.MONTH);
        int dateMonth = date.get(Calendar.MONTH);
        int currentDay = today.get(Calendar.DAY_OF_MONTH);
        int dateDay = date.get(Calendar.DAY_OF_MONTH);

        if (currentMonth < dateMonth || (currentMonth == dateMonth && currentDay < dateDay)) {
            age--;
        }

        return age;
    }

    public static String format(Calendar calendar) {
        return new SimpleDateFormat(DEFAULT_PATTERN).format(calendar.getTime());
    }
}
