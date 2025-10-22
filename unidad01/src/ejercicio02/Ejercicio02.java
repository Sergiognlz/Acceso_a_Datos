package ejercicio02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Ejercicio02 {
	public static void main(String[] args) {
		// Cambia esto por tu nombre de usuario
		String usuario = "sgonzalez";
		String rutaBase = "C:\\Users\\" + usuario;

		// Archivo que contiene la estructura
		String archivoEstructura = "src\\ejercicio02\\estructura.txt";

		try (BufferedReader br = new BufferedReader(new FileReader(archivoEstructura))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				// Construir la ruta completa de la carpeta
				Path rutaCarpeta = Paths.get(rutaBase, linea);

				// Crear un archivo HTML dentro de la carpeta
				Path archivoHTML = rutaCarpeta.resolve("index.html");
				if (!Files.exists(archivoHTML)) {
					try (FileWriter fw = new FileWriter(archivoHTML.toFile())) {
						fw.write("<html>");
						fw.write("<head>");
						fw.write(" <title>" + linea + "</title>");
						fw.write(" </head>");
						fw.write("   <body>");
						fw.write("<h1>" + rutaCarpeta + linea + "</h1>");
						fw.write("<h3>Autor:" + usuario + "</h3>");
						fw.write("    </body>");
						fw.write("</html>");
					}
					System.out.println("Archivo HTML creado en: " + archivoHTML);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Proceso completado.");
	}
}
