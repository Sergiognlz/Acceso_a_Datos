package proyectojdvc;

// Importamos las clases necesarias para manejar la conexión JDBC
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
    // Método estático que intenta conectarse a la base de datos y devuelve true si tiene éxito
	public static void conectarBD() {
		
        // Variable que indica si la conexión se realizó correctamente
        boolean exito = false;

        // Datos necesarios para conectarnos a la base de datos MySQL
        // url → dirección del servidor + puerto + nombre de la base de datos
        String url = "jdbc:mysql://dns11036.phdns11.es:3306/ad2526_sergio_gonzalez";
        
        // Usuario de la base de datos
        String usuario = "sergio_gonzalez";
        
        // Contraseña del usuario (evita usar "ñ" en nombres de variables)
        String contraseña = "12345";
        
        // Objeto que representará la conexión con la base de datos
        Connection conexion = null;
        
        try {
            // Cargamos el driver JDBC de MySQL (para que Java sepa cómo comunicarse con MySQL)
            // En versiones nuevas a veces no es obligatorio, pero es buena práctica.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Intentamos establecer la conexión usando la URL, usuario y contraseña
            conexion = DriverManager.getConnection(url, usuario, contraseña);

            // Si llegamos aquí, la conexión fue exitosa
            System.out.println("Conexión establecida correctamente");
            exito = true;  // Indicamos que salió bien
        
        // Si el driver no existe en el proyecto (error en las librerías)
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró el driver JDBC");
            e.printStackTrace();
        
        // Si falla la conexión (usuario incorrecto, base de datos caída, etc.)
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos");
            e.printStackTrace();
        
        // Este bloque se ejecuta siempre, haya error o no
        } finally {
            try {
                // Si la conexión llegó a abrirse, la cerramos para liberar recursos
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       
	}
}

