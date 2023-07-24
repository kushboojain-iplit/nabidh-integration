package org.bahmni.module.feedintegration.repository;

import org.bahmni.module.feedintegration.model.FailedEvent;
import org.bahmni.module.feedintegration.model.OpenMRSPatientFeedForNabidhJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FailedEventRepository extends JpaRepository<FailedEvent, Integer> {
    List<FailedEvent> findAll();
}