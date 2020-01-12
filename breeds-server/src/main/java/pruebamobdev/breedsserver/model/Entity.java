package pruebamobdev.breedsserver.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Entity {

	private HashMap<String, ArrayList<String>> message;

	private String status;

	public HashMap<String, ArrayList<String>> getMessage() {
		return message;
	}

	public void setMessage(HashMap<String, ArrayList<String>> message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
