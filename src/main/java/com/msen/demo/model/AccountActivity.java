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

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class AccountActivity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_id")
	@ApiModelProperty(hidden = true)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "id", nullable = false)
	@JsonBackReference
	@ApiModelProperty(hidden = true)
	private Customer customerId;
	@ApiModelProperty(hidden = true)
	private Instant timeOfActivity;
	@ApiModelProperty(hidden = true)
	private Double price;
	@ApiModelProperty(hidden = true)
	private MoneyProcess process;

}
