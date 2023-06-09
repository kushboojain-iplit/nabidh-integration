package org.bahmni.module.feedintegration.atomfeed.jobs;

import org.bahmni.module.feedintegration.atomfeed.client.AtomFeedClientFactory;
import org.bahmni.module.feedintegration.atomfeed.worker.EncounterFeedWorker;
import org.ict4h.atomfeed.client.service.FeedClient;
import org.quartz.DisallowConcurrentExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@DisallowConcurrentExecution
@Component("openMRSEncounterFailedFeedJob")
@ConditionalOnExpression("'${enable.scheduling}'=='true'")
@PropertySource("classpath:atomfeed.properties")
public class EncounterFailedFeedJob implements FeedJob {

    @Value("${openmrs.encounter.feed.uri}")
    private String ENCOUNTER_FEED_URI;
    private final Logger logger = LoggerFactory.getLogger(EncounterFailedFeedJob.class);
    private FeedClient atomFeedClient;
    private EncounterFeedWorker encounterFeedWorker;
    private AtomFeedClientFactory atomFeedClientFactory;

    @Autowired
    public EncounterFailedFeedJob(EncounterFeedWorker encounterFeedWorker, AtomFeedClientFactory atomFeedClientFactory) {
        this.encounterFeedWorker = encounterFeedWorker;
        this.atomFeedClientFactory = atomFeedClientFactory;
    }

    public EncounterFailedFeedJob() {
    }

    @Override
    public void process() {
        if(atomFeedClient == null){
            atomFeedClient = atomFeedClientFactory.get(ENCOUNTER_FEED_URI, encounterFeedWorker);
        }
        atomFeedClient.processFailedEvents();
    }
}
