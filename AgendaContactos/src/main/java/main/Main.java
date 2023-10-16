package main;

import java.io.IOException;
import agendaController.Agenda;
import menu.Menu;


/**
 * Agenda de contactos
 * Atributos: UUID, nombre, telefono, edad
 * Metodos/Funciones: añadir, eliminar, lista, consulta "UUID/nombre"
 * @author Jhovanny, Jean Paul, Cristian
 */
public class Main {
	public static void main(String[] args) throws IOException {
		var agenda = new Agenda();
		new Menu(agenda).iniciarMenu();
	}
}
