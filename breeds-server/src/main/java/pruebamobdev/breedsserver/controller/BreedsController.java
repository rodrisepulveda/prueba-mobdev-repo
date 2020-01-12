package pruebamobdev.breedsserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pruebamobdev.breedsserver.exception.ErrorNoEncontrado;
import pruebamobdev.breedsserver.model.Breed;
import pruebamobdev.breedsserver.service.BreedsService;

@RestController
public class BreedsController {

	private Logger logger = LoggerFactory.getLogger(BreedsController.class);

	@Autowired
	private BreedsService breedsService;

	@RequestMapping(value = "/obtenerbreed/{breed}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Breed> obtenerBreed(@PathVariable String breed) {

		try {

			Breed Breed = breedsService.obtenerBreed(breed);

			return new ResponseEntity<>(Breed, HttpStatus.OK);

		} catch (ErrorNoEncontrado error) {

			logger.error(error.getMessage(), error);

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception error) {

			logger.error(error.getMessage(), error);

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

}
