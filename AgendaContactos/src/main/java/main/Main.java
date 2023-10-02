package main;


import java.io.IOException;

import agendaController.Agenda;
import io.IO;
import menu.Menu;

public class Main {
	public static void main(String[] args) throws IOException {
		Agenda agenda = new Agenda();
		
		Menu menu = new Menu(agenda);
		
		menu.iniciarMenu();;
	}
}
