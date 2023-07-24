package org.bahmni.module.feedintegration.atomfeed.worker;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.bahmni.module.feedintegration.atomfeed.contract.patient.OpenMRSPatientFullRepresentation;
import org.bahmni.module.feedintegration.services.FailedEventService;
import org.bahmni.module.feedintegration.services.OpenMRSService;
import org.ict4h.atomfeed.client.domain.Event;
import org.ict4h.atomfeed.client.service.EventWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientFeedWorker implements EventWorker {
    private static final Logger logger = LoggerFactory.getLogger(PatientFeedWorker.class);

    @Autowired
    private OpenMRSService openMRSService;

    @Autowired
    private FailedEventService failedEventService;

    public PatientFeedWorker() {
    }

    @Override
    public void process(Event event) {
        try {
            logger.info("Getting patient details ...");
            String patientUri = event.getContent();
            if (!checkIfFailedEvent(patientUri)) {
                OpenMRSPatientFullRepresentation patientFR = openMRSService.getPatientFR(patientUri);
                if (patientFR.getPerson().getNames().get(0).getGivenName().startsWith("B")) {
                    throw new RuntimeException("Trying failed events");
                }
                logger.info(String.valueOf(patientFR));
                logger.info(patientUri);
            } else {
                logger.info("Event Failed");
                throw new RuntimeException("Failed Patient");
            }
        } catch (Exception e) {
            logger.error("Failed to fetch patient details", e);
            throw new RuntimeException("Failed to fetch patient details", e);
        }
    }

    private boolean checkIfFailedEvent(String patientUri) throws JsonProcessingException {
        return failedEventService.checkIfPreviousEventFailedFor(patientUri);
    }

    @Override
    public void cleanUp(Event event) {
    }
}
