package com.dllerenasg.challenge.atcvapi.model.cv;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Challenge {
	
	private final String name;
	private final Date date;
	private final String description;
	private final String url;
}
