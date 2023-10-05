package classes;

import java.util.UUID;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contacto {

	/**
	 * Constructor para añadir nuevo contacto, (nombre, telefono, edad)
	 * @param nombre
	 * @param telefono
	 * @param edad
	 */
	public Contacto(String nombre, String telefono, int edad) {

		this.nombre = nombre;
		this.telefono = telefono;
		this.edad = edad;
	}

	/**
	 * Clave identificador de contacto
	 */
	private UUID uuid = UUID.randomUUID();

	/**
	 * Nombre contacto
	 */
	private String nombre;

	/**
	 * Telefono contacto
	 */
	private String telefono;

	/**
	 * Edad contacto
	 */
	private int edad;
}
