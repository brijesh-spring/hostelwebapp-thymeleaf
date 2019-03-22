package com.hostel.controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hostel.commands.ContactCommand;
import com.hostel.commands.StudentCommand;
import com.hostel.exception.NotFoundException;
import com.hostel.service.StudentService;
import com.hostel.validators.GroupValidator;
import com.hostel.validators.ParentValidator;

@Controller
public class NewRegistrationController {

	private StudentService studentService;
	
	
	
	public NewRegistrationController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	@RequestMapping( "/student/new" )
	public String registerNewStudent( Model model )
	{
		model.addAttribute( "student" , new StudentCommand() );
		return "studentform";
	}
	
	@RequestMapping( "/student" )
	//public String saveOrUpdateStudent( Model model, @ModelAttribute StudentCommand studentCommand )
	public String saveOrUpdateStudent( Model model, @Validated(GroupValidator.class) @ModelAttribute("student") StudentCommand studentCommand, BindingResult bindingResult )
	{ /* You need to add both the annotations exactly shown above otherwise errors will not be displayed*/
		if( bindingResult.hasErrors() ) 
		{
			model.addAttribute("student", studentCommand);
			return "studentform";
		}
		
		studentCommand = studentService.saveStudentCommand( studentCommand );
		
		model.addAttribute( "student" , studentCommand );
		
		return "redirect:/student/" + studentCommand.getId();
	}
	
	@RequestMapping( "/student/{id}" )
	public String getStudent( Model model, @PathVariable("id") Long id ) throws NotFoundException
	{
		model.addAttribute( "student", studentService.getStudent( id ) );
		return "student";
	}
	
	@RequestMapping( "/student/{id}/update")
	public String updateStudent( Model model, @PathVariable Long id) throws NotFoundException
	{
		model.addAttribute( "student" , studentService.getStudent( id ) );
		
		return "studentform";
	}
	
	
}
