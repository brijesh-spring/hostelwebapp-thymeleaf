package com.hostel.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hostel.Utility;
import com.hostel.commands.StudentCommand;
import com.hostel.domain.Address;
import com.hostel.domain.AddressType;
import com.hostel.domain.Student;

@Component
public class StudentCommandToStudent implements Converter<StudentCommand, Student> {

	private ContactCommandToContact commandToContact;
	private AddressCommandToAddress commandToAddress;
	private RefereceCommandToReference commandToReference;
	private AdmissionCommandToAdmission commandToAdmission;
	private EmergencyCommandToEmergency commandToEmergency;
	
	public StudentCommandToStudent(ContactCommandToContact commandToContact, AddressCommandToAddress commandToAddress,
			AdmissionCommandToAdmission commandToAdmission, EmergencyCommandToEmergency commandToEmergency ) {
		super();
		this.commandToContact = commandToContact;
		this.commandToAddress = commandToAddress;
		this.commandToAdmission = commandToAdmission;
		this.commandToEmergency = commandToEmergency;
	}

	@Override
	public Student convert(StudentCommand source) {
		
		Student student = new Student();
		
		student.setActive( source.isActive() );
		student.setDob( source.getDob() );
		student.setFatherName( source.getFatherName() );
		student.setFatherOccupation( source.getFatherOccupation() );
		student.setFirstName( source.getFirstName() );
		student.setLastName( source.getLastName() );
		student.setMotherName( source.getMotherName() );
		student.setMotherOccupation( source.getMotherOccupation() );
		student.setId( source.getId() );
		student.setImage( source.getImage() );
		student.setStatus( source.getStatus() );
		student.setStatusDate( source.getStatusDate() );
		
		if( source.getContact() != null ) 		source.getContact().setStudent( student );
		student.setContact( source.getContact() != null ? commandToContact.convert( source.getContact() ) : null );
		
		if( source.getHomeAddress() != null && !Utility.isEmpty( source.getHomeAddress().getCity() ) )
		{
			Address address = commandToAddress.convert( source.getHomeAddress() );
			address.setType( AddressType.HOME );
			address.setStudent( student );
			student.getAddresses().add( address );
		}
		
		if( source.getFatherAddress() != null && !Utility.isEmpty( source.getFatherAddress().getCity() ) )
		{
			Address address = commandToAddress.convert( source.getFatherAddress() );
			address.setType( AddressType.FATHER_OFFICE );
			address.setStudent( student );
			student.getAddresses().add( address );
		}
		
		if( source.getMotherAddress() != null && !Utility.isEmpty( source.getMotherAddress().getCity() ) )
		{
			Address address = commandToAddress.convert( source.getMotherAddress() );
			address.setType( AddressType.MOTHER_OFFICE );
			address.setStudent( student );
			student.getAddresses().add( address );
		}
		
		if( source.getReferences() != null )
		{
			source.getReferences().forEach( reference -> commandToReference.convert( reference ) );
		}
		
		if( source.getEmergencyContacts() != null )
		{
			source.getEmergencyContacts().forEach( contact -> commandToEmergency.convert( contact ) );
		}
		
		if( source.getAdmission() != null )
		{
			student.getAdmissions().add( commandToAdmission.convert( source.getAdmission() ) );
		}
		
		return student;
	}

	
}
