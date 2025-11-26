package proyectojdvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    // Método estático que devuelve la conexión abierta
    public static Connection conectarBD() {
        String url = "jdbc:mysql://dns11036.phdns11.es:3306/ad2526_sergio_gonzalez";
        String usuario = "ad2526_sergio_gonzalez";
        String contraseña = "12345";

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, usuario, contraseña);
          

        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró el driver JDBC");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos");
            e.printStackTrace();
        }

        return con; // Devuelve la conexión abierta
    }
}


