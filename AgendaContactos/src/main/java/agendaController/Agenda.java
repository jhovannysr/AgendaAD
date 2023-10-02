package agendaController;

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
	
      public void listarUsuarios() throws IOException {
        String archivoCSV = "./agendaCSV.csv";
        String linea;
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            while ((linea = br.readLine()) != null) {
                // Dividir la línea en campos utilizando una coma como delimitador
                String[] campos = linea.split(",");
                
                // Mostrar o almacenar los campos según sea necesario
                for (String campo : campos) {
                    System.out.print(campo + " ");
                }
                System.out.println(); // Salto de línea para la siguiente fila
            }
        }
    }


}
