package com.hostel.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Admission extends Auditable<String> {

	@Id
	@GeneratedValue
	private Long id;
	
	@Enumerated ( value = EnumType.STRING )
	@Column ( name = "registration_fee" )
	private FeeStatus registrationFee = FeeStatus.NOT_PAID;
	
	@Enumerated ( value = EnumType.STRING )
	@Column( name = "security_fee" )
	private FeeStatus securityFee = FeeStatus.NOT_PAID;
	
	@Enumerated ( value = EnumType.STRING )
	@Column( name = "hostelFeeStatus" )
	private FeeStatus hostelFeeStatus = FeeStatus.NOT_PAID;
	
	@Column ( name = "ac_room" )
	private boolean acRoom;
	
	@Column ( name = "admission_date" )
	private LocalDate admissionDate;
	
	@Column ( name = "hostel_fee_paid" )
	private double hostelFeePaid;
	
	@Column( name = "room_no" )
	private int roomNo;
	
	private boolean active = true;
	
	@ManyToOne
	private Student student;
	
	@OneToMany ( mappedBy = "admission" , cascade = CascadeType.ALL)
	private Set<PaymentDetails> paymentDetails = new HashSet<>();
}
