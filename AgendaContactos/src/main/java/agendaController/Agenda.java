package agendaController;

public class Agenda {

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
