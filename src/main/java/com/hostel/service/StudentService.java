package com.hostel.service;

import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.hostel.commands.AdmissionCommand;
import com.hostel.commands.EmergencyCommand;
import com.hostel.commands.ReferencesCommand;
import com.hostel.commands.SearchCommand;
import com.hostel.commands.StudentCommand;
import com.hostel.exception.NotFoundException;

public interface StudentService {

	StudentCommand saveStudentCommand(StudentCommand command);
	StudentCommand getStudent( Long id ) throws NotFoundException;
	ReferencesCommand saveReferenceCommand( ReferencesCommand command );
	ReferencesCommand getReference( Long id ) throws NotFoundException;
	List<StudentCommand> getStudents( SearchCommand searchCommand );
	void saveStudentImage( Long studentId, MultipartFile file );
	AdmissionCommand getAdmissionDetails( Long studentId );
	StudentCommand saveAdmissionDetails( AdmissionCommand command );
	EmergencyCommand saveEmergencyCommand( EmergencyCommand command );
	EmergencyCommand getEmergenceContact( Long id ) throws NotFoundException;
	public StudentCommand renewAdmissionDetails( Long studentId );
	public StudentCommand releaseStudent( Long studentId );
}
