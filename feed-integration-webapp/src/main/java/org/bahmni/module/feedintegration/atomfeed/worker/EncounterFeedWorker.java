package org.bahmni.module.feedintegration.atomfeed.worker;

import org.bahmni.module.feedintegration.atomfeed.contract.encounter.OpenMRSEncounter;
import org.bahmni.module.feedintegration.atomfeed.contract.patient.OpenMRSPatientFullRepresentation;
import org.bahmni.module.feedintegration.services.OpenMRSService;
import org.ict4h.atomfeed.client.domain.Event;
import org.ict4h.atomfeed.client.service.EventWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EncounterFeedWorker implements EventWorker {
    private static final Logger logger = LoggerFactory.getLogger(EncounterFeedWorker.class);

    @Autowired
    private OpenMRSService openMRSService;

    public EncounterFeedWorker() {
    }

    @Override
    public void process(Event event) {
        try {
            logger.info("Getting patient details ...");
            String encounterUri = event.getContent();
            OpenMRSEncounter encounter = openMRSService.getEncounter(encounterUri);
            logger.info(String.valueOf(encounter));
            logger.info(encounterUri);
        } catch (Exception e) {
            logger.error("Failed to fetch patient details", e);
            throw new RuntimeException("Failed to fetch patient details", e);
        }
    }

    @Override
    public void cleanUp(Event event) {
    }
}