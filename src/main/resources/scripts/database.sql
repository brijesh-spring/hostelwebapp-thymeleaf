CREATE TABLE `address` (
  `id` bigint(20) NOT NULL,
  `address_line1` varchar(150) DEFAULT NULL,
  `address_line2` varchar(150) DEFAULT NULL,
  `city` varchar(75) DEFAULT NULL,
  `county` varchar(75) DEFAULT NULL,
  `house_no` varchar(5) DEFAULT NULL,
  `landmark` varchar(150) DEFAULT NULL,
  `plot_no` varchar(5) DEFAULT NULL,
  `state` varchar(30) DEFAULT NULL,
  `type` varchar(25) DEFAULT NULL,
  `zipcode` varchar(11) DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  `society_name` varchar(100) DEFAULT NULL,
  `created_by` varchar(75) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(75) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)--,
  --KEY `addressKey` (`student_id`),
  --CONSTRAINT `addressKey` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
commit;


CREATE TABLE `admission` (
  `id` bigint(20) NOT NULL,
  `ac_room` bit(1) DEFAULT NULL,
  `admission_date` date DEFAULT NULL,
  `hostel_fee_paid` double DEFAULT NULL,
  `registration_fee` varchar(20) DEFAULT NULL,
  `security_fee` varchar(20) DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  `room_no` int(11) DEFAULT NULL,
  `created_by` varchar(75) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(75) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `hostel_fee_status` varchar(20) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
  --,KEY `admissionKey` (`student_id`),
  --CONSTRAINT `admissionKey` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `college` (
  `id` bigint(20) NOT NULL,
  `address_line1` varchar(150) DEFAULT NULL,
  `address_line2` varchar(150) DEFAULT NULL,
  `city` varchar(55) DEFAULT NULL,
  `county` varchar(55) DEFAULT NULL,
  `name` varchar(150) DEFAULT NULL,
  `state` varchar(55) DEFAULT NULL,
  `zipcode` bigint(20) NOT NULL,
  `created_by` varchar(75) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(75) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `contact` (
  `id` bigint(20) NOT NULL,
  `father_email` varchar(200) DEFAULT NULL,
  `father_phone` varchar(20) DEFAULT NULL,
  `home_phone` varchar(20) DEFAULT NULL,
  `mother_email` varchar(200) DEFAULT NULL,
  `mother_phone` varchar(20) DEFAULT NULL,
  `student_email` varchar(200) DEFAULT NULL,
  `student_phone` varchar(20) DEFAULT NULL,
  `emergency_contact_id` bigint(20) DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  `created_by` varchar(75) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(75) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
  --,KEY `fkContactKey_emergency` (`emergency_contact_id`),
  --KEY `fkContactKey_student` (`student_id`),
  --CONSTRAINT `fkContactKey_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  --CONSTRAINT `fkContactKey_emergency` FOREIGN KEY (`emergency_contact_id`) REFERENCES `emergency_contact` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `emergency_contact` (
  `id` bigint(20) NOT NULL,
  `cell_phone` bigint(20) DEFAULT NULL,
  `landline` bigint(20) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `created_by` varchar(75) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(75) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  `relationship` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`) --,
  --  KEY `student_id` (`student_id`),
  -- CONSTRAINT `emergency_contact_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `payment_details` (
  `id` bigint(20) NOT NULL,
  `amount` double NOT NULL,
  `fee_type` varchar(15) DEFAULT NULL,
  `payment_date` datetime DEFAULT NULL,
  `payment_ref_number` varchar(55) DEFAULT NULL,
  `payment_type` varchar(15) DEFAULT NULL,
  `admission_id` bigint(20) DEFAULT NULL,
  `created_by` varchar(75) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(75) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)--,
  --KEY `fkPaymentDetailsKey_admission` (`admission_id`),
  --CONSTRAINT `fkPaymentDetailsKey_admission` FOREIGN KEY (`admission_id`) REFERENCES `admission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `reference` (
  `id` bigint(20) NOT NULL,
  `cell_phone` bigint(20) DEFAULT NULL,
  `landline` bigint(20) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `relationship` varchar(20) DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  `created_by` varchar(75) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(75) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)--,
  --KEY `fkReferenceKey_student` (`student_id`),
  --CONSTRAINT `fkReferenceKey_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `student` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `dob` date DEFAULT NULL,
  `adhar_no` varchar(75) DEFAULT NULL,
  `father_name` varchar(100) DEFAULT NULL,
  `father_occupation` varchar(100) DEFAULT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `mother_name` varchar(100) DEFAULT NULL,
  `mother_occupation` varchar(100) DEFAULT NULL,
  `admission_id` bigint(20) DEFAULT NULL,
  `student_college_id` bigint(20) DEFAULT NULL,
  `image` longblob,
  `created_by` varchar(75) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(75) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `status_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)--,
  --KEY `fkStudentKey_admission` (`admission_id`),
  --KEY `fkStudentKey_student_college` (`student_college_id`),
  --CONSTRAINT `fkStudentKey_admission` FOREIGN KEY (`admission_id`) REFERENCES `admission` (`id`),
  --CONSTRAINT `fkStudentKey_student_college` FOREIGN KEY (`student_college_id`) REFERENCES `student_college` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `student_college` (
  `id` bigint(20) NOT NULL,
  `admission_year` int(11) DEFAULT NULL,
  `role_number` varchar(55) DEFAULT NULL,
  `semester` int(11) NOT NULL,
  `subject_major` varchar(75) DEFAULT NULL,
  `college_id` bigint(20) DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  `created_by` varchar(75) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(75) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)--,
  --KEY `fkStudentCollegeKey_college` (`college_id`),
  --KEY `fkStudentCollegeKey_student` (`student_id`),
  --CONSTRAINT `fkStudentCollegeKey_college` FOREIGN KEY (`college_id`) REFERENCES `college` (`id`),
  --CONSTRAINT `fkStudentCollegeKey_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


alter table address add constraint `addressKey` foreign key (student_id) references student (id);
alter table admission add CONSTRAINT `admissionKey` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`);
alter table contact add CONSTRAINT `fkContactKey_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`);
alter table contact add CONSTRAINT `fkContactKey_emergency` FOREIGN KEY (`emergency_contact_id`) REFERENCES `emergency_contact` (`id`);
alter table emergency_contact CONSTRAINT `emergency_contact_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`);
alter table payment_details add CONSTRAINT `fkPaymentDetailsKey_admission` FOREIGN KEY (`admission_id`) REFERENCES `admission` (`id`);
alter table reference add CONSTRAINT `fkReferenceKey_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`);
alter table student add CONSTRAINT `fkStudentKey_admission` FOREIGN KEY (`admission_id`) REFERENCES `admission` (`id`);
alter table student add CONSTRAINT `fkStudentKey_student_college` FOREIGN KEY (`student_college_id`) REFERENCES `student_college` (`id`);
alter table student_college add CONSTRAINT `fkStudentCollegeKey_college` FOREIGN KEY (`college_id`) REFERENCES `college` (`id`);
alter table student_college add CONSTRAINT `fkStudentCollegeKey_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`);



