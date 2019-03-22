package com.hostel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hostel.commands.EmergencyCommand;
import com.hostel.commands.StudentCommand;
import com.hostel.domain.Student;
import com.hostel.exception.NotFoundException;
import com.hostel.service.StudentService;

@Controller
public class EmergencyContactController {

	StudentService service;

	public EmergencyContactController(StudentService service) {
		super();
		this.service = service;
	}
	
	@RequestMapping( "/student/{studentId}/emergency/{id}" )
	public String showEmergencyContactForm( Model model, @PathVariable Long studentId, @PathVariable Long id ) throws NotFoundException
	{
		StudentCommand student = service.getStudent( studentId );
		
		EmergencyCommand reference = null;
		
		try {
				reference = service.getEmergenceContact(id);
		}
		catch( NotFoundException nfe )
		{
			reference = new EmergencyCommand();
			Student temp = new Student();
			temp.setId( student.getId() );
			reference.setStudent( temp );
		}
		
		model.addAttribute( "emergencyContact" , reference );
		
		return "emergencycontactform";
	}
	
	@RequestMapping( "/emergency" )
	public String saveOrUpdateEmergencyContact( Model model, @ModelAttribute EmergencyCommand refCommand ) throws NotFoundException
	{
		EmergencyCommand command = service.saveEmergencyCommand( refCommand );
		StudentCommand studentCommand = service.getStudent( command.getStudent().getId() );
		model.addAttribute( "student" , studentCommand );
		
		return "redirect:/student/" + studentCommand.getId();
	}
}
