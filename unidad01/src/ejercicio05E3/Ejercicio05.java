package ejercicio05E3;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Ejercicio05 {

    public static void main(String[] args) {

        String archivoOrigen = "src\\ejercicio05E1\\A.txt";
        String archivoDestino = "src\\ejercicio05E1\\5A.txt";
        String linea;

        try {
            // Abrir el archivo origen solo para lectura
            RandomAccessFile origen = new RandomAccessFile(archivoOrigen, "r");
            // Abrir el archivo destino para escritura
            RandomAccessFile destino = new RandomAccessFile(archivoDestino, "rw");

            long longitud = origen.length(); // obtener tamaño del archivo

            // Recorrer el archivo desde el final hacia el inicio
            for (long i = longitud - 1; i >= 0; i--) {
                origen.seek(i); // posicionar el puntero en la i-ésima posición
                char c = (char) origen.readByte(); // leer carácter
               linea=c + " " + (i + 1) + "\n";
                // escribir en el archivo destino el carácter seguido de su posición +1 y un salto de línea
                destino.writeBytes(c + " " + (i + 1) + "\n");
            }

            // Cerrar archivos
            origen.close();
            destino.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Proceso completado.");
    }
}

