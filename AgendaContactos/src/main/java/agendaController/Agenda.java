package agendaController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

import classes.Contacto;

public class Agenda {

	/**
	 * Nombre del fichero
	 */
	private String NOMBRE_FILE = "./agenda.csv";
	
	/**
	 * Tipo de fichero que vamos a utilizar
	 */
	private RandomAccessFile file;
	
	/**
	 * Constructor en el que inicializamos RandomAccessFile
	 * @throws FileNotFoundException
	 */
	
	public Agenda() throws FileNotFoundException {
		file = new RandomAccessFile(NOMBRE_FILE, "rw");
	}

	/** (Jhovanny)
	 * Posiciona el puntero en la última línea para ingresar nuevos registros 
	 * después del último registro que contiene el fichero
	 * @param contacto
	 * @return
	 * @throws IOException
	 */
	public boolean crearContacto(Contacto contacto) throws IOException {
		long posicion = file.length();
		file.seek(posicion);
		almacenarContacto(contacto);
		return true;
	}
	
	/** (Jhovanny)
	 * Almacena el registro al fichero
	 * @param contacto
	 * @throws IOException
	 */
	public void almacenarContacto(Contacto contacto) throws IOException {
		String registro = String.format("%s,%s,%s,%d\n", 
				contacto.getUuid().toString(),
				contacto.getNombre(),
				contacto.getTelefono(),
				contacto.getEdad());
		file.writeBytes(registro);
	}
	
	/** (Cristian)
	 * @throws IOException
	 */
	public void lista() throws IOException {
	    try  {
	        String linea;
	        file.seek(0L);
	        while ((linea = file.readLine()) != null) {
	            // Dividir la línea en campos utilizando una coma como delimitador
	            String[] campos = linea.split(",");
	            
	            // Mostrar o almacenar los campos separados por comas
	            for (int i = 0; i < campos.length; i++) {
	                System.out.print(campos[i] + ",");
	            }
	            System.out.println(); // Salto de línea para la siguiente fila
	        }
	    } catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * (JeanPaul)
	 * @param nombre
	 * @return
	 * @throws IOException
	 */public Contacto consultaPorNombre(String nombre) throws IOException {
		long posicion = 0L;
		file.seek(posicion);
		while (file.getFilePointer() < file.length()) {
			Contacto c = leerContacto();
			if (c.getNombre().equals(nombre)) {
				return c;
			} 
		}
		return null;
	}
	
	/**
	 * (JeanPaul)
	 * @param codigo
	 * @return
	 * @throws IOException
	 */
	public Contacto consultaPorCodigo(String codigo) throws IOException {
		long posicion = 0L;
		file.seek(posicion);
		while (file.getFilePointer() < file.length()) {
			Contacto c = leerContacto();
			if (c.getUuid().toString().equals(codigo)) {
				return c;
			} 
		}
		return null;
	}
	

	private Contacto leerContacto() throws IOException {
		String registro = file.readLine();
		String[] campos = registro.split(",");
		Contacto c = new Contacto(
				UUID.fromString(campos[0]), 
				campos[1],
				campos[2],
				Integer.parseInt(campos[3]));
		return c;
	}
}
