package classes;

import java.util.UUID;
   import lombok.*;
 
   @Data
   @ToString
   @AllArgsConstructor
   @NoArgsConstructor
   @RequiredArgsConstructor
   //Es funcional cuando lo pides te da el usuario,nombre, telefono y edad, Realmente deberia funcionar todo con solo poner @Data pero pongo el resto solo por si acaso.
public class Contacto {	   
	   
	public Contacto(UUID usuario, String nombre, String telefono, int edad) {
		super();
		this.usuario = usuario;
		this.nombre = nombre;
		this.telefono = telefono;
		this.edad = edad;
	}
	@Getter
	@Setter
	private UUID usuario;
	@Getter
	@Setter
	private String nombre;
	@Getter
	@Setter
	private String telefono;
	@Getter
	@Setter
	private int edad;
}
