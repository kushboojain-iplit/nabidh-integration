package org.bahmni.module.feedintegration.atomfeed.worker;

import org.bahmni.module.feedintegration.atomfeed.contract.encounter.OpenMRSEncounter;
import org.bahmni.module.feedintegration.atomfeed.contract.encounter.OpenMRSOrder;
import org.bahmni.module.feedintegration.atomfeed.contract.patient.OpenMRSPatientFullRepresentation;
import org.bahmni.module.feedintegration.services.OpenMRSService;
import org.ict4h.atomfeed.client.domain.Event;
import org.ict4h.atomfeed.client.service.EventWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EncounterFeedWorker implements EventWorker {
    private static final Logger logger = LoggerFactory.getLogger(EncounterFeedWorker.class);
    public static final String DISCHARGE_EVENT = "discharge";

    @Autowired
    private OpenMRSService openMRSService;

    public EncounterFeedWorker() {
    }

    @Override
    public void process(Event event) {
        try {
            String encounterUri = event.getContent();
            OpenMRSEncounter encounter = openMRSService.getEncounter(encounterUri);
            displayEventCode(encounter);
        } catch (Exception e) {
            logger.error("Failed to fetch patient details", e);
            throw new RuntimeException("Failed to fetch patient details", e);
        }
    }

    private static void displayEventCode(OpenMRSEncounter encounter) {
        String encounterType = encounter.getEncounterType();
        logger.info(encounterType);
        if (encounterType.equalsIgnoreCase(DISCHARGE_EVENT)) {
            logger.info("Event Number A03- Discharge event");
        } else if (encounterType.equalsIgnoreCase("consultation")) {
            List<OpenMRSOrder> orders = encounter.getOrders();
            orders.forEach(openMRSOrder -> {
                if (openMRSOrder.getOrderType().equalsIgnoreCase("Lab Order")) {
                    logger.info("Event Number R01- Lab order event");

                }
                if (openMRSOrder.getOrderType().equalsIgnoreCase("Drug Order")) {
                    logger.info("Event Number O01- Medication event");

                }
                logger.info("-----Action-----" + openMRSOrder.getAction());
                logger.info("-----type-----" + openMRSOrder.getOrderType());
            });
        }
    }

    @Override
    public void cleanUp(Event event) {
    }
}
