package com.bspicinini.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity(name = "generate_contract_dlts")
public class GenerateContractDlt {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generate_contract_dlt_seq")
	@SequenceGenerator(name = "generate_contract_dlt_seq", sequenceName = "generate_contract_dlt_sequence", allocationSize = 1)
	private Long id;
	@Column(name = "body_message")
	private String bodyMessage;
	@Column(name = "header_message")
	private String headerMessage;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBodyMessage() {
		return bodyMessage;
	}
	public void setBodyMessage(String bodyMessage) {
		this.bodyMessage = bodyMessage;
	}
	public String getHeaderMessage() {
		return headerMessage;
	}
	public void setHeaderMessage(String headerMessage) {
		this.headerMessage = headerMessage;
	}

}
