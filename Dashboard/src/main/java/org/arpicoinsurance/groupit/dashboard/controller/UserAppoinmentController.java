package org.arpicoinsurance.groupit.dashboard.controller;

import org.arpicoinsurance.groupit.dashboard.service.UserAppoinmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*")
public class UserAppoinmentController {

	@Autowired
	private UserAppoinmentService userAppoinmentService;

	@RequestMapping(value = "/AppoinmentPrint/{id}", method = RequestMethod.GET, produces = "application/pdf")
	public ResponseEntity<Object> getAppoinmentByUserCod(@PathVariable Integer id) {

		System.out.println("called travell..." + id);
		try {

			ResponseEntity<Object> responseEntity = null;

			Object pdf = userAppoinmentService.createAppoinment(id);

			if (pdf == null) {
				responseEntity = new ResponseEntity<Object>(null, HttpStatus.METHOD_NOT_ALLOWED);
			} else {

				responseEntity = new ResponseEntity<Object>(pdf, HttpStatus.OK);
			}

			return responseEntity;

		} catch (Exception e) {
			e.printStackTrace();

			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/AppoinmentTravellingPrint/{id}", method = RequestMethod.GET, produces = "application/pdf")
	public ResponseEntity<Object> getAppoinmentTravellingByUserCod(@PathVariable Integer id) {

		System.out.println("called..." + id);
		try {

			ResponseEntity<Object> responseEntity = null;

			Object pdf = userAppoinmentService.createTravelling(id);

			if (pdf == null) {
				responseEntity = new ResponseEntity<Object>(null, HttpStatus.METHOD_NOT_ALLOWED);
			} else {

				responseEntity = new ResponseEntity<Object>(pdf, HttpStatus.OK);
			}

			return responseEntity;

		} catch (Exception e) {
			e.printStackTrace();

			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
