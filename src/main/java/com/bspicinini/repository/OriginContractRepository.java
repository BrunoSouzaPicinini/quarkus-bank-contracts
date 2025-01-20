package com.bspicinini.repository;

import com.bspicinini.repository.entity.OriginContract;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OriginContractRepository implements PanacheRepository<OriginContract> {
}