package com.hostel.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hostel.commands.ReferencesCommand;
import com.hostel.domain.Reference;

@Component
public class RefereceCommandToReference implements Converter<ReferencesCommand, Reference> {

	 public RefereceCommandToReference(StudentCommandToStudent commandToStudent) {
		super(); 
	 }

	@Override
	public Reference convert(ReferencesCommand source) {
		Reference object = new Reference();
		object.setCellPhone( source.getCellPhone() );
		object.setId( source.getId() );
		object.setLandline( source.getLandline() );
		object.setName( source.getName() );
		object.setRelationship( source.getRelationship() );
		object.setStudent( source.getStudent() );
		return object;
	}

}
