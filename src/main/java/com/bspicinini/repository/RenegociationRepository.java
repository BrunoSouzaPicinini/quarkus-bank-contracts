package com.bspicinini.repository;

import com.bspicinini.repository.entity.Renegociation;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RenegociationRepository implements PanacheRepository<Renegociation> {
}
