package com.hostel.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hostel.commands.PaymentCommand;
import com.hostel.domain.PaymentDetails;

@Component
public class PaymentToPaymentCommand implements Converter<PaymentDetails, PaymentCommand>{

	@Override
	public PaymentCommand convert(PaymentDetails source) {

		PaymentCommand object = new PaymentCommand();
		object.setAmount( source.getAmount() );
		object.setFeeType( source.getFeeType() );
		object.setId( source.getId() );
		object.setPaymentDate( source.getPaymentDate() );
		object.setPaymentRefNumber( source.getPaymentRefNumber() );
		object.setPaymentType( source.getPaymentType() );
		
		return object;
	}

}
