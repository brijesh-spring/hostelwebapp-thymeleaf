package com.hostel.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hostel.commands.StudentCommand;
import com.hostel.exception.NotFoundException;
import com.hostel.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ImageController {

	private StudentService studentService;
	
	public ImageController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	@RequestMapping( method=RequestMethod.GET, path = "/student/{studentId}/image"  )
	public String showImageForm( Model model, @PathVariable("studentId") Long studentId )
	{
		model.addAttribute( "studentId", studentId );
		return "imageuploadform";
	}
	
	@RequestMapping( method=RequestMethod.POST, path = "/student/{studentId}/image" )
	public String saveImage( Model model, @PathVariable("studentId") Long studentId,
										  @RequestParam("imagefile") MultipartFile file )
	{
		studentService.saveStudentImage( studentId, file );
		return "redirect:/student/"+studentId;
	}
	
	@GetMapping( "/student/{studentId}/imagefile" )
	public void renderImageFromDatabase( @PathVariable Long studentId, HttpServletResponse response ) throws NotFoundException
	{
		StudentCommand command = studentService.getStudent( studentId );
		
		if( command.getImage() == null )
		{
			log.info("*** NO IMAGE DATA FOUND *** ");
			return;
		}
		
		byte[] imageBytes = new byte[ command.getImage().length ];
		
		int i = 0;
		for( byte b : command.getImage() )
		{
			imageBytes[i++] = b;
		}
		
		response.setContentType("image/jpeg");
		InputStream is = new ByteArrayInputStream( imageBytes );
		
		try {
			IOUtils.copy( is, response.getOutputStream() );
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
