package com.msen.demo.model;

import java.time.Instant;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Table(name = "customer-table")
@Data
@Builder
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String lastName;
	private Instant createDate;
	private Double balance;
	private String phoneNumber;
	@OneToMany(mappedBy = "customerId", fetch = FetchType.LAZY)
	private Set<AccountActivity> accountActivity;

}
