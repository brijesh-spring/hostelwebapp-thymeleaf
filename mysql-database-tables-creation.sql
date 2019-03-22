create table address (id bigint not null, address_line1 varchar(255), address_line2 varchar(255), city varchar(255), county varchar(255), house_no varchar(255), landmark varchar(255), plot_no varchar(255), state varchar(255), type varchar(255), zipcode bigint not null, student_id bigint, primary key (id)) engine=InnoDB
create table admission (id bigint not null, ac_room bit, admission_date date, hostel_fee_paid double precision, registration_fee double precision, security_fee double precision, student_id bigint, primary key (id)) engine=InnoDB
create table college (id bigint not null, address_line1 varchar(255), address_line2 varchar(255), city varchar(255), county varchar(255), name varchar(255), state varchar(255), zipcode bigint not null, primary key (id)) engine=InnoDB
create table contact (id bigint not null, father_email varchar(255), father_phone varchar(255), home_phone varchar(255), mother_email varchar(255), mother_phone varchar(255), student_email varchar(255), student_phone varchar(255), emergency_contact_id bigint, student_id bigint, primary key (id)) engine=InnoDB
create table emergency_contact (id bigint not null, cell_phone bigint, landline bigint not null, name varchar(255), primary key (id)) engine=InnoDB
create table hibernate_sequence (next_val bigint) engine=InnoDB
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table payment_details (id bigint not null, amount double precision not null, fee_type varchar(255), payment_date datetime, payment_ref_number varchar(255), payment_type varchar(255), admission_id bigint, primary key (id)) engine=InnoDB
create table reference (id bigint not null, cell_phone bigint, landline bigint not null, name varchar(255), relationship varchar(255), student_id bigint, primary key (id)) engine=InnoDB
create table student (id bigint not null, active bit not null, dob date, father_name varchar(255), father_occupation varchar(255), first_name varchar(255), last_name varchar(255), mother_name varchar(255), mother_occupation varchar(255), admission_id bigint, student_college_id bigint, primary key (id)) engine=InnoDB
create table student_college (id bigint not null, admission_year integer, role_number varchar(255), semester integer not null, subject_major varchar(255), college_id bigint, student_id bigint, primary key (id)) engine=InnoDB
alter table address add constraint FKg6qcywqtp08ksr5r9lpv16g1k foreign key (student_id) references student (id)
alter table admission add constraint FKq3acjsqfwl5jksp7utfmyjhju foreign key (student_id) references student (id)
alter table contact add constraint FKqw116khrg5ep87lfnmnrvukhb foreign key (emergency_contact_id) references emergency_contact (id)
alter table contact add constraint FKb0ed2q8ddlfa467966hy2nah9 foreign key (student_id) references student (id)
alter table payment_details add constraint FK1f55mec92dvb44q6mxba3lrt foreign key (admission_id) references admission (id)
alter table reference add constraint FKawvlrgvpg454mq5kvs5ai525r foreign key (student_id) references student (id)
alter table student add constraint FKoqeniu91wxxpp0ryxeg0qdviy foreign key (admission_id) references admission (id)
alter table student add constraint FKsbelwhcia2nrqqaq4s9i9wmf5 foreign key (student_college_id) references student_college (id)
alter table student_college add constraint FKiry7o85mbs3c3yq8cbl0i35fi foreign key (college_id) references college (id)
alter table student_college add constraint FKj2ldburycwm81luj3nrorqr6e foreign key (student_id) references student (id)
