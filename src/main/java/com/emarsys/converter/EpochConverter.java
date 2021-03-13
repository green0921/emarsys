package com.emarsys.converter;

import java.time.LocalDateTime;

public interface EpochConverter {

    LocalDateTime epochTimeToLocalDateTime(long submitDate);
    long localDateTimeToEpochTime(LocalDateTime localDateTime);
}
