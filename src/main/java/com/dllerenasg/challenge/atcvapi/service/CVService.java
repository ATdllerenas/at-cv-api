package com.dllerenasg.challenge.atcvapi.service;

import java.util.List;
import com.dllerenasg.challenge.atcvapi.exception.*;
import com.dllerenasg.challenge.atcvapi.model.CVPResponse;
import com.dllerenasg.challenge.atcvapi.model.cv.CV;

public interface CVService {
	
	public List<CV> getAllCV();
    
	public CV getCvById(String resourceId) throws CVNotFoundException;
   
	public CVPResponse createCV(CV cv) throws CVInvalidFormatException, CVMissingRequiredFieldException;
    
	public void updateCV(CV cv, String resourceId) throws CVInvalidFormatException, CVMissingRequiredFieldException, CVNotFoundException;
}
