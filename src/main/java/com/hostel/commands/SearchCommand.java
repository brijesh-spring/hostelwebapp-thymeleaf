package com.hostel.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchCommand {

	private String firstName = "";
	private String lastName = "";
	private String fatherName = "";
	private String motherName = "";
	private String phone ="";
	private String email = "";
}
