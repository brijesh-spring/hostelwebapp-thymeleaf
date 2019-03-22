package com.hostel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hostel.commands.ReferencesCommand;
import com.hostel.commands.StudentCommand;
import com.hostel.domain.Student;
import com.hostel.exception.NotFoundException;
import com.hostel.service.StudentService;

@Controller
public class ReferencesController {

	private StudentService service;
	
	public ReferencesController(StudentService service) {
		super();
		this.service = service;
	}

	@RequestMapping( "/student/{studentId}/reference/{refId}" )
	public String showReferencesForm( Model model, @PathVariable Long studentId, @PathVariable Long refId ) throws NotFoundException
	{
		StudentCommand student = service.getStudent( studentId );
		
		ReferencesCommand reference = null;
		
		try {
				reference = service.getReference( refId );
		}
		catch( NotFoundException nfe )
		{
			reference = new ReferencesCommand();
			Student temp = new Student();
			temp.setId( student.getId() );
			reference.setStudent( temp );
		}
		
		model.addAttribute( "reference" , reference );
		
		return "referencesform";
	}
	
	@RequestMapping( "/references" )
	public String saveOrUpdateReferences( Model model, @ModelAttribute ReferencesCommand refCommand ) throws NotFoundException
	{
		ReferencesCommand command = service.saveReferenceCommand( refCommand );
		StudentCommand studentCommand = service.getStudent( command.getStudent().getId() );
		model.addAttribute( "student" , studentCommand );
		
		return "redirect:/student/" + studentCommand.getId();
	}
}
