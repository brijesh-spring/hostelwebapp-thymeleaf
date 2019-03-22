package com.hostel.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hostel.commands.PaymentCommand;
import com.hostel.domain.PaymentDetails;

@Component
public class PaymentCommandToPayment implements Converter<PaymentCommand, PaymentDetails>{

	@Override
	public PaymentDetails convert(PaymentCommand source) {

		PaymentDetails object = new PaymentDetails();
		object.setAmount( source.getAmount() );
		object.setFeeType( source.getFeeType() );
		object.setId( source.getId() );
		object.setPaymentDate( source.getPaymentDate() );
		object.setPaymentRefNumber( source.getPaymentRefNumber() );
		object.setPaymentType( source.getPaymentType() );
		
		return object;
		
	}

}
