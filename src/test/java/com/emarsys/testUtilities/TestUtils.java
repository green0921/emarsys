package com.emarsys.testUtilities;

import com.emarsys.model.TimeRequest;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    private TestUtils() {
    }

    public static TimeRequest createTimeRequest(long submitTime, long turnaroundTime) {
        TimeRequest timeRequest = new TimeRequest();
        timeRequest.setSubmitTime(submitTime);
        timeRequest.setTurnaroundTime(turnaroundTime);
        return timeRequest;
    }

    public static List<LocalDate> createHolidays() {
        List<LocalDate> holidays = new ArrayList<>();
        holidays.add(LocalDate.of(2021, Month.MARCH, 2 ));
        return holidays;
    }
}
