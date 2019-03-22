CREATE TABLE `address` (
  `id` bigint(20) NOT NULL,
  `address_line1` varchar(255) DEFAULT NULL,
  `address_line2` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `county` varchar(255) DEFAULT NULL,
  `house_no` varchar(255) DEFAULT NULL,
  `landmark` varchar(255) DEFAULT NULL,
  `plot_no` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `zipcode` bigint(20) NOT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  `society_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
  ##,
  #KEY `FKg6qcywqtp08ksr5r9lpv16g1k` (`student_id`),
  #CONSTRAINT `FKg6qcywqtp08ksr5r9lpv16g1k` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `admission` (
  `id` bigint(20) NOT NULL,
  `ac_room` bit(1) DEFAULT NULL,
  `admission_date` date DEFAULT NULL,
  `hostel_fee_paid` double DEFAULT NULL,
  `registration_fee` varchar(20) DEFAULT NULL,
  `security_fee` varchar(20) DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  `room_no` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
  #,
  #KEY `FKq3acjsqfwl5jksp7utfmyjhju` (`student_id`),
  
  #CONSTRAINT `FKq3acjsqfwl5jksp7utfmyjhju` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `college` (
  `id` bigint(20) NOT NULL,
  `address_line1` varchar(255) DEFAULT NULL,
  `address_line2` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `county` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `zipcode` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `contact` (
  `id` bigint(20) NOT NULL,
  `father_email` varchar(255) DEFAULT NULL,
  `father_phone` varchar(255) DEFAULT NULL,
  `home_phone` varchar(255) DEFAULT NULL,
  `mother_email` varchar(255) DEFAULT NULL,
  `mother_phone` varchar(255) DEFAULT NULL,
  `student_email` varchar(255) DEFAULT NULL,
  `student_phone` varchar(255) DEFAULT NULL,
  `emergency_contact_id` bigint(20) DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)#,
  #KEY `FKqw116khrg5ep87lfnmnrvukhb` (`emergency_contact_id`),
  #KEY `FKb0ed2q8ddlfa467966hy2nah9` (`student_id`),
  #CONSTRAINT `FKb0ed2q8ddlfa467966hy2nah9` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  #CONSTRAINT `FKqw116khrg5ep87lfnmnrvukhb` FOREIGN KEY (`emergency_contact_id`) REFERENCES `emergency_contact` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `emergency_contact` (
  `id` bigint(20) NOT NULL,
  `cell_phone` bigint(20) DEFAULT NULL,
  `landline` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );

CREATE TABLE `payment_details` (
  `id` bigint(20) NOT NULL,
  `amount` double NOT NULL,
  `fee_type` varchar(255) DEFAULT NULL,
  `payment_date` datetime DEFAULT NULL,
  `payment_ref_number` varchar(255) DEFAULT NULL,
  `payment_type` varchar(255) DEFAULT NULL,
  `admission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
  #,
  #KEY `FK1f55mec92dvb44q6mxba3lrt` (`admission_id`),
  #CONSTRAINT `FK1f55mec92dvb44q6mxba3lrt` FOREIGN KEY (`admission_id`) REFERENCES `admission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `reference` (
  `id` bigint(20) NOT NULL,
  `cell_phone` bigint(20) DEFAULT NULL,
  `landline` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `relationship` varchar(255) DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
  #,
  #KEY `FKawvlrgvpg454mq5kvs5ai525r` (`student_id`),
  #CONSTRAINT `FKawvlrgvpg454mq5kvs5ai525r` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `student` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `dob` date DEFAULT NULL,
  `father_name` varchar(255) DEFAULT NULL,
  `father_occupation` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mother_name` varchar(255) DEFAULT NULL,
  `mother_occupation` varchar(255) DEFAULT NULL,
  `admission_id` bigint(20) DEFAULT NULL,
  `student_college_id` bigint(20) DEFAULT NULL,
  `image` longblob,
  PRIMARY KEY (`id`)
  #,
  #KEY `FKoqeniu91wxxpp0ryxeg0qdviy` (`admission_id`),
  #KEY `FKsbelwhcia2nrqqaq4s9i9wmf5` (`student_college_id`),
  #CONSTRAINT `FKoqeniu91wxxpp0ryxeg0qdviy` FOREIGN KEY (`admission_id`) REFERENCES `admission` (`id`),
  #CONSTRAINT `FKsbelwhcia2nrqqaq4s9i9wmf5` FOREIGN KEY (`student_college_id`) REFERENCES `student_college` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `student_college` (
  `id` bigint(20) NOT NULL,
  `admission_year` int(11) DEFAULT NULL,
  `role_number` varchar(255) DEFAULT NULL,
  `semester` int(11) NOT NULL,
  `subject_major` varchar(255) DEFAULT NULL,
  `college_id` bigint(20) DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
  #,
  #KEY `FKiry7o85mbs3c3yq8cbl0i35fi` (`college_id`),
  #KEY `FKj2ldburycwm81luj3nrorqr6e` (`student_id`),
  #CONSTRAINT `FKiry7o85mbs3c3yq8cbl0i35fi` FOREIGN KEY (`college_id`) REFERENCES `college` (`id`),
  #CONSTRAINT `FKj2ldburycwm81luj3nrorqr6e` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table address add constraint FKg6qcywqtp08ksr5r9lpv16g1k foreign key (student_id) references student (id);
alter table admission add constraint FKq3acjsqfwl5jksp7utfmyjhju foreign key (student_id) references student (id);
alter table contact add constraint FKqw116khrg5ep87lfnmnrvukhb foreign key (emergency_contact_id) references emergency_contact (id);
alter table contact add constraint FKb0ed2q8ddlfa467966hy2nah9 foreign key (student_id) references student (id);
alter table payment_details add constraint FK1f55mec92dvb44q6mxba3lrt foreign key (admission_id) references admission (id);
alter table reference add constraint FKawvlrgvpg454mq5kvs5ai525r foreign key (student_id) references student (id);
alter table student add constraint FKoqeniu91wxxpp0ryxeg0qdviy foreign key (admission_id) references admission (id);
alter table student add constraint FKsbelwhcia2nrqqaq4s9i9wmf5 foreign key (student_college_id) references student_college (id);
alter table student_college add constraint FKiry7o85mbs3c3yq8cbl0i35fi foreign key (college_id) references college (id);
alter table student_college add constraint FKj2ldburycwm81luj3nrorqr6e foreign key (student_id) references student (id);