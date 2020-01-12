package pruebamobdev.breedsserver;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pruebamobdev.breedsserver.exception.ErrorNoEncontrado;
import pruebamobdev.breedsserver.exception.ErrorServicio;
import pruebamobdev.breedsserver.model.Breed;
import pruebamobdev.breedsserver.service.IBreedsService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ServiceUnitTests {

	private static final String BREED = "bulldog";

	private static final String BREED_NOT_EXIST = "cachorro";

	@Autowired
	@Qualifier("breedsServiceImpl")
	private IBreedsService breedsService;

	/**
	 * Testea que el servicio devuelve el objeto correspondiente a la breed que se
	 * busca.
	 * 
	 * Es necesario que el config server "spring-cloud-cofig-server" se esté
	 * ejecutando para que este test funcione.
	 */
	@Test
	public void testObtenerbreed() throws ErrorNoEncontrado, ErrorServicio {

		Breed breed = breedsService.obtenerBreed(BREED);

		assertThat(breed.getBreed()).isEqualTo(BREED);

	}

	/**
	 * Testea que al preguntar por un breed que no está en la API el servicio
	 * devuelve un ErrorNoEncontrado.
	 * 
	 * Es necesario que el config server "spring-cloud-cofig-server" se esté
	 * ejecutando para que este test funcione.
	 */
	@Test(expected = ErrorNoEncontrado.class)
	public void testObtenerbreedNotFound() throws Exception {

		breedsService.obtenerBreed(BREED_NOT_EXIST);

	}

}
