package menu;

import java.io.IOException;

import agendaController.Agenda;
import classes.Contacto;
import io.IO;

public class Menu {

    private Agenda agenda;

    public Menu(Agenda agenda) throws IOException {
        this.agenda = agenda;
    }

    public void iniciarMenu() throws IOException {
        while (menu());
    }

    /** (jhovanny)
     * Pedir datos del nuevo contacto
     * @return
     * @throws IOException
     */
    public boolean crearContacto() throws IOException {
        IO.println("Introduzca el nombre");
        String nombre = IO.readStringNotBlank();
        IO.println("Introduzca el teléfono");
        String telefono = IO.readStringNotBlank();
        IO.println("Introduzca la edad:");
        int edad = IO.readInt();

        Contacto contacto = new Contacto(nombre, telefono, edad);
        return agenda.crearContacto(contacto);
    }


    public boolean borrarUsuario() throws IOException {
        IO.println("Seleccione el usuario que se borrará");
        String usuario = IO.readStringNotBlank();
        // Aqui luego eliminamos
		return false;
    }
    
    public boolean listarUsuario() throws IOException {
    	
    	return false;
    }

    public boolean buscar() throws IOException {
    	
    	return false;
    }
    
    public boolean menu() throws IOException {
        IO.println("Selecciona una opción:");
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
                if (borrarUsuario()) {
                    IO.println("Usuario eliminado");
                } else {
                    IO.println("No se ha podido eliminar al usuario");
                }
                break;
                
            case 3:
            	if(listarUsuario()) {
            		
            	}
        		break;

            case 4:
            	if(buscar()) {
            		
            	}
        		break;
        		
            case 5:
            	if(buscar()) {
            		
            	}
        		break;

        		
            case 6:
            	return false;

            default:
                IO.println("Opción no válida");
                break;
        }
        return true;
    }
}
