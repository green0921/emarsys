package com.emarsys.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TimeValidator {

    void validateWorkingHours(LocalDateTime submitTime);
    void validateWorkingDay(LocalDateTime submitTime, List<LocalDate> holidays);
}
