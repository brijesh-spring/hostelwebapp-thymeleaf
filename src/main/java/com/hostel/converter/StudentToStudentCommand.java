package com.hostel.converter;

import java.util.Optional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hostel.commands.AddressCommand;
import com.hostel.commands.StudentCommand;
import com.hostel.domain.Address;
import com.hostel.domain.AddressType;
import com.hostel.domain.Admission;
import com.hostel.domain.Student;

@Component
public class StudentToStudentCommand implements Converter<Student, StudentCommand> {

	private ContactToContactCommand contactToCommand;
	private AddressToAddressCommand addressToCommand;
	private ReferenceToReferenceCommand referenceToCommand;
	private AdmissionToAdmissionCommand admissionToCommand;
	private EmergencyToEmergencyCommand emergencyToCommand;
	
	public StudentToStudentCommand(ContactToContactCommand contactToCommand, 
			AddressToAddressCommand addressToCommand,
			ReferenceToReferenceCommand referenceToCommand,
			AdmissionToAdmissionCommand admissionToCommand,
			EmergencyToEmergencyCommand emergencyToCommand ) {
		super();
		this.contactToCommand = contactToCommand;
		this.addressToCommand = addressToCommand;
		this.referenceToCommand = referenceToCommand;
		this.admissionToCommand = admissionToCommand;
		this.emergencyToCommand = emergencyToCommand;
	}

	@Override
	public StudentCommand convert(Student source) {
		
		StudentCommand command = new StudentCommand();
		
		command.setActive( source.isActive() );
		command.setDob( source.getDob() );
		command.setFatherName( source.getFatherName() );
		command.setFatherOccupation( source.getFatherOccupation() );
		command.setFirstName( source.getFirstName() );
		command.setLastName( source.getLastName() );
		command.setMotherName( source.getMotherName() );
		command.setMotherOccupation( source.getMotherOccupation() );
		command.setId( source.getId() );
		command.setImage( source.getImage() );
		command.setStatus( source.getStatus() );
		command.setStatusDate( source.getStatusDate() );
		
		if( source.getContact() != null ) command.setContact( contactToCommand.convert( source.getContact() ) );
		
		if( source.getAddresses() != null && !source.getAddresses().isEmpty() )
		{
			command.setHomeAddress( getAddress( source, AddressType.HOME ) );
			command.setFatherAddress( getAddress( source, AddressType.FATHER_OFFICE ) );
			command.setMotherAddress( getAddress( source, AddressType.MOTHER_OFFICE ) );
		}
		
		if( source.getReference() != null )
		{
			source.getReference().forEach( reference -> {
				command.getReferences().add( referenceToCommand.convert( reference ) );
			});
		}
		
		if( source.getEmergencyContacts() != null )
		{
			source.getEmergencyContacts().forEach( contact -> command.getEmergencyContacts().add( emergencyToCommand.convert( contact ) ));
		}
		
		if( source.getAdmissions() != null )
		{
			// Check for Active Admission record only.
			Optional<Admission> temp = source.getAdmissions().stream().filter( admission -> admission.isActive() ).findFirst();
			
			if( temp.isPresent() ) command.setAdmission( admissionToCommand.convert( temp.get() ) );
		}
		
		return command;
	}
	
	private AddressCommand getAddress( Student source, AddressType type )
	{
		Optional<AddressCommand> optionalAddress =
				source.getAddresses().stream()
						.filter( address -> address.getType().equals( type ) )
						.map( addressToCommand :: convert )
						.findFirst();
		
		if( optionalAddress.isPresent() ) return optionalAddress.get();
		else return null;
	}

}
