package org.bahmni.module.feedintegration.atomfeed.contract.encounter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenMRSEncounter {
    private String encounterUuid;
    private String patientUuid;
    private String encounterType;
    private List<OpenMRSOrder> orders = new ArrayList<OpenMRSOrder>();
    private List<OpenMRSProvider> providers = new ArrayList<OpenMRSProvider>();
    private List<OpenMRSObservation> observations = new ArrayList<>();

    public OpenMRSEncounter() {
    }

    public OpenMRSEncounter(String encounterUuid, String patientUuid, List<OpenMRSOrder> orders, List<OpenMRSProvider> providers) {

        this.encounterUuid = encounterUuid;
        this.orders = orders;
        this.patientUuid = patientUuid;
        this.providers = providers;
    }

    public void setEncounterUuid(String encounterUuid) {
        this.encounterUuid = encounterUuid;
    }

    public String getEncounterUuid() {
        return encounterUuid;
    }

    public List<OpenMRSOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<OpenMRSOrder> orders) {
        this.orders = orders;
    }

    public String getPatientUuid() {
        return patientUuid;
    }

    public void setPatientUuid(String patientUuid) {
        this.patientUuid = patientUuid;
    }

    public List<OpenMRSProvider> getProviders() {
        return providers;
    }

    public void setProviders(List<OpenMRSProvider> providers) {
        this.providers = providers;
    }

    public void addTestOrder(OpenMRSOrder order) {
        orders.add(order);
    }

    public boolean hasOrders() {
        return getOrders().size() > 0;
    }

    public String getEncounterType() {
        return encounterType;
    }

    public void setEncounterType(String encounterType) {
        this.encounterType = encounterType;
    }

    public List<OpenMRSObservation> getObservations() {
        return observations;
    }

    public void setObservations(List<OpenMRSObservation> observations) {
        this.observations = observations;
    }
}
