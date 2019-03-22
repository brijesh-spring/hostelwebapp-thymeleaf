package com.hostel.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class PaymentDetails extends Auditable<String> {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;
	private double amount;
	
	@Enumerated ( value = EnumType.STRING )
	@Column( name="fee_type")
	private FeeType feeType;
	
	@Enumerated ( value = EnumType.STRING )
	@Column( name="payment_type")
	private PaymentType paymentType;
	
	@Column( name="payment_date" )
	private LocalDateTime paymentDate;
	
	@Column( name="payment_ref_number" )
	private String paymentRefNumber;
	
	@ManyToOne
	private Admission admission;
}
