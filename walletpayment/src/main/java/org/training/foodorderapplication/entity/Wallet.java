package org.training.foodorderapplication.entity;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Wallet {

	@Id
	@GeneratedValue
	@Column(columnDefinition = "BINARY(8)")
	private UUID walletId;

	private double balance;

	private LocalDate validFromDate;

	private LocalDate validTill;
}
