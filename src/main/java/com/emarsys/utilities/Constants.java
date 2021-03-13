package com.emarsys.utilities;

import java.time.DayOfWeek;
import java.util.EnumSet;
import java.util.Set;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

public interface Constants {
    long WORK_HOUR_PER_DAY = 8L;
    long OPENING_HOUR = 9L;
    long CLOSING_HOUR = 17L;
    String GMT_TIME_ZONE = "GMT";
    Set<DayOfWeek> weekend = EnumSet.of(SATURDAY, SUNDAY);
}
