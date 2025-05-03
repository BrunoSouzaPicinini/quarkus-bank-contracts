package com.bspicinini.repository;

import com.bspicinini.repository.entity.CustomerOffer;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerOfferRepository implements PanacheRepository<CustomerOffer>{

}
