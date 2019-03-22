package com.hostel.commands;

import java.time.LocalDateTime;

import com.hostel.domain.FeeType;
import com.hostel.domain.PaymentType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentCommand {

	private Long id;
	private double amount;
	private FeeType feeType;
	private PaymentType paymentType;
	private LocalDateTime paymentDate;
	private String paymentRefNumber;
	
}
