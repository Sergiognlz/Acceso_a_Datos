package ejercicio02;

// Importaciones necesarias para leer/escribir archivos y manejar rutas
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Ejercicio02 {
	public static void main(String[] args) {
		// Cambia esto por tu nombre de usuario (se usará para construir
		// C:\Users\<usuario>)
		String usuario = "sgonzalez";

		// Ruta base en Windows donde se crearán/leerán archivos: "C:\Users\sgonzalez"
		String rutaBase = "C:\\Users\\" + usuario;

		// Variable que contendrá cada línea leída del archivo estructura.txt
		String linea;

		// Ruta del archivo que contiene la "estructura" (cada línea es una subruta o
		// carpeta)
		String archivoEstructura = "src\\ejercicio02\\estructura.txt";

		// try-with-resources: abre el BufferedReader y lo cierra automáticamente al
		// terminar
		try (BufferedReader br = new BufferedReader(new FileReader(archivoEstructura))) {

			// Lee archivo línea por línea hasta que readLine() devuelva null (fin de
			// archivo)
			while ((linea = br.readLine()) != null) {
				// Construye la ruta completa de la carpeta uniendo rutaBase + la línea leída
				// Ejemplo: si linea = "proyecto\\web", rutaCarpeta será
				// C:\Users\sgonzalez\proyecto\web
				Path rutaCarpeta = Paths.get(rutaBase, linea);

				// Construye la ruta del archivo index.html dentro de esa carpeta
				Path archivoHTML = rutaCarpeta.resolve("index.html");

				// Si no existe ya ese archivo, lo crea y escribe contenido HTML básico
				if (!Files.exists(archivoHTML)) {
					// FileWriter necesita un java.io.File, por eso se usa archivoHTML.toFile()
					// try-with-resources para asegurar cierre del FileWriter
					try (FileWriter fw = new FileWriter(archivoHTML.toFile())) {
						fw.write("<html>");
						fw.write("<head>");
						fw.write(" <title>" + linea + "</title>"); // título con el nombre de la línea
						fw.write(" </head>");
						fw.write("   <body>");
						// Aquí concatena rutaCarpeta + linea; probablemente produce algo redundante.
						fw.write("<h1>" + rutaCarpeta + linea + "</h1>");
						fw.write("<h3> Autor:" + usuario + "</h3>");
						fw.write("    </body>");
						fw.write("</html>");
					}
					// Informa por consola que se creó el archivo
					System.out.println("Archivo HTML creado en: " + archivoHTML);
				}

			}
		} catch (IOException e) {
			// Si ocurre un error al leer/escribir archivos, imprime la traza de la
			// excepción
			e.printStackTrace();
		}

		// Mensaje final
		System.out.println("Proceso completado.");
	}
}
