package ejercicio03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Ejercicio03 {

	public static void main(String[] args) {

		// Variable que contendrá cada línea leída del archivo estructura.txt
		String linea;

		int c;

		char letra;

		// Archivo de texto que contiene la "estructura de carpetas"
		String archivoBase = "src\\ejercicio03\\palabras.txt";

		String archivoOrdenado = "src\\ejercicio03\\palabrasOrdenadas.txt";

		// try-with-resources: abre el BufferedReader y lo cierra automáticamente
		try (BufferedReader br = new BufferedReader(new FileReader(archivoBase))) {
			
			
			
			try (FileWriter fw = new FileWriter(archivoOrdenado)) {
				// Leer el archivo línea por línea
				while ((c = br.read()) != -1) {
					letra = (char) c;

					if (Character.isUpperCase(letra)) {
						fw.write(System.lineSeparator());

					}
					fw.write(letra);
				
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
