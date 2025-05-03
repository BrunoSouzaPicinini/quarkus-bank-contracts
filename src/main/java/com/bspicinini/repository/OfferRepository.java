package com.bspicinini.repository;

import com.bspicinini.repository.entity.Offer;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OfferRepository implements PanacheRepository<Offer>{
}
