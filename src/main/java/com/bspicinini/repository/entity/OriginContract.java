package com.bspicinini.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class OriginContract {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "origin_contract_seq")
    @SequenceGenerator(name = "origin_contract_seq", sequenceName = "origin_contract_sequence", allocationSize = 1)
    private Long id;

    private String originName;

    @OneToMany(mappedBy = "originContract")
    private List<Contract> contracts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    
}