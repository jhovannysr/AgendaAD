package main;

import java.io.IOException;
import agendaController.Agenda;
import io.IO;
import menu.Menu;


/**
 * Agenda de contactos
 * Atributos: UUID, nombre, telefono, edad
 * Metodos/Funciones: añadir, eliminar, lista, consulta "UUID/nombre"
 * @author Jhovanny, Jean Paul, Cristian
 */
public class Main {
	public static void main(String[] args) throws IOException {
		Agenda agenda = new Agenda();
		
		Menu menu = new Menu(agenda);
		menu.iniciarMenu();;
	}
}
