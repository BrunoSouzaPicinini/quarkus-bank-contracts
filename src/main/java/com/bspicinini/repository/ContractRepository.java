package com.bspicinini.repository;

import java.util.List;

import com.bspicinini.repository.entity.Contract;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.LockModeType;

@ApplicationScoped
public class ContractRepository implements PanacheRepository<Contract> {

    public List<Contract> findByIdIn(List<Long> list) {
        return find("id in ?1", list).withLock(LockModeType.PESSIMISTIC_WRITE).list();
    }

}