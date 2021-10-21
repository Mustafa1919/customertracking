package com.msen.demo.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Table (name = "activity-table")
@Data
@Builder
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class AccountActivity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account-id")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "id", nullable = false)
	private Customer customerId;
	private Instant timeOfActivity;
	private Double price;
	private MoneyProcess process;

}
