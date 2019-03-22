package com.hostel.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hostel.commands.ContactCommand;
import com.hostel.domain.Contact;

@Component
public class ContactToContactCommand implements Converter<Contact, ContactCommand> {

	@Override
	public ContactCommand convert(Contact source) {

		ContactCommand result = new ContactCommand();
		
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
