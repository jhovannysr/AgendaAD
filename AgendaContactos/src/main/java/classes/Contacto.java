package classes;

import java.util.UUID;
import lombok.*;

@Data

@AllArgsConstructor
@NoArgsConstructor

public class Contacto {

	public Contacto(String nombre, String telefono, int edad) {

		this.nombre = nombre;
		this.telefono = telefono;
		this.edad = edad;
	}

	private UUID uuid = UUID.randomUUID();

	private String nombre;

	private String telefono;

	private int edad;
}
