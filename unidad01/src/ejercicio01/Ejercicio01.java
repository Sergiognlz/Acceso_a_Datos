package ejercicio01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Ejercicio01 {

	    public static void main(String[] args) {
	        // Cambia esto por tu nombre de usuario
	        String usuario = "sgonzalez";
	        String rutaBase = "C:\\Users\\" + usuario;

	        // Archivo que contiene la estructura
	        String archivoEstructura = "src\\ejercicio01\\estructura.txt";

	    
	        try (BufferedReader br = new BufferedReader(new FileReader(archivoEstructura))) {
	            String linea;
	            while ((linea = br.readLine()) != null) {
	                // Construir la ruta completa de la carpeta
	                Path rutaCarpeta = Paths.get(rutaBase, linea);
	                // Crear la carpeta si no existe
	                if (!Files.exists(rutaCarpeta)) {
	                    Files.createDirectories(rutaCarpeta);
	                    System.out.println("Carpeta creada: " + rutaCarpeta);
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        System.out.println("Proceso completado.");
	    }
	}
