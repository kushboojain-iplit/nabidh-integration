package org.bahmni.module.feedintegration.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bahmni.module.feedintegration.model.FailedEvent;
import org.bahmni.module.feedintegration.repository.FailedEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ict4h.atomfeed.client.domain.Event;

import java.util.List;

@Component
public class FailedEventService {
    @Autowired
    private FailedEventRepository failedEventRepository;

    @Autowired
    private ObjectMapper objectMapper;


    public boolean checkIfPreviousEventFailedFor(String patientUri) throws JsonProcessingException {
        List<FailedEvent> failedEvents = failedEventRepository.findAll();
        for (FailedEvent failedEvent : failedEvents) {
            String eventData = failedEvent.getEvent();

            if (eventData.contains(patientUri)) {
                return true;
            }
        }
        return false;
    }
}

