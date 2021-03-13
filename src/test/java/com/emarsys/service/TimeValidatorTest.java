package com.emarsys.service;

import com.emarsys.exception.TimeValidationException;
import com.emarsys.testUtilities.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TimeValidatorTest {

    @InjectMocks
    private TimeValidatorImpl underTest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGivenLocalTime_whenTimeBetweenWorkingHours_thenDoNothing () {
        //GIVEN
        LocalDateTime localTime = LocalDateTime.of(2021, Month.MARCH, 12, 9, 0, 0);
        //WHEN
        //THEN
        underTest.validateWorkingHours(localTime);
    }

    @Test
    public void testGivenLocalTime_whenTimeBeforeWorkingHours_thenThrowTimeValidationException  () {
        //GIVEN
        LocalDateTime localTime = LocalDateTime.of(2021, Month.MARCH, 12, 8, 59, 59);
        //WHEN
        TimeValidationException timeValidationException = Assertions.assertThrows(TimeValidationException.class, () -> {
            underTest.validateWorkingHours(localTime);
        });
        //THEN
        assertEquals(timeValidationException.getMessage(), "Submit time cannot be outside opening hours!");
    }

    @Test
    public void testGivenLocalTime_whenTimeAfterWorkingHours_thenThrowTimeValidationException  () {
        //GIVEN
        LocalDateTime localTime = LocalDateTime.of(2021, Month.MARCH, 12, 17, 0, 0);
        //WHEN
        TimeValidationException timeValidationException = Assertions.assertThrows(TimeValidationException.class, () -> {
            underTest.validateWorkingHours(localTime);
        });
        //THEN
        assertEquals(timeValidationException.getMessage(), "Submit time cannot be outside opening hours!");
    }

    @Test
    public void testGivenRequest_whenWorkingDay_thenDoNothing () {
        //GIVEN
        LocalDateTime localTime = LocalDateTime.of(2021, Month.MARCH, 12, 9, 0, 0);
        List<LocalDate> holidays = TestUtils.createHolidays();
        //WHEN
        //THEN
        underTest.validateWorkingDay(localTime, holidays);
    }

    @Test
    public void testGivenRequest_whenNotWorkingDay_thenThrowTimeValidationException  () {
        //GIVEN
        LocalDateTime localTime = LocalDateTime.of(2021, Month.MARCH, 13, 9, 0, 0);
        List<LocalDate> holidays = TestUtils.createHolidays();
        //WHEN
        TimeValidationException timeValidationException = Assertions.assertThrows(TimeValidationException.class, () -> {
            underTest.validateWorkingDay(localTime, holidays);
        });
        //THEN
        assertEquals(timeValidationException.getMessage(), "Submit time cannot be the Weekend or Holiday!");
    }

    @Test
    public void testGivenRequest_whenHoliday_thenThrowTimeValidationException  () {
        //GIVEN
        LocalDateTime localTime = LocalDateTime.of(2021, Month.MARCH, 2, 9, 0, 0);
        List<LocalDate> holidays = TestUtils.createHolidays();
        //WHEN
        TimeValidationException timeValidationException = Assertions.assertThrows(TimeValidationException.class, () -> {
            underTest.validateWorkingDay(localTime, holidays);
        });
        //THEN
        assertEquals(timeValidationException.getMessage(), "Submit time cannot be the Weekend or Holiday!");
    }

}
