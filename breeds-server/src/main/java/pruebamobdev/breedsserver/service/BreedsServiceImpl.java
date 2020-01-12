package pruebamobdev.breedsserver.service;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pruebamobdev.breedsserver.controller.BreedsController;
import pruebamobdev.breedsserver.exception.ErrorNoEncontrado;
import pruebamobdev.breedsserver.exception.ErrorServicio;
import pruebamobdev.breedsserver.model.Breed;
import pruebamobdev.breedsserver.model.EntityBreeds;
import pruebamobdev.breedsserver.model.EntityImages;
import pruebamobdev.breedsserver.model.Image;

@Service("breedsServiceImpl")
public class BreedsServiceImpl implements IBreedsService {

	private Logger logger = LoggerFactory.getLogger(BreedsController.class);

	@Value("${breds.api.server.url}")
	private String bredsApiServerUrl;

	@Value("${breds.api.all.endpoint}")
	private String bredsApiAllEndpoint;

	@Value("${breds.api.images.endpoint}")
	private String bredsApiImagesEndpoint;

	public Breed obtenerBreed(String breedName) throws ErrorServicio, ErrorNoEncontrado {

		try {

			Breed breed = new Breed();

			this.agregarBreedYSubBreeds(breed, breedName);

			this.agregarImagenes(breed);

			return breed;

		} catch (ErrorNoEncontrado error) {

			logger.error(error.getMessage(), error);

			throw error;

		} catch (Exception error) {

			logger.error("Error en el servicio al obtener información de una raza: " + error.getMessage(), error);

			throw new ErrorServicio(error);

		}

	}

	private void agregarImagenes(Breed breed) {

		RestTemplate plantilla = new RestTemplate();

		EntityImages entity = plantilla.getForObject(bredsApiServerUrl + bredsApiImagesEndpoint, EntityImages.class,
				breed.getBreed());

		List<Image> listaImages = new LinkedList<Image>();

		for (String strImage : entity.getMessage()) {

			Image image = new Image();

			image.setUrl(strImage);

			listaImages.add(image);

		}

		breed.setImages(listaImages);

	}

	private void agregarBreedYSubBreeds(Breed breed, String breedName) throws ErrorServicio, ErrorNoEncontrado {

		RestTemplate plantilla = new RestTemplate();

		EntityBreeds entity = plantilla.getForObject(bredsApiServerUrl + bredsApiAllEndpoint, EntityBreeds.class);

		if (entity == null || !entity.getStatus().equals("success")) {

			throw new ErrorServicio("Error al obtener el listado de razas desde la api.");

		}

		if (!entity.getMessage().containsKey(breedName)) {

			throw new ErrorNoEncontrado("La raza no está registrada.");

		}

		breed.setBreed(breedName);

		breed.setSubBreeds(entity.getMessage().get(breedName));

	}

}
