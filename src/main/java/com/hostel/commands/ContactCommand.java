package com.hostel.commands;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.hostel.domain.Student;
import com.hostel.validators.GroupContactValidator;
import com.hostel.validators.GroupFatherContactValidator;
import com.hostel.validators.GroupMotherContactValidator;
import com.hostel.validators.GroupValidator;
import com.hostel.validators.ParentValidator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContactCommand {

	private Long id;
	
	@NotEmpty( groups= { GroupValidator.class } )
	@Digits( integer=12, fraction=0, groups= { GroupValidator.class } )
	@Size( max=12, min=10, groups= { GroupValidator.class } )
	private String studentPhone;
	
	@Digits( integer=12, fraction=0, groups= { GroupValidator.class, GroupContactValidator.class  } )
	@Size( max=12, min=10, groups= { GroupValidator.class  } )
	private String fatherPhone;
	
	@Digits( integer=12, fraction=0, groups= { GroupValidator.class, GroupContactValidator.class } )
	@Size( max=12, min=10, groups= { GroupValidator.class } )
	private String motherPhone;
	
	@Digits( integer=12, fraction=0, groups= { GroupValidator.class } )
	@Size( max=12, min=10, groups= { GroupValidator.class } )
	private String homePhone;
	
	@NotEmpty( groups= { GroupValidator.class } )
	@Email( groups= { GroupValidator.class } )
	private String studentEmail;
	
	@Email( groups= { GroupValidator.class, GroupContactValidator.class } )
	private String fatherEmail;
	
	@Email( groups= { GroupValidator.class, GroupContactValidator.class } )
	private String motherEmail;
	
	private Student student;
}
