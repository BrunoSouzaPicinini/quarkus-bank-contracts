package com.bspicinini.repository;

import java.util.List;

import com.bspicinini.repository.entity.Contract;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContractRepository implements PanacheRepository<Contract> {

    public List<Contract> findByIdIn(List<Long> list) {
        return find("id in ?1", list).list();
    }

}