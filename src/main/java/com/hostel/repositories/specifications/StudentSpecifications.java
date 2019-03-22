package com.hostel.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;


import com.hostel.domain.Student;

final public class StudentSpecifications {

	private StudentSpecifications() {
		
	}
	
	public static Specification<Student> searchByFirstName( String firstName )
	{
		return (root, query, cb ) -> {
			return cb.like( root.get( "firstName") , "%"+firstName+"%" );
		};
	}
	
	public static Specification<Student> searchByLastName( String lastName )
	{
		return ( root, query, cb ) -> {
			return cb.like( root.get( "lastName" ) , "%"+lastName+"%" );
		};
	}
	
	public static Specification<Student> searchByFatherName( String fatherName )
	{
		return ( root, query, cb ) -> {
			return cb.like( root.get( "fatherName" ) , "%"+fatherName+"%" );
		};
	}
	
	public static Specification<Student> searchByMotherName( String motherName )
	{
		return ( root, query, cb ) -> {
			return cb.like( root.get( "motherName" ) , "%"+motherName+"%" );
		};
	}
	
	public static Specification<Student> searchByPhone( String phone )
	{
		return ( root, query, cb ) -> {
			return cb.like( root.get( "contact").get( "studentPhone" ) , "%"+phone+"%" );
		};
	}
}
