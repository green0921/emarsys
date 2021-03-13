package com.emarsys.service;

import com.emarsys.exception.TimeValidationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.emarsys.utilities.Constants.*;

@Service
public class TimeValidatorImpl implements TimeValidator {

    @Override
    public void validateWorkingHours(LocalDateTime submitTime) {
        if(isNotWorkingHour(submitTime)) {
            throw new TimeValidationException("Submit time cannot be outside opening hours!");
        }
    }

    private boolean isNotWorkingHour(LocalDateTime submitTime) {
        return submitTime.getHour() < OPENING_HOUR || submitTime.getHour() >= CLOSING_HOUR;
    }

    @Override
    public void validateWorkingDay(LocalDateTime submitTime, List<LocalDate> holidays) {
        if(isWeekend(submitTime) || isHoliday(submitTime, holidays)) {
            throw new TimeValidationException("Submit time cannot be the Weekend or Holiday!");
        }
    }

    private boolean isHoliday(LocalDateTime submitTime, List<LocalDate> holidays) {
        return holidays.contains(submitTime.toLocalDate());
    }

    private boolean isWeekend(LocalDateTime submitTime) {
        return weekend.contains(submitTime.getDayOfWeek());
    }

}
