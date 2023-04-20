package org.bahmni.module.feedintegration.atomfeed.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bahmni.module.feedintegration.atomfeed.contract.encounter.OpenMRSEncounter;

import java.io.IOException;

public class OpenMRSEncounterMapper {
    private ObjectMapper objectMapper;

    public OpenMRSEncounterMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public OpenMRSEncounter map(String encounterJSON) throws IOException {
        return objectMapper.readValue(encounterJSON, OpenMRSEncounter.class);
    }
}
