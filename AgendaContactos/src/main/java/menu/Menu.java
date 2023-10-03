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
    public String consulta(String modo) throws IOException {
    	if (modo.equals("nombre")) {
    		IO.println("Buscando por nombre: ");
    		String nombre = IO.readStringNotBlank();
    		Contacto c = agenda.consultaPorNombre(nombre);
    		return c == null ? null : c.toString();	
		} 
    	else if(modo.equals("codigo")){
			IO.println("Buscando por codigo: ");
			String uuid = IO.readStringNotBlank();
			Contacto c = agenda.consultaPorCodigo(uuid);
			return c == null ? null : c.toString();	
		}    	
    	return null;
    }
    
    public void listaContacto() throws IOException {
    	agenda.lista();
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
            	listaContacto();
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
            	return false;

            default:
                IO.println("Opción no válida");
                break;
        }
        return true;
    }
}
