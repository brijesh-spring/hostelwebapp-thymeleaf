package com.hostel.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hostel.Utility;
import com.hostel.commands.AdmissionCommand;
import com.hostel.commands.EmergencyCommand;
import com.hostel.commands.PaymentCommand;
import com.hostel.commands.PaymentsCommand;
import com.hostel.commands.ReferencesCommand;
import com.hostel.commands.SearchCommand;
import com.hostel.commands.StudentCommand;
import com.hostel.converter.AdmissionCommandToAdmission;
import com.hostel.converter.AdmissionToAdmissionCommand;
import com.hostel.converter.EmergencyCommandToEmergency;
import com.hostel.converter.EmergencyToEmergencyCommand;
import com.hostel.converter.RefereceCommandToReference;
import com.hostel.converter.ReferenceToReferenceCommand;
import com.hostel.converter.StudentCommandToStudent;
import com.hostel.converter.StudentToStudentCommand;
import com.hostel.domain.Admission;
import com.hostel.domain.EmergencyContact;
import com.hostel.domain.FeeStatus;
import com.hostel.domain.FeeType;
import com.hostel.domain.PaymentDetails;
import com.hostel.domain.PaymentType;
import com.hostel.domain.Reference;
import com.hostel.domain.Student;
import com.hostel.domain.StudentStatus;
import com.hostel.exception.NotFoundException;
import com.hostel.repositories.EmergencyContactRepository;
import com.hostel.repositories.ReferenceRepository;
import com.hostel.repositories.StudentRepository;
import com.hostel.repositories.specifications.StudentSpecifications;

@Service
@PropertySource( ignoreResourceNotFound=true, value="classpath:config.properties" )
public class StudentServiceImpl implements StudentService{

	@Value( "${registrationFee}" )
	String registrationFee;
	
	@Value( "${securityFee}" )
	String securityFee;
	
	@Value( "${hostelFee}" )
	String hostelFee;
	
	@Value( "${acFee}" )
	String acFee;
	
	private StudentRepository studentRepository;
	private ReferenceRepository referenceRepository;
	private EmergencyContactRepository emergencyRepository;
	
	private StudentCommandToStudent commandToStudent;
	private StudentToStudentCommand toStudentCommand;
	private ReferenceToReferenceCommand referenceToCommand;
	private RefereceCommandToReference commandToReference;
	private AdmissionToAdmissionCommand toAdmissionCommand;
	private AdmissionCommandToAdmission commandToAdmission;
	private EmergencyCommandToEmergency commandToEmergency;
	private EmergencyToEmergencyCommand emergencyToCommand;
	
	public StudentServiceImpl(StudentRepository studentRepository, StudentCommandToStudent commandToStudent,
			StudentToStudentCommand toStudentCommand, ReferenceRepository referenceRepository,
			ReferenceToReferenceCommand referenceToCommand, RefereceCommandToReference commandToReference,
			AdmissionToAdmissionCommand toAdmissionCommand, AdmissionCommandToAdmission commandToAdmission,
			EmergencyCommandToEmergency commandToEmergency, EmergencyToEmergencyCommand emergencyToCommand,
			EmergencyContactRepository emergencyRepository ) 
	{
		super();
		this.studentRepository = studentRepository;
		this.commandToStudent = commandToStudent;
		this.toStudentCommand = toStudentCommand;
		this.referenceRepository = referenceRepository;
		this.referenceToCommand = referenceToCommand;
		this.commandToReference = commandToReference;
		this.toAdmissionCommand = toAdmissionCommand;
		this.commandToAdmission = commandToAdmission;
		this.commandToEmergency = commandToEmergency;
		this.emergencyToCommand = emergencyToCommand;
		this.emergencyRepository = emergencyRepository;
	}

	@Override
	public StudentCommand saveStudentCommand(StudentCommand command) {
		
		Student student = commandToStudent.convert( command ); 
		
		student = studentRepository.save( student );
		
		return toStudentCommand.convert( student );
	}

