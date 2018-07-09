package org.arpicoinsurance.groupit.dashboard.controller;

import org.arpicoinsurance.groupit.dashboard.service.ArtmLstRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*")
public class ArtmLastYearRateController {

	@Autowired
	private ArtmLstRateService artmLastRateService;

	@RequestMapping(value = "/lstYearRate", method = RequestMethod.POST)
	public ResponseEntity<Object> getLstYearRate(@RequestParam String quoCreDate) {

		try {
			Double rate = artmLastRateService.getLstYerRate(quoCreDate);
			return new ResponseEntity<Object>(rate, HttpStatus.OK);
		} catch (Exception e) {

			e.printStackTrace();

			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
}
