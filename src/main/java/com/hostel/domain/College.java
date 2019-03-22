package com.hostel.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity( name = "college" )
public class College extends Auditable<String> {

	@Id
	@GeneratedValue ( strategy = GenerationType.AUTO )
	private Long id;
	private String name;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String county = "India";
	private long zipcode;
	
 
}
