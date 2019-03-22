package com.hostel.commands;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.hostel.domain.StudentStatus;
import com.hostel.validators.GroupValidator;
import com.hostel.validators.ParentValidator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentCommand {

	private Long id;
	@NotEmpty( groups= {ParentValidator.class, GroupValidator.class } )
	@Size( min=2, max=50 , groups= {ParentValidator.class, GroupValidator.class } )
	private String firstName;
	
	@NotEmpty( groups= {ParentValidator.class, GroupValidator.class } )
	@Size( min=2, max=50, groups= {ParentValidator.class, GroupValidator.class } )
	private String lastName;
	
	@NotEmpty( groups= {ParentValidator.class, GroupValidator.class } )
	@Size( min=2, max=50, groups= {ParentValidator.class, GroupValidator.class } )
	private String fatherName;
	
	@NotEmpty( groups= {ParentValidator.class, GroupValidator.class } )
	@Size( min=2, max=50, groups= {ParentValidator.class, GroupValidator.class } )
	private String motherName;
	
	@Size( min=0, max=50, groups= {ParentValidator.class, GroupValidator.class } )
	private String fatherOccupation;
	
	@Size( min=0, max=50, groups= {ParentValidator.class, GroupValidator.class } )
	private String motherOccupation;
	
	private LocalDate dob;
	private boolean active;
	private byte[] image;
	
	private LocalDateTime statusDate;
	private StudentStatus status = StudentStatus.Pending;
	
	@Valid
	private ContactCommand contact;
	
	@Valid
	private AddressCommand homeAddress; 
	
	 
	private AddressCommand fatherAddress;
	
	 
	private AddressCommand motherAddress;
	
	private Set<ReferencesCommand> references = new HashSet<>();
	
	private Set<EmergencyCommand> emergencyContacts = new HashSet<>();
	
	private AdmissionCommand admission;
}
