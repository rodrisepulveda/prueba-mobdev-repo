package pruebamobdev.breedsserver.model;

import java.util.List;

public class Breed {

	private String breed;

	private List<String> subBreeds;

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public List<String> getSubBreeds() {
		return subBreeds;
	}

	public void setSubBreeds(List<String> subBreeds) {
		this.subBreeds = subBreeds;
	}

}
