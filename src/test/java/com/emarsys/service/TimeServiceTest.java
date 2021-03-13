package com.emarsys.service;

import com.emarsys.converter.EpochConverter;
import com.emarsys.model.TimeRequest;
import com.emarsys.utilities.FileUtils;
import com.emarsys.testUtilities.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class TimeServiceTest {

    @Mock
    private EpochConverter epochConverter;
    @Mock
    private TimeValidator timeValidator;
    @Mock
    private FileUtils fileUtils;

    @InjectMocks
    private TimeServiceImpl underTest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCalculateDueDate_whenSolutionTimeContainsWeekend_thenReturnEpochTime() {
        //GIVEN
        TimeRequest timeRequest = TestUtils.createTimeRequest(1615542315L, 20L);;
        LocalDateTime localDateTime = LocalDateTime.of(2021, Month.MARCH, 12, 9, 45, 15);
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2021, Month.MARCH, 16, 13, 45, 15);
        long expectedEpochTime = 1615815915L;
        when(epochConverter.epochTimeToLocalDateTime(timeRequest.getSubmitTime())).thenReturn(localDateTime);
        doNothing().when(timeValidator).validateWorkingHours(any());
        doNothing().when(timeValidator).validateWorkingDay(any(), any());
        when(fileUtils.getHolidays()).thenReturn(TestUtils.createHolidays());
        when(epochConverter.localDateTimeToEpochTime(expectedLocalDateTime)).thenReturn(expectedEpochTime);
        //WHEN
        long result = underTest.calculateDueDate(timeRequest);
        //THEN
        assertEquals(result, expectedEpochTime);
    }

    @Test
    public void testCalculateDueDate_whenSolutionTimeNotContainsWeekend_thenReturnEpochTime() {
        //GIVEN
        TimeRequest timeRequest = TestUtils.createTimeRequest(1615283115L, 20L);
        LocalDateTime localDateTime = LocalDateTime.of(2021, Month.MARCH, 9, 9, 45, 15);
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2021, Month.MARCH, 11, 13, 45, 15);
        long expectedEpochTime = 1615470315L;
        when(epochConverter.epochTimeToLocalDateTime(timeRequest.getSubmitTime())).thenReturn(localDateTime);
        doNothing().when(timeValidator).validateWorkingHours(any());
        doNothing().when(timeValidator).validateWorkingDay(any(), any());
        when(fileUtils.getHolidays()).thenReturn(TestUtils.createHolidays());
        when(epochConverter.localDateTimeToEpochTime(expectedLocalDateTime)).thenReturn(expectedEpochTime);
        //WHEN
        long result = underTest.calculateDueDate(timeRequest);
        //THEN
        assertEquals(result, expectedEpochTime);
    }

    @Test
    public void testCalculateDueDate_whenSolutionTimeShiftToNextDay_thenReturnEpochTime() {
        //GIVEN
        TimeRequest timeRequest = TestUtils.createTimeRequest(1615308315L, 5L);
        LocalDateTime localDateTime = LocalDateTime.of(2021, Month.MARCH, 9, 16, 45, 15);
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2021, Month.MARCH, 10, 13, 45, 15);
        long expectedEpochTime = 1615383915L;
        when(epochConverter.epochTimeToLocalDateTime(timeRequest.getSubmitTime())).thenReturn(localDateTime);
        doNothing().when(timeValidator).validateWorkingHours(any());
        doNothing().when(timeValidator).validateWorkingDay(any(), any());
        when(fileUtils.getHolidays()).thenReturn(TestUtils.createHolidays());
        when(epochConverter.localDateTimeToEpochTime(expectedLocalDateTime)).thenReturn(expectedEpochTime);
        //WHEN
        long result = underTest.calculateDueDate(timeRequest);
        //THEN
        assertEquals(result, expectedEpochTime);
    }

    @Test
    public void testCalculateDueDate_whenSolutionContainsHoliday_thenReturnEpochTime() {
        //GIVEN
        TimeRequest timeRequest = TestUtils.createTimeRequest(1615308315L, 8L);
        LocalDateTime localDateTime = LocalDateTime.of(2021, Month.MARCH, 1, 9, 45, 15);
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2021, Month.MARCH, 3, 9, 45, 15);
        long expectedEpochTime = 1615383915L;
        when(epochConverter.epochTimeToLocalDateTime(timeRequest.getSubmitTime())).thenReturn(localDateTime);
        doNothing().when(timeValidator).validateWorkingHours(any());
        doNothing().when(timeValidator).validateWorkingDay(any(), any());
        when(fileUtils.getHolidays()).thenReturn(TestUtils.createHolidays());
        when(epochConverter.localDateTimeToEpochTime(expectedLocalDateTime)).thenReturn(expectedEpochTime);
        //WHEN
        long result = underTest.calculateDueDate(timeRequest);
        //THEN
        assertEquals(result, expectedEpochTime);
    }
}
