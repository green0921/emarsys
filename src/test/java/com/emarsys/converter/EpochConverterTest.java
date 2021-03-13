package com.emarsys.converter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;

public class EpochConverterTest {

    @InjectMocks
    private EpochConverterImpl underTest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGivenEpochTime_whenLocalToEpochTime_thenReturnEpochTime () {
        //GIVEN
        LocalDateTime localTime = LocalDateTime.of(2021, Month.MARCH, 12, 9, 45, 15);
        //WHEN
        long result = underTest.localDateTimeToEpochTime(localTime);
        //THEN
        assertEquals(result, 1615542315L);
    }

    @Test
    public void testGivenEpochTime_whenEpochTimeToLocalTime_thenReturnLocalTime () {
        //GIVEN
        long epochTime = 1615542315L;
        //WHEN
        LocalDateTime result = underTest.epochTimeToLocalDateTime(epochTime);
        //THEN
        assertEquals(result, LocalDateTime.of(2021, Month.MARCH, 12, 9, 45, 15));
    }
}