	@Override
	public StudentCommand getStudent(Long id) throws NotFoundException {
		
		Optional<Student> studentOption = studentRepository.findById( id );
		
		if( studentOption.isPresent() )
		{
			Student student = studentOption.get();
			
			System.out.println( student );
			
			if( student.getAddresses() != null ) student.getAddresses().forEach( System.out::println );
			
			System.out.println( student.getContact() );
			
			if( student.getReference() != null ) student.getReference().forEach( System.out::println );
			
			if( student.getEmergencyContacts() != null ) student.getEmergencyContacts().forEach( System.out::println );
			
			StudentCommand command = toStudentCommand.convert( studentOption.get() );
			
			return command;
		}
		
		throw new NotFoundException( "Student with id " + id + " Not Found !" );
	}

	@Override
	public ReferencesCommand saveReferenceCommand(ReferencesCommand command) {
		
		Reference reference = commandToReference.convert(command);
		
		reference = referenceRepository.save( reference );
		
		return referenceToCommand.convert(reference);
	}

	@Override
	public ReferencesCommand getReference(Long id) throws NotFoundException {
		
		Optional<Reference> reference = referenceRepository.findById(id);
		
		if( reference.isPresent() )
		{
			return referenceToCommand.convert(reference.get());
		}
		
		throw new NotFoundException( "Reference details for id " + id + " not found !" );
	}

	@Override
	public List<StudentCommand> getStudents(SearchCommand searchCommand) {
		
		List<StudentCommand> result = new ArrayList<>();
		boolean found = false;
		List<Student> students = null;
		
		Specification<Student> specFirstName = Utility.isEmpty( searchCommand.getFirstName() ) ? null : StudentSpecifications.searchByFirstName( searchCommand.getFirstName() );
		Specification<Student> specLastName = Utility.isEmpty( searchCommand.getLastName() ) ? null : StudentSpecifications.searchByLastName( searchCommand.getLastName() );
		Specification<Student> specFatherName = Utility.isEmpty( searchCommand.getFatherName() ) ? null : StudentSpecifications.searchByFatherName( searchCommand.getFatherName() );
		Specification<Student> specMotherName = Utility.isEmpty( searchCommand.getMotherName() ) ? null : StudentSpecifications.searchByMotherName( searchCommand.getMotherName() );
		Specification<Student> specPhone = Utility.isEmpty( searchCommand.getPhone() ) ? null : StudentSpecifications.searchByPhone( searchCommand.getPhone() );
		
		if( specFirstName == null && specLastName == null && specFatherName == null && specMotherName == null )
		{
			studentRepository.findAll().forEach( student -> result.add( toStudentCommand.convert( student )) );
		}
		else
		{
			
			students = studentRepository.findAll( Specification.where( specFirstName )
											.or( specLastName )
											.or( specFatherName )
											.or( specMotherName )
											.or( specPhone ))
					;
			if( !students.isEmpty() )
			{
				students.stream().forEach( student -> result.add( toStudentCommand.convert( student ) ) ) ;
			}

		}
		
		return result;
	}
	
