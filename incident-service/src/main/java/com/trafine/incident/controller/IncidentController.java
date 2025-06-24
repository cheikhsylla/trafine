package com.trafine.incident.controller;
import com.trafine.incident.entity.Incident;
import com.trafine.incident.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incidents")
public class IncidentController {
  @Autowired private IncidentService service;

  @PostMapping
  public Incident create(@RequestBody Incident incident) {
    return service.create(incident);
  }

  @GetMapping
  public List<Incident> getAll(@RequestParam(required = false) String type) {
    return (type != null) ? service.listByType(type) : service.listAll();
  }

  @PatchMapping("/{id}/validate")
  public Incident validate(@PathVariable Long id) {
    return service.validate(id);
  }
}