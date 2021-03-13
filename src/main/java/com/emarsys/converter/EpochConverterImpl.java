package com.emarsys.converter;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.emarsys.utilities.Constants.GMT_TIME_ZONE;

@Component
public class EpochConverterImpl implements EpochConverter {

    @Override
    public LocalDateTime epochTimeToLocalDateTime(long submitDate) {
        return Instant.ofEpochSecond(submitDate)
                .atZone(ZoneId.of(GMT_TIME_ZONE))
                .toLocalDateTime();
    }

    @Override
    public long localDateTimeToEpochTime(LocalDateTime localDateTime) {
        return localDateTime
                .atZone(ZoneId.of(GMT_TIME_ZONE))
                .toEpochSecond();
    }
}
