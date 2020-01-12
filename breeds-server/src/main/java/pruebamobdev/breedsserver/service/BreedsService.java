package pruebamobdev.breedsserver.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pruebamobdev.breedsserver.controller.BreedsController;
import pruebamobdev.breedsserver.exception.ErrorServicio;
import pruebamobdev.breedsserver.model.Breed;
import pruebamobdev.breedsserver.model.Entity;

@Service
public class BreedsService {

	private Logger logger = LoggerFactory.getLogger(BreedsController.class);

	public Breed obtenerBreed(String breedName) throws ErrorServicio {

		try {

			RestTemplate plantilla = new RestTemplate();

			Entity entity = plantilla.getForObject("https://dog.ceo/api/breeds/list/all", Entity.class);

			Breed breed = new Breed();

			this.agregarBreedYSubBreeds(breed, breedName, entity.getMessage().get(breedName));

			logger.info(entity.toString());

			return breed;

		} catch (Exception error) {

			logger.error("Error en el servicio al obtener información de una raza: " + error.getMessage(), error);

			throw new ErrorServicio(error);

		}

	}

	private void agregarBreedYSubBreeds(Breed breed, String breedName, List<String> listaSubBreeds) {

		breed.setBreed(breedName);

		breed.setSubBreeds(listaSubBreeds);

	}

}
