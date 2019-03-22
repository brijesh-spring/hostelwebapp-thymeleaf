package com.hostel.commands;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentsCommand {

	private Set<PaymentCommand> payments = new HashSet<>();
}
