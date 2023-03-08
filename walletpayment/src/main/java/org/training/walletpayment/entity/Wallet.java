package org.training.walletpayment.entity;

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
	private long walletId;

	private double balance;

	private LocalDate validFromDate;

	private LocalDate validTill;
}