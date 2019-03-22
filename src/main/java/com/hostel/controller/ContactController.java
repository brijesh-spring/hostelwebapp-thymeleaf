package com.hostel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hostel.commands.ContactCommand;
import com.hostel.commands.StudentCommand;
import com.hostel.exception.NotFoundException;
import com.hostel.service.StudentService;
import com.hostel.validators.GroupContactValidator;
import com.hostel.validators.GroupFatherContactValidator;

@Controller
public class ContactController {

	private StudentService service;
	
	
	
	public ContactController(StudentService service) {
		super();
		this.service = service;
	}

	@RequestMapping( "/contact/{id}" )
	public String showContactForm( Model model, @PathVariable Long id ) throws NotFoundException
	{
		model.addAttribute( "contact", service.getStudent( id ) );
		return "contactform";
	}
	
	@RequestMapping( "/contact")
	public String saveOrUpdateContact( Model model, @ModelAttribute("contact") StudentCommand command ) throws NotFoundException
	{
		
		StudentCommand existing = service.getStudent( command.getId() );
		existing.getContact().setFatherEmail( command.getContact().getFatherEmail() );
		existing.getContact().setFatherPhone( command.getContact().getFatherPhone() );
		existing.getContact().setMotherEmail( command.getContact().getMotherEmail() );
		existing.getContact().setMotherPhone( command.getContact().getMotherPhone() );
		existing.setFatherAddress( command.getFatherAddress() );
		existing.setMotherAddress( command.getMotherAddress() );
		
		existing = service.saveStudentCommand( existing );
		model.addAttribute( "student", existing );
		
		return "redirect:/student/" + command.getId();
	}
}
