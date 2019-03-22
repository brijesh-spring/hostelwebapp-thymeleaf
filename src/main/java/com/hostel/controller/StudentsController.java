package com.hostel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hostel.commands.AdmissionCommand;
import com.hostel.commands.SearchCommand;
import com.hostel.exception.NotFoundException;
import com.hostel.service.StudentService;

@Controller
public class StudentsController {

	private StudentService studentService;
	
	public StudentsController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	@RequestMapping( "/students/search" )
	public String searchStudent( Model model )
	{
		model.addAttribute("searchStudent", new SearchCommand() );
		return "students";
	}
	
	@RequestMapping( "/students" )
	public String showStudents( Model model, @ModelAttribute SearchCommand searchCommand )
	{
		model.addAttribute( "searchStudent", searchCommand );
		model.addAttribute( "students", studentService.getStudents(searchCommand) );
		
		return "students";
	}
	
	@RequestMapping( "/students/admission/search" )
	public String searchStudentForAdmission( Model model )
	{
		model.addAttribute("searchStudent", new SearchCommand() );
		return "admissionsearch";
	}
	
	@RequestMapping( "/students/admission" )
	public String showStudentsForAdmission( Model model, @ModelAttribute SearchCommand searchCommand )
	{
		model.addAttribute( "searchStudent", searchCommand );
		model.addAttribute( "students", studentService.getStudents(searchCommand) );
		
		return "admissionsearch";
	}
	
	@RequestMapping ( "/student/{studentId}/admissionform")
	public String showAdmissionForm( Model model, @PathVariable ("studentId") Long studentId, @RequestParam boolean renewed ) throws NotFoundException
	{
		AdmissionCommand command = studentService.getAdmissionDetails( studentId );
		//model.addAttribute("payments", command.getPayments() );
		command.setRenewed( renewed );
		model.addAttribute( "admission" , command );
		return "admissionform";
	}
	
	@RequestMapping( "/student/{studentId}/admission" )
	public String saveAdmission( Model model, @PathVariable("studentId") Long studentId, @ModelAttribute AdmissionCommand command )
	{
		command.setStudentId( studentId );
		
		model.addAttribute( "student" , studentService.saveAdmissionDetails( command ) );
		
		return "redirect:/student/"+studentId + "/admissionform?renewed=true";
	}
	
	@RequestMapping( "/renewstudents/search" )
	public String searchStudentsForRenew( Model model, @ModelAttribute SearchCommand searchCommand )
	{
		model.addAttribute("searchStudent", new SearchCommand() );
		
		return "renewsearch";
	}
	
	@RequestMapping( "/renewstudents/show" )
	public String showStudentsForRenew( Model model, @ModelAttribute SearchCommand searchCommand )
	{
		model.addAttribute( "searchStudent", searchCommand );
		model.addAttribute( "students", studentService.getStudents(searchCommand) );
		
		return "renewsearch";
	}
	
	@RequestMapping( "/student/{studentId}/renew" )
	public String doStudentRenew( @PathVariable Long studentId, Model model )
	{
		model.addAttribute( "student" , studentService.renewAdmissionDetails( studentId ) );
		model.addAttribute( "renewed", true );
		
		return "redirect:/student/"+studentId + "/admissionform?renewed=true";
	}
	
	@RequestMapping( "/student/{studentId}/release" )
	public String doStudentRelease( @PathVariable Long studentId, Model model )
	{
		model.addAttribute( "student" , studentService.releaseStudent( studentId )  );
		
		return "redirect:/students";
	}
}
