package com.hostel.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hostel.commands.ContactCommand;
import com.hostel.domain.Contact;

@Component
public class ContactCommandToContact implements Converter<ContactCommand, Contact>{

	@Override
	public Contact convert(ContactCommand source) {
		
		Contact result = new Contact();
		
		result.setFatherEmail( source.getFatherEmail() );
		result.setFatherPhone( source.getFatherPhone() );
		result.setHomePhone( source.getHomePhone() );
		result.setMotherEmail( source.getMotherEmail() );
		result.setMotherPhone( source.getMotherPhone() );
		result.setStudentEmail( source.getStudentEmail() );
		result.setStudentPhone( source.getStudentPhone() );
		result.setId( source.getId() );
		result.setStudent( source.getStudent() );
		
		return result;
		
	}

	
}
