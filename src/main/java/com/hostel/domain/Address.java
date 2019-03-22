package com.hostel.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity( name = "address" )
public class Address extends Auditable<String> {

	@Id
	@GeneratedValue ( strategy = GenerationType.AUTO )
	private Long id;
	
	@Column( name = "society_name" )
	private String societyName;
	private String addressLine1;
	private String addressLine2;
	
	@Column( name = "house_no" )
	private String houseNo;
	
	@Column( name = "plot_no" )
	private String plotNo;
	
	private String city;
	private String state;
	private String zipcode;
	private String county = "India";
	private String landmark;
	
	@Enumerated ( value = EnumType.STRING )
	private AddressType type;
	
	@ManyToOne
	private Student student;
	
	@Override
	public String toString()
	{
		return "[ ID: "+ id + ", Address: "+ societyName + " " + city + " " + state + " " + zipcode + " ]";
	}
}
