package org.training.walletpayment.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Wallet {

	@Id
	private long walletId;

	private double balance;

	private LocalDate validFromDate;

	private LocalDate validTill;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
}
