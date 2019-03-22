package com.hostel.commands;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.hostel.domain.AddressType;
import com.hostel.validators.GroupContactValidator;
import com.hostel.validators.GroupValidator;
import com.hostel.validators.ParentValidator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressCommand {

	private Long id;
	
	@NotEmpty( groups= { GroupValidator.class } )
	@Size( min=3, max=150, groups= {GroupValidator.class } )
	private String societyName;
	
	@NotEmpty( groups= { GroupValidator.class } )
	@Size( min=3, max=150, groups= {GroupValidator.class } )
	private String addressLine1;
	
	private String addressLine2;
	
	@NotEmpty( groups= { GroupValidator.class } )
	@Size( min=1, max=10, groups= {GroupValidator.class } )
	private String houseNo;
	
	private String plotNo;
	
	@NotEmpty( groups= { GroupValidator.class } )
	@Size( min=2, max=50, groups= {GroupValidator.class } )
	private String city;
		
	@NotEmpty( groups= { GroupValidator.class } )
	@Size( min=3, max=50, groups= {GroupValidator.class } )
	private String state;
	
	@NotEmpty( groups= { GroupValidator.class } )
	@Digits( integer=6, fraction=0 ,  groups= { GroupValidator.class, GroupContactValidator.class })
	@Size( min=6, max=6, groups= {GroupValidator.class } )
	private String zipcode;
	
	
	private String county = "India";
	
	@Size( min=0, max=50, groups= {GroupValidator.class } )
	private String landmark;
	private AddressType type;
}
