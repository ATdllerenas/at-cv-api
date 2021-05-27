package com.dllerenasg.challenge.atcvapi.model.cv;

import lombok.Data;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cv")
public class CV {
	
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String job;
	private String city;
	private String country;
	private String email;
	private String phoneNumber;
    private List<Skill> skills;
    private List<Language> languages;
    private List<WorkExperience> workexperience;
    private List<Education> education;
    private List<Challenge> challenges;
    private SocialMedia socialMedia;
    private Configuration config;
}
