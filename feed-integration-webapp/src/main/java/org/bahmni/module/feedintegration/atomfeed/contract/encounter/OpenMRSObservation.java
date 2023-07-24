package org.bahmni.module.feedintegration.atomfeed.contract.encounter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenMRSObservation {

    private String conceptNameToDisplay;

    public String getConceptNameToDisplay() {
        return conceptNameToDisplay;
    }

    public void setConceptNameToDisplay(String conceptNameToDisplay) {
        this.conceptNameToDisplay = conceptNameToDisplay;
    }
}
