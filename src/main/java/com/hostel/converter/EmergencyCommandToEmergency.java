package com.hostel.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hostel.commands.EmergencyCommand;
import com.hostel.domain.EmergencyContact;

@Component
public class EmergencyCommandToEmergency implements Converter<EmergencyCommand, EmergencyContact> {

	@Override
	public EmergencyContact convert(EmergencyCommand source) {
		
		EmergencyContact object = new EmergencyContact();
		object.setCellPhone( source.getCellPhone() );
		object.setId( source.getId() );
		object.setLandline( source.getLandline() );
		object.setName( source.getName() );
		object.setRelationship( source.getRelationship() );
		object.setStudent( source.getStudent() );
		return object;
	}

}
