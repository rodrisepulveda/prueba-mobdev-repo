package pruebamobdev.breedsserver;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ControllerUnitTest {

	private static final String BREED = "bulldog";

	private static final String BREED_NOT_EXIST = "cachorro";

	@Autowired
	private MockMvc mockMvc;

	/**
	 * Testea que el servicio web devuelve el objeto correspondiente a la breed que
	 * se busca.
	 * 
	 * Es necesario que el config server "spring-cloud-cofig-server" se esté
	 * ejecutando para que este test funcione.
	 */
	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {

		this.mockMvc.perform(get("/obtenerbreed/" + BREED)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.breed", is(BREED)));

	}

	/**
	 * Testea que al preguntar por un breed que no está en la API el servicio web
	 * devuelve 404 not found.
	 * 
	 * Es necesario que el config server "spring-cloud-cofig-server" se esté
	 * ejecutando para que este test funcione.
	 */
	@Test
	public void testObtenerbreedNotFound() throws Exception {

		this.mockMvc.perform(get("/obtenerbreed/" + BREED_NOT_EXIST)).andDo(print()).andExpect(status().isNotFound());

	}

}