	@Override
	public void saveStudentImage( Long studentId, MultipartFile file )
	{
		Optional<Student> studentOption = studentRepository.findById( studentId );
		
		if( studentOption.isPresent() )
		{
			Student student = studentOption.get();
			
			try {
				student.setImage( file.getBytes() );
				studentRepository.save( student );
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public AdmissionCommand getAdmissionDetails( Long studentId )
	{
		AdmissionCommand command = new AdmissionCommand();
		
		//if( !Utility.isEmpty( registrationFee ) ) command.setRegistrationFee( Double.parseDouble( registrationFee ) );
		//if( !Utility.isEmpty( securityFee ) ) command.setSecurityFee( Double.parseDouble( securityFee ) );
		
		Optional<Student> studentOption = studentRepository.findById( studentId );
		if( studentOption.isPresent() )
		{
			Student student = studentOption.get();
			command.setStudentId( student.getId() );
			
			if( student.getAdmissions() != null && !student.getAdmissions().isEmpty() )
			{
				//TreeSet<PaymentDetails> paymentDetails = new TreeSet( student.getAdmission().getPaymentDetails() ); ;
				Admission temp = getActiveAdmission( student );
				
				if( temp != null )
				{
					Set<PaymentDetails> paymentDetails = temp.getPaymentDetails();
					
					command = toAdmissionCommand.convert( temp );
					
					Utility.updateHostelFeeStatus(command, hostelFee, acFee);
				}

			}
		}
		populateInitialPayments( command );
		
		command.getPayments().stream().sorted( Comparator.comparing( PaymentCommand::getId ).reversed() );
		
		return command;
	}
	
	private AdmissionCommand populateInitialPayments( AdmissionCommand command )
	{
		Set<PaymentCommand> payments = null;
		Set<PaymentCommand> result = new HashSet<>();
		if( command.getPayments() == null )
		{
			payments = new HashSet<>();
			command.setPayments( payments );
		}
		else {
			payments = command.getPayments();
		}
		command.setExistingPayments( payments );
		
		Map<FeeType, PaymentCommand> temp = new HashMap<>();
		
		if( !payments.isEmpty() ) payments.forEach( payment -> temp.put( payment.getFeeType(), payment ) );
		
		if( !temp.containsKey(FeeType.REGISTRATION_FEE) ) 
		{
			PaymentCommand regPay = new PaymentCommand();
			regPay.setAmount( Double.parseDouble( registrationFee ) );
			regPay.setFeeType( FeeType.REGISTRATION_FEE );
			regPay.setPaymentDate( LocalDateTime.now() );
			regPay.setPaymentRefNumber( "" );
			regPay.setPaymentType( PaymentType.CASH );
			
			result.add( regPay );
		}
		
		if( !temp.containsKey( FeeType.SECURITY_FEE ) ) 
		{
			PaymentCommand securityPay = new PaymentCommand();
			securityPay.setAmount( Double.parseDouble( securityFee ) );
			securityPay.setFeeType( FeeType.SECURITY_FEE );
			securityPay.setPaymentDate( LocalDateTime.now() );
			securityPay.setPaymentRefNumber( "" );
			securityPay.setPaymentType( PaymentType.CASH );
			
			result.add( securityPay );
		}
		
		command.setPayments( result );
		
		return command;
	}
	
	private AdmissionCommand populatPaymentDetailsForUpdate( Set<PaymentDetails> payments, AdmissionCommand command )
	{
		Map<FeeType, PaymentDetails> temp = new HashMap<>();
		
		if( payments != null && !payments.isEmpty() ) payments.forEach( payment -> temp.put( payment.getFeeType(), payment ) );
		
		if( command.getRegFeeStatus().equals( FeeStatus.PAID_IN_FULL ) && !temp.containsKey( FeeType.REGISTRATION_FEE ))
		{
			PaymentCommand regPay = new PaymentCommand();
			
			regPay.setAmount( command.isRenewed() ? 0 : Double.parseDouble( registrationFee ) );
			regPay.setFeeType( FeeType.REGISTRATION_FEE );
			regPay.setPaymentDate( LocalDateTime.now() );
			regPay.setPaymentRefNumber( command.getRegPaymentRefNumber() );
			regPay.setPaymentType( command.getRegPaymentType() );
			
			command.getPayments().add( regPay );
		}
		
		if( command.getSecurityFeeStatus().equals( FeeStatus.PAID_IN_FULL ) && !temp.containsKey( FeeType.SECURITY_FEE ))
		{
			PaymentCommand securityPay = new PaymentCommand();
			
			securityPay.setAmount( command.isRenewed() ? 0 : Double.parseDouble( securityFee ) );
			securityPay.setFeeType( FeeType.SECURITY_FEE );
			securityPay.setPaymentDate( LocalDateTime.now() );
			securityPay.setPaymentRefNumber( command.getSecPaymentRefNumber() );
			securityPay.setPaymentType( command.getSecPaymentType() );
			
			command.getPayments().add( securityPay );
		}
		
		if( command.getHostelFeeAmount() > 0 )
		{
			PaymentCommand payment = new PaymentCommand();
			payment.setAmount( command.getHostelFeeAmount() );
			payment.setFeeType( FeeType.HOSTEL_FEE );
			payment.setPaymentDate( LocalDateTime.now() );
			payment.setPaymentRefNumber( command.getHostelPaymentRefNumber() );
			payment.setPaymentType( command.getHostelPaymentType() );
			
			command.getPayments().add( payment );
			
			double totalFeePaid = command.getHostelFeePaid() + command.getHostelFeeAmount();
			command.setHostelFeePaid( totalFeePaid );
			
			
		}
		
		return command;
	}
	
	private Admission getActiveAdmission( Student student )
	{
		if( student == null ) return null;
		
		Optional<Admission> optional = student.getAdmissions().stream().filter( admission -> admission.isActive() ).findFirst();
		
		if( optional.isPresent() )
		{
			return optional.get();
		}
		
		return null;
	}
	
	public StudentCommand saveAdmissionDetails( AdmissionCommand command )
	{
		Optional<Student> studentOption = studentRepository.findById( command.getStudentId() );
		
		if( studentOption.isPresent() )
		{
			Student student = studentOption.get();
			
			// Mark student as active if registration fee is paid
			if( !command.isRenewed() && command.getRegFeeStatus().equals( FeeStatus.PAID_IN_FULL ) ) 
			{
				student.setActive( true );
				student.setStatus( StudentStatus.Active );
				student.setStatusDate( LocalDateTime.now() );
			}

			Set<PaymentDetails> payments = getActiveAdmission( student ) != null ? getActiveAdmission( student ).getPaymentDetails() : null;
			
			populatPaymentDetailsForUpdate( payments, command );
			
			// Update Hoste Fee Status
			Utility.updateHostelFeeStatus(command, hostelFee, acFee);
			
			Admission admission = commandToAdmission.convert( command );
			
			
			
			admission.setStudent( student );
			
			if(admission.getId() == null || admission.getId() == 0 ) {
				student.getAdmissions().add( admission );
			}
			else
			{
				student.getAdmissions().removeIf( adm -> adm.getId().longValue() == admission.getId().longValue() );
				student.getAdmissions().add( admission );
			}
			
			if( admission.getPaymentDetails() != null ) admission.getPaymentDetails().stream().forEach( payment -> {payment.setAdmission(admission); System.out.println("Setting admission in payments : " + admission.getId());} );
			
			studentRepository.save( student );
			
			return toStudentCommand.convert( student );
		}
		
		return null;
	}

	@Override
	public StudentCommand renewAdmissionDetails( Long studentId )
	{
		Optional<Student> studentOption = studentRepository.findById( studentId );
		
		if( studentOption.isPresent() )
		{
			Student student = studentOption.get();
			
			Admission activeAdmission = getActiveAdmission( student );
			
			if( activeAdmission != null )
			{
				activeAdmission.setActive( false );
			}
			
			student.setStatus( StudentStatus.Renewed );
			student.setStatusDate( LocalDateTime.now() );
			studentRepository.save( student );
			
			AdmissionCommand command = new AdmissionCommand();
			command.setRegFeeStatus( FeeStatus.PAID_IN_FULL );
			command.setRegPaymentRefNumber( "Waived-Off-For-Renewal" );
			command.setRegPaymentType( PaymentType.CASH );
			command.setSecurityFeeStatus( FeeStatus.PAID_IN_FULL );
			command.setSecPaymentRefNumber( "Waived-Off-For-Renewal" );
			command.setSecPaymentType( PaymentType.CASH );
			command.setRenewed( true );
			command.setStudentId( student.getId() );
			
			return saveAdmissionDetails( command );
		}
		
		
		return null;
	}
	
	public StudentCommand releaseStudent( Long studentId )
	{
		Optional<Student> studentOption = studentRepository.findById( studentId );
		
		if( studentOption.isPresent() )
		{
			Student student = studentOption.get();
			
			Admission activeAdmission = getActiveAdmission( student );
			
			if( activeAdmission != null )
			{
				activeAdmission.setActive( false );
			}
			
			student.setStatus( StudentStatus.Left );
			student.setStatusDate( LocalDateTime.now() );
			studentRepository.save( student );
			
			return toStudentCommand.convert( student );
		}
		
		return null;
	}
	
	@Override
	public EmergencyCommand saveEmergencyCommand(EmergencyCommand command) {

		EmergencyContact contact =  commandToEmergency.convert(command);
		
		contact = emergencyRepository.save( contact );
		
		return emergencyToCommand.convert(contact);
	}

	@Override
	public EmergencyCommand getEmergenceContact(Long id) throws NotFoundException {

		Optional<EmergencyContact> reference = emergencyRepository.findById(id);
		
		if( reference.isPresent() )
		{
			return emergencyToCommand.convert(reference.get());
		}
		
		throw new NotFoundException( "Emergency Contact details for id " + id + " not found !" );
	}
	

}
