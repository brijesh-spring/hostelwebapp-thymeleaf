package com.hostel.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hostel.commands.AdmissionCommand;
import com.hostel.domain.Admission;
import com.hostel.domain.RoomStatus;

@Component
public class AdmissionCommandToAdmission implements Converter<AdmissionCommand, Admission>{

	PaymentCommandToPayment toPayment;
	
	public AdmissionCommandToAdmission( PaymentCommandToPayment toPayment ) {
		super();
		this.toPayment = toPayment;
	}

	@Override
	public Admission convert(AdmissionCommand source) {

		Admission object = new Admission();
		
		object.setAcRoom( source.getRoomStatus().equals( RoomStatus.AC_ROOM ) ? true : false );
		object.setAdmissionDate( source.getAdmissionDate() );
		object.setHostelFeePaid( source.getHostelFeePaid() );
		object.setId( source.getId() );
		object.setRegistrationFee( source.getRegFeeStatus() );
		object.setRoomNo( source.getRoomNo() );
		object.setSecurityFee( source.getSecurityFeeStatus() );
		object.setHostelFeeStatus( source.getHostelFeeStatus() );
		
		if( source.getRoomStatus().equals( RoomStatus.AC_ROOM ) ) object.setAcRoom( true ); else object.setAcRoom( false );
		
		if( source.getPayments() != null )
		{
			source.getPayments().forEach( payment -> object.getPaymentDetails().add( toPayment.convert( payment ) ) );
		}
		
		return object;
	}

}
