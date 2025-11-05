package ejercicio04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.LinkedList;

public class Ejercicio04 {

	public static void main(String[] args) {

		// Variable que contendrá cada línea leída del archivo estructura.txt
		String linea;

		
		LinkedList<String> lista = new LinkedList<String>();
				
		// Archivo de texto que contiene la "estructura de carpetas"
		String archivoBase = "src\\ejercicio03\\palabrasOrdenadas.txt";

		String archivoOrdenado = "src\\ejercicio04\\palabrasOrdenadasAsc.txt";

		// try-with-resources: abre el BufferedReader y lo cierra automáticamente
		try (BufferedReader br = new BufferedReader(new FileReader(archivoBase))) {

			// Leer el archivo línea por línea
			while ((linea = br.readLine()) != null) {
				
				lista.add(linea);
				
				
				
			}
			lista.sort(null);
			
			
		} catch (IOException e) {
			// Captura errores de lectura/escritura y los muestra
			e.printStackTrace();
		}


		
		try (FileWriter fw = new FileWriter(archivoOrdenado)) {
			
		for (String palabra:lista) {
			
			fw.append(palabra);
			fw.write(System.lineSeparator());
		}
		
		
		} catch (IOException e) {
			// Captura errores de lectura/escritura y los muestra
			e.printStackTrace();
		}
		
		// Mensaje final indicando que terminó el proceso
				System.out.println("Proceso completado.");

	}
}
