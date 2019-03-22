package com.hostel.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hostel.commands.AdmissionCommand;
import com.hostel.domain.Admission;
import com.hostel.domain.RoomStatus;

@Component
public class AdmissionToAdmissionCommand implements Converter<Admission, AdmissionCommand>{

	private PaymentToPaymentCommand toPaymentCommand;
	
	public AdmissionToAdmissionCommand( PaymentToPaymentCommand toPaymentCommand ) {
		super();
		this.toPaymentCommand = toPaymentCommand;
	}

	@Override
	public AdmissionCommand convert(Admission source) {

		AdmissionCommand object = new AdmissionCommand();
		
		object.setAcRoom( source.isAcRoom() );
		object.setAdmissionDate( source.getAdmissionDate() );
		object.setHostelFeePaid( source.getHostelFeePaid() );
		object.setId( source.getId() );
		object.setRegFeeStatus( source.getRegistrationFee() );
		object.setRoomNo( source.getRoomNo() );
		object.setSecurityFeeStatus( source.getSecurityFee() );
		object.setStudentId( source.getStudent().getId() );
		object.setHostelFeeStatus( source.getHostelFeeStatus() );
		
		if( source.isAcRoom() ) object.setRoomStatus( RoomStatus.AC_ROOM ); else object.setRoomStatus( RoomStatus.NON_AC_ROOM );
		
		object.setActive( source.getStudent().isActive() );
		
		if( source.getPaymentDetails() != null )
		{
			source.getPaymentDetails().forEach( payment -> object.getPayments().add( toPaymentCommand.convert( payment ) ) );
		}
		
		return object;
	}

}
