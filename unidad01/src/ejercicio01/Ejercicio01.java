package ejercicio01;

// Importaciones necesarias para manejar archivos y rutas
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Ejercicio01 {

    public static void main(String[] args) {
        // Cambia esto por tu nombre de usuario en Windows
        String usuario = "sgonzalez";

        // Ruta base donde se crearán las carpetas: "C:\Users\sgonzalez"
        String rutaBase = "C:\\Users\\" + usuario;
        

		// Variable que contendrá cada línea leída del archivo estructura.txt
		String linea;

        // Archivo de texto que contiene la "estructura de carpetas"
        String archivoEstructura = "src\\ejercicio01\\estructura.txt";

        // try-with-resources: abre el BufferedReader y lo cierra automáticamente
        try (BufferedReader br = new BufferedReader(new FileReader(archivoEstructura))) {
        	
        
            // Leer el archivo línea por línea
            while ((linea = br.readLine()) != null) {
                // Construir la ruta completa de la carpeta
                Path rutaCarpeta = Paths.get(rutaBase, linea);

                // Si la carpeta no existe, la crea
                if (!Files.exists(rutaCarpeta)) {
                    // Crea todas las carpetas necesarias en la ruta
                    Files.createDirectories(rutaCarpeta);
                    System.out.println("Carpeta creada: " + rutaCarpeta);
                }
            }
        } catch (IOException e) {
            // Captura errores de lectura/escritura y los muestra
            e.printStackTrace();
        }

        // Mensaje final indicando que terminó el proceso
        System.out.println("Proceso completado.");
    }
}
