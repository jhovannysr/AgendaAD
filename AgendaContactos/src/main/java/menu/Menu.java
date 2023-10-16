package menu;

import java.io.IOException;

import agendaController.Agenda;
import classes.Contacto;
import io.IO;

public class Menu {

	/**
	 * Objeto de clase Agenda
	 */
	private Agenda agenda;

	/**
	 * Constructor
	 * @param agenda
	 * @throws IOException
	 */
	public Menu(Agenda agenda) throws IOException {
		this.agenda = agenda;
	}

	/**
	 * (Cristian) Iniciar Menu
	 * @throws IOException
	 */
	public void iniciarMenu() throws IOException {
		if (!menu()) {
			agenda.cerrarFichero();
		}
	}

	/**
	 * (jhovanny) Pedir datos del nuevo contacto
	 * @return
	 * @throws IOException
	 */
	private boolean crearContacto() throws IOException {
		IO.println("Introduzca el nombre");
		String nombre = IO.readStringNotBlank();
		IO.println("Introduzca el teléfono");
		String telefono = IO.readStringNotBlank();
		IO.println("Introduzca la edad:");
		int edad = IO.readInt();

		var contacto = new Contacto(nombre,telefono,edad);
		return agenda.crearContacto(contacto);
	}

	/**
	 * (Jhovanny) Introducir contacto a eliminar
	 * @throws IOException
	 */
	private void contactoAEliminar() throws IOException {
		IO.print("Ingrese el codigo de contacto que desea exterminar: ");
		String usuario = IO.readStringNotBlank();
		agenda.eliminarContacto(usuario);
	}

	/**
	 * (Jean Paul) Seleccion de modo de consulta
	 * @param modo
	 * @return
	 * @throws IOException
	 */
	private String consulta(String modo) throws IOException {
		if (modo.equals("nombre")) {
			IO.println("Buscando por nombre: ");
			String nombre = IO.readStringNotBlank();
			var c = agenda.consultaPorNombre(nombre);
			return c == null ? null : c.toString();
		} else if (modo.equals("codigo")) {
			IO.println("Buscando por codigo: ");
			String uuid = IO.readStringNotBlank();
			var c = agenda.consultaPorCodigo(uuid);
			return c == null ? null : c.toString();
		}
		return null;
	}

	/**
	 * (Cristian) Menu principal
	 * @return
	 * @throws IOException
	 */
	private boolean menu() throws IOException {
		boolean salir = true;
		while (salir) {
			IO.println("\nSelecciona una opción:");
			IO.println("1. Crear Usuario");
			IO.println("2. Borrar Usuario");
			IO.println("3. Listar Fichero");
			IO.println("4. Buscar por Código");
			IO.println("5. Buscar por Nombre");
			IO.println("6. Salir");

			int opcion = IO.readInt();

			switch (opcion) {
			case 1:
				if (crearContacto()) {
					IO.println("Contacto creado");
				} else {
					IO.println("No se ha podido crear el contacto");
				}
				break;
			case 2:
				contactoAEliminar();
				break;
			case 3:
				agenda.lista();
				break;
			case 4:
				String bc = consulta("codigo");
				if (bc == null) {
					IO.println("Codigo no encontrado");
				} else {
					IO.println(bc);
				}
				break;
			case 5:
				String bn = consulta("nombre");
				if (bn == null) {
					IO.println("Contacto no encontrado");
				} else {
					IO.println(bn);
				}
				break;
			case 6:
				salir = false;
				break;
			default:
				IO.println("Opción no válida");
				break;
			}
		}
		return salir;
	}
}