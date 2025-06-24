package com.trafine.incident.service;
import com.trafine.incident.entity.Incident;
import com.trafine.incident.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentService {
  @Autowired private IncidentRepository repo;

  public Incident create(Incident incident) {
    return repo.save(incident);
  }

  public List<Incident> listAll() {
    return repo.findAll();
  }

  public List<Incident> listByType(String type) {
    return repo.findByType(type);
  }

  public Incident validate(Long id) {
    Incident i = repo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    i.setValidated(true);
    return repo.save(i);
  }
}