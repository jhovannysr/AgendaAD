package agendaController;

public class Agenda {
      
      private String NOMBRE_FILE = "./agenda.csv";
	RandomAccessFile file;
      
	public Agenda() throws FileNotFoundException {
		file = new RandomAccessFile(NOMBRE_FILE, "rw");
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
