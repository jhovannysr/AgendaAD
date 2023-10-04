package agendaController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

import classes.Contacto;
import io.IO;

public class Agenda {

	/**
	 * Nombre del fichero
	 */
	private String NOMBRE_FILE = "./agenda.csv";
	
	/**
	 * Nombre del fichero temporal
	 */
	private String NOMBRE_FILE_TEMPORAL = "./agendaTemporal.csv";
	
	/**
	 * Utilizaremos la clase RandomAcessFile
	 */
	private RandomAccessFile raf_file;
	
	/**
	 * Clase RandomAcessFile para el fichero temporal
	 */
	private RandomAccessFile raf_file_temporal;
	/**
	 * Constructor en el que inicializamos RandomAccessFile
	 * @throws FileNotFoundException
	 */
	
	/**
	 * Constructor
	 * @throws FileNotFoundException
	 */
	public Agenda() throws FileNotFoundException {
		raf_file = new RandomAccessFile(NOMBRE_FILE, "rw");
	}

	/** 
	 * (Jhovanny)
	 * Posiciona el puntero en la última línea para ingresar nuevos registros 
	 * después del último registro que contiene el fichero
	 * @param contacto
	 * @return
	 * @throws IOException
	 */
	public boolean crearContacto(Contacto contacto) throws IOException {
		long posicion = raf_file.length();
		raf_file.seek(posicion);
		almacenarContacto(contacto);
		return true;
	}
	
	/** 
	 * (Jhovanny)
	 * Almacena en el fichero el nuevo regsitro
	 * @param contacto
	 * @throws IOException
	 */
	public void almacenarContacto(Contacto contacto) throws IOException {
		String registro = String.format("%s,%s,%s,%d\n", 
				contacto.getUuid().toString(),
				contacto.getNombre(),
				contacto.getTelefono(),
				contacto.getEdad());
		raf_file.writeBytes(registro);
	}
	
	/** 
	 * (Cristian)
	 * Lista de todos los registros del fichero
	 * @throws IOException
	 */
	public void lista() throws IOException {
	    try  {
	        String linea;
	        raf_file.seek(0L);
	        while ((linea = raf_file.readLine()) != null) {
	            // Dividir la línea en campos utilizando una coma como delimitador
	            String[] campos = linea.split(",");
	            
	            // Mostrar o almacenar los campos separados por comas
	            for (int i = 0; i < campos.length; i++) {
	                IO.println(campos[i] + ",");
	            }
	            IO.println(""); // Salto de línea para la siguiente fila
	        }
	    } catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** (Jhovanny)
	 * Almacenar en un objeto un registro del fichero
	 * @return
	 * @throws IOException
	 */
	private Contacto leerContacto() throws IOException {
		String registro = raf_file.readLine();
		String[] campos = registro.split(",");
		Contacto c = new Contacto(
				UUID.fromString(campos[0]), 
				campos[1],
				campos[2],
				Integer.parseInt(campos[3]));
		return c;
	}
	
	/**
	 * (JeanPaul)
	 * Consulta de contacto por nombre
	 * @param nombre
	 * @return
	 * @throws IOException
	 */public Contacto consultaPorNombre(String nombre) throws IOException {
		long posicion = 0L;
		raf_file.seek(posicion);
		while (raf_file.getFilePointer() < raf_file.length()) {
			Contacto c = leerContacto();
			if (c.getNombre().equals(nombre)) {
				return c;
			} 
		}
		return null;
	}
	
	/**
	 * (JeanPaul)
	 * Consulta de contacto por codigo
	 * @param codigo
	 * @return
	 * @throws IOException
	 */
	public Contacto consultaPorCodigo(String codigo) throws IOException {
		long posicion = 0L;
		raf_file.seek(posicion);
		while (raf_file.getFilePointer() < raf_file.length()) {
			Contacto c = leerContacto();
			if (c.getUuid().toString().equals(codigo)) {
				return c;
			} 
		}
		return null;
	}
	
	/** 
	 * (Jhovanny)
	 * Almacenar los registros a un fichero temporal excepto el contacto eliminado
	 * @return
	 * @throws IOException
	 */
	public String eliminarContactoSeleccionado(String codigoContacto) throws IOException {
		boolean contactoEncontrado = false;
		
		raf_file.seek(0);
		String registroActualizado = "";
		while (raf_file.getFilePointer() < raf_file.length()) {
			String registro = raf_file.readLine();
			String[] campos = registro.split(",");
			if ( !(campos[0].equals(codigoContacto)) ) {
				registroActualizado += registro + "\n";
			} else {
				contactoEncontrado = true;
			}
		}
		
		IO.println(contactoEncontrado == true ? "Contacto eliminado exitosamente." 
				: "No existe el contacto con el codigo introducido.");
		
		return registroActualizado;
	}
	
	/**
	 * (Jhovanny)
	 * Elimina el fichero principal y renombra el fichero temporal por el principal
	 * @param codigoContacto
	 * @throws IOException
	 */
	public void eliminarContacto(String codigoContacto) throws IOException {
		raf_file_temporal = new RandomAccessFile(NOMBRE_FILE_TEMPORAL, "rw");
		
		String registrosActualizados = eliminarContactoSeleccionado(codigoContacto);
		raf_file_temporal.writeBytes(registrosActualizados);
		
		//Cerramos los ficheros 
		raf_file.close();
		raf_file_temporal.close();
		
		File file_principal = new File(NOMBRE_FILE);
		file_principal.delete();
		
		File file_temporal = new File(NOMBRE_FILE_TEMPORAL);
		file_temporal.renameTo(file_principal);
		
		raf_file = new RandomAccessFile(NOMBRE_FILE, "rw");
	}

	/**
	 * (Jhovanny)
	 * Cerrar fichero
	 * @throws IOException
	 */
	public void cerrarFichero() throws IOException {
		raf_file.close();
		IO.println("fichero cerrado");
	}
}
