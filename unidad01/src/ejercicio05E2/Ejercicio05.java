package ejercicio05E2;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Ejercicio05 {
    public static void main(String[] args) {

        String archivoOrigen = "src/ejercicio05E2/ABCDE.txt";
        String archivoDestino = "src/ejercicio05E2/Inverso.txt";

        try (RandomAccessFile origen = new RandomAccessFile(archivoOrigen, "r");
             RandomAccessFile destino = new RandomAccessFile(archivoDestino, "rw")) {

            long puntero = origen.length() - 1; // empieza desde el último byte

            while (puntero >= 0) {
                origen.seek(puntero);            // mover puntero al byte actual
                char c = (char) origen.readByte(); // leer un carácter
                destino.writeByte(c);            // escribirlo en el archivo destino
                puntero--;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Proceso completado.");
    }
}
