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
@Entity( name = "student_college" )
public class StudentCollege extends Auditable<String> {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;
	
	@Column( name = "role_number" )
	private String roleNumber;
	
	private int semester;
	
	@ Column( name = "admission_year" )
	private int admissionYear;
	
	@Column( name="subject_major" )
	private String subjectMajor;
	
	@OneToOne
	private Student student;
	
	@OneToOne
	private College college;
	
}
