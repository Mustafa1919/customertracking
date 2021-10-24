package com.msen.demo.model;

import java.time.Instant;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Builder
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ApiModel
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(hidden = true)
	private Long id;
	@ApiModelProperty(hidden = true)
	private String name;
	@ApiModelProperty(hidden = true)
	private String lastName;
	@ApiModelProperty(hidden = true)
	private Instant createDate;
	@ApiModelProperty(hidden = true)
	private Double balance;
	@ApiModelProperty(hidden = true)
	private String phoneNumber;
	@OneToMany(mappedBy = "customerId", fetch = FetchType.LAZY)
	@JsonManagedReference
	@ApiModelProperty(hidden = true)
	private List<AccountActivity> accountActivity;

}
