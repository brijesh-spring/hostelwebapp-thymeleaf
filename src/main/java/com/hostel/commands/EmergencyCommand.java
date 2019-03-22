package com.hostel.commands;

import com.hostel.domain.Relationship;
import com.hostel.domain.Student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmergencyCommand {

	private Long id;
	private String name;
	private long cellPhone;
	private long landline;
	private Relationship relationship; 
	private Student student;
}
