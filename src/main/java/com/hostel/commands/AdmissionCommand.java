package com.hostel.commands;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.hostel.domain.FeeStatus;
import com.hostel.domain.PaymentType;
import com.hostel.domain.RoomStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdmissionCommand {

	private Long id;
	private FeeStatus regFeeStatus = FeeStatus.NOT_PAID;
	private FeeStatus securityFeeStatus = FeeStatus.NOT_PAID;
	private boolean acRoom;
	private RoomStatus roomStatus = RoomStatus.SELECT;
	private LocalDate admissionDate = LocalDate.now();
	private double hostelFeePaid;
	private FeeStatus hostelFeeStatus = FeeStatus.NOT_PAID;
	private int roomNo;
	private Long studentId;
	private boolean active;
	private Set<PaymentCommand> payments = new HashSet<>();
	private Set<PaymentCommand> existingPayments = new HashSet<>();
	private PaymentType regPaymentType;
	private String regPaymentRefNumber;
	private PaymentType secPaymentType;
	private String secPaymentRefNumber;
	private PaymentType hostelPaymentType;
	private String hostelPaymentRefNumber;
	private double hostelFeeAmount;
	private boolean renewed;
}
