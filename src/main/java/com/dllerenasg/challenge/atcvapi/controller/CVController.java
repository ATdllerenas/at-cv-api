package com.dllerenasg.challenge.atcvapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import com.dllerenasg.challenge.atcvapi.exception.*;
import com.dllerenasg.challenge.atcvapi.model.CVPResponse;
import com.dllerenasg.challenge.atcvapi.model.cv.CV;
import com.dllerenasg.challenge.atcvapi.service.CVService;

@RestController
@RequestMapping("/api/v1/cv")
public class CVController {
	
	@Autowired
    private CVService cvService;
	
	@GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CV> findAllCV() {
        return cvService.getAllCV();
    }
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CV getCVById(@PathVariable String id) {
		try {
			return cvService.getCvById(id);
			}
		catch(CVNotFoundException notFound) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, notFound.getMessage());
			}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CVPResponse createCV(@RequestBody CV newcv) {
		try {
			return cvService.createCV(newcv);
			}
		catch (CVInvalidFormatException | CVMissingRequiredFieldException errorMessage) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage.getMessage());
			}
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void updateCV(@RequestBody CV cv, @PathVariable String id) {
		try {
			cvService.updateCV(cv, id);
		}
		catch(CVNotFoundException notFound) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, notFound.getMessage());
		}
		catch(CVInvalidFormatException | CVMissingRequiredFieldException errorMessage) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage.getMessage());
		}
	}
}
