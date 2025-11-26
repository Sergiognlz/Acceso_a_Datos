package proyectojdvc;

public class SinConexionException extends RuntimeException {
    public SinConexionException() {
        super("No hay conexión establecida con la base de datos.");
    }

    public SinConexionException(String mensaje) {
        super(mensaje);
    }
}
