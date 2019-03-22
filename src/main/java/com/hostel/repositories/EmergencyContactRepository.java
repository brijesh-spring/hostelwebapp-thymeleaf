package com.hostel.repositories;

import org.springframework.data.repository.CrudRepository;

import com.hostel.domain.EmergencyContact;

public interface EmergencyContactRepository extends CrudRepository<EmergencyContact, Long>{

}
