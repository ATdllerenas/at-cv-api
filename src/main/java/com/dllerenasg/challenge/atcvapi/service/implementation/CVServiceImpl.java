package com.dllerenasg.challenge.atcvapi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.dllerenasg.challenge.atcvapi.exception.*;
import com.dllerenasg.challenge.atcvapi.model.CVPResponse;
import com.dllerenasg.challenge.atcvapi.model.cv.CV;
import com.dllerenasg.challenge.atcvapi.service.CVService;
import com.dllerenasg.challenge.atcvapi.repository.CVRepository;
import com.dllerenasg.challenge.atcvapi.validator.cv.CVValidator;

@Service
public class CVServiceImpl implements CVService {
	
	@Autowired
    private CVRepository cvRepository;

    @Autowired
    private CVValidator cvValidator;
    
    @Override
    public List<CV> getAllCV() {
        return cvRepository.findAll();
    }

    @Override
    public CV getCvById(String id) {
    	Optional<CV> foundCV = cvRepository.findById(id);
    	if(foundCV.isPresent()) {
    		return foundCV.get();
    	}
    	throw new CVNotFoundException(String.format("Curriculum Vitae with id %s couldn't be found.", id));
    }

    @Override
    public CVPResponse createCV(CV cv) {
        cvValidator.validate(cv);
        CV createdCV = cvRepository.save(cv);
        return new CVPResponse(createdCV.getId());
    }
    
    @Override
    public void updateCV(CV cv, String id) throws CVInvalidFormatException, CVMissingRequiredFieldException, CVNotFoundException {
    	if(cvRepository.existsById(id)) {
    		cvValidator.validate(cv);
    		cv.setId(id);
    		cvRepository.save(cv);
    		return;
    	}
    	throw new CVNotFoundException(String.format("Curriculum Vitae with id %s couldn't be found.", id));
    }
}
