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
@Entity( name="reference" )
public class Reference extends Auditable<String> {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;
	private String name;
	
	@Column( name="cell_phone" )
	private long cellPhone;
	private long landline;
	
	@Enumerated ( value = EnumType.STRING )
	private Relationship relationship; 
	
	@ManyToOne
	private Student student;
	
	@Override
	public String toString()
	{
		return "[Reference ID: "+ id + ", Name : "+ name + ", Cell Phone : " + cellPhone + " ]";
	}
}
