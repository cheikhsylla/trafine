package com.trafine.incident.repository;
import com.trafine.incident.entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
  List<Incident> findByType(String type);
}