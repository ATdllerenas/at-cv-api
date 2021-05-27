package com.dllerenasg.challenge.atcvapi.model.cv;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WorkExperience {
	 	
	private final String title;
	private final String company;
	private final Date from;
	private final Date to;
	private final Boolean current;
	private final String description;
}
