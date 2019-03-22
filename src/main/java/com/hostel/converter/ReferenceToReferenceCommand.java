package com.hostel.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hostel.commands.ReferencesCommand;
import com.hostel.domain.Reference;

@Component
public class ReferenceToReferenceCommand implements Converter<Reference, ReferencesCommand>{

	//private StudentToStudentCommand studentToCommand;
	
	public ReferenceToReferenceCommand() {
		super();
		//this.studentToCommand = studentToCommand;
	}

	@Override
	public ReferencesCommand convert(Reference source) {

		ReferencesCommand object = new ReferencesCommand();
		object.setCellPhone( source.getCellPhone() );
		object.setId( source.getId() );
		object.setLandline( source.getLandline() );
		object.setName( source.getName() );
		object.setRelationship( source.getRelationship() );
		object.setStudent( source.getStudent() );
		return object;
	}

}
