package com.emarsys.utilities;

import com.emarsys.json.LocalDateTimeSerializer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileUtils {

    @Value("classpath:holidays.json")
    private Resource holidaysResource;
    private List<LocalDate> holidays;

    @EventListener(ApplicationReadyEvent.class)
    public void readHolidays() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(LocalDate.class, new LocalDateTimeSerializer());
            objectMapper.registerModule(module);
            holidays = objectMapper.readValue(holidaysResource.getInputStream(), new TypeReference<ArrayList<LocalDate>>(){});

        } catch (IOException e) {
            throw new RuntimeException("Could not read from holidays.json");
        }
    }

    public List<LocalDate> getHolidays() {
        return holidays;
    }
}
