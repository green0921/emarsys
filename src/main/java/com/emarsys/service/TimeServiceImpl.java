package com.emarsys.service;

import com.emarsys.converter.EpochConverter;
import com.emarsys.model.TimeRequest;
import com.emarsys.utilities.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static com.emarsys.utilities.Constants.*;

@Service
public class TimeServiceImpl implements TimeService {

    @Autowired
    private EpochConverter epochConverter;
    @Autowired
    private TimeValidator timeValidator;
    @Autowired
    private FileUtils fileUtils;

    @Override
    public long calculateDueDate(TimeRequest timeRequest) {
        LocalDateTime submitTime = epochConverter.epochTimeToLocalDateTime(timeRequest.getSubmitTime());
        timeValidator.validateWorkingHours(submitTime);
        timeValidator.validateWorkingDay(submitTime, fileUtils.getHolidays());
        LocalDateTime solutionTime = adjustWithSolutionTime(submitTime, timeRequest.getTurnaroundTime());
        return epochConverter.localDateTimeToEpochTime(solutionTime);
    }

    private LocalDateTime adjustWithSolutionTime(LocalDateTime solutionTime, long turnaroundTime) {
        long solutionDays = turnaroundTime / WORK_HOUR_PER_DAY ;
        long solutionHours = turnaroundTime % WORK_HOUR_PER_DAY;
        LocalDateTime adjustedTimeWithHours = adjustWithHours(solutionTime, solutionHours);
        return adjustWithDays(adjustedTimeWithHours, solutionDays);
    }

    private LocalDateTime adjustWithDays(LocalDateTime adjustedTimeWithHours, long solutionDays) {
        long weekendsAndHolidays = Stream.iterate(adjustedTimeWithHours, date -> date.plusDays(1))
                .limit(solutionDays)
                .map(date -> date.plusDays(1))
                .filter(date -> weekend.contains(date.getDayOfWeek()) || fileUtils.getHolidays().contains(date.toLocalDate()))
                .count();
        return adjustedTimeWithHours.plusDays(weekendsAndHolidays + solutionDays);
    }

    private LocalDateTime adjustWithHours(LocalDateTime solutionTime, long solutionHours) {
        return solutionTime.getHour() + solutionHours >= CLOSING_HOUR ?
                solutionTime.plusDays(1).minusHours(WORK_HOUR_PER_DAY - solutionHours) :
                solutionTime.plusHours(solutionHours);
    }
}
