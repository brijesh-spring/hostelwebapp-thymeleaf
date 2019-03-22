package com.hostel.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Contact extends Auditable<String> {

	@Id
	@GeneratedValue ( strategy = GenerationType.AUTO )
	private Long id;
	
	@Column( name="student_phone" )
	private String studentPhone;
	
	@Column( name="father_phone" )
	private String fatherPhone;
	
	@Column( name="mother_phone" )
	private String motherPhone;
	
	@Column( name="home_phone" )
	private String homePhone;
	
	@Column( name="student_email" )
	private String studentEmail;
	
	@Column( name="father_email" )
	private String fatherEmail;
	
	@Column( name="mother_email" )
	private String motherEmail;
	
	@OneToOne
	private Student student;
	
	@OneToOne
	private EmergencyContact emergencyContact;
	
	
	@Override
	public String toString()
	{
		return "[ ID: "+ id + ", Student Phone : "+ studentPhone + ", Student Email : " + studentEmail + " ]";
	}
}
