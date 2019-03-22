package com.hostel.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity( name = "student" )
public class Student extends Auditable<String> {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;
	
	@Column( name="first_name" )
	private String firstName;
	
	@Column( name="last_name" )
	private String lastName;
	
	@Column( name="father_name" )
	private String fatherName;
	
	@Column( name="mother_name" )
	private String motherName;
	
	@Column( name="father_occupation" )
	private String fatherOccupation;
	
	@Column( name="mother_occupation" )
	private String motherOccupation;
	
	private LocalDate dob;
	
	@Column( name="image" )
	@Lob
	private byte[] image;

	private boolean active;
	
	@Column( name="status_date" )
	private LocalDateTime statusDate;
	
	@Enumerated ( value = EnumType.STRING )
	@Column( name = "status" )
	private StudentStatus status = StudentStatus.Pending;
	
	@OneToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	private StudentCollege studentCollege;
	
	@OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "student" )
	private Set<Address> addresses = new HashSet<>(); 
	
	@OneToOne ( mappedBy = "student" , cascade = CascadeType.ALL )
	private Contact contact;
	
	@OneToMany( mappedBy="student" , cascade = CascadeType.ALL )
	private Set<Reference> reference = new HashSet<>();
	
	@OneToMany( mappedBy="student" , cascade = CascadeType.ALL )
	private Set<EmergencyContact> emergencyContacts = new HashSet<>();
	
	@OneToMany ( mappedBy="student", cascade = CascadeType.ALL  )
	private Set<Admission> admissions = new HashSet<>();
	
	@Override
	public String toString()
	{
		return "[ ID: "+ id + ", Name: "+ firstName + " " + lastName +" ]";
	}
}
