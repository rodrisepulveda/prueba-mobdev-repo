package pruebamobdev.breedsserver.service;

import pruebamobdev.breedsserver.exception.ErrorNoEncontrado;
import pruebamobdev.breedsserver.exception.ErrorServicio;
import pruebamobdev.breedsserver.model.Breed;

public interface IBreedsService {

	Breed obtenerBreed(String breedName) throws ErrorServicio, ErrorNoEncontrado;

}
