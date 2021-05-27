package com.dllerenasg.challenge.atcvapi.model.cv;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Education {
	
	private final String schoolName;
    private final String career;
    private final Date from;
    private final Date to;
    private final String degree;

}
