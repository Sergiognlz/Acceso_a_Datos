package proyectojdvc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class CRUD {
	static Connection conn = Conexion.conectarBD();

	// Método para crear la tabla Factura
	public static boolean crearTablaFactura() {
		boolean exito = false;

		Statement stmt = null;

		try {

			stmt = conn.createStatement();

			String SQL = "CREATE TABLE IF NOT EXISTS Factura (" + "idFactura INT AUTO_INCREMENT PRIMARY KEY, "
					+ "idMesa INT NOT NULL, " + "tipoPago VARCHAR(50), " + "importe DOUBLE" + ")";

			stmt.execute(SQL);
			exito = true;
			System.out.println("Tabla Factura creada correctamente!");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return exito;
	}

	// Método para crear la tabla Mesa
	public static boolean crearTablaMesa() {
		boolean exito = false;

		Statement stmt = null;

		try {

			stmt = conn.createStatement();

			String SQL = "CREATE TABLE IF NOT EXISTS Mesa (" + "idMesa INT AUTO_INCREMENT PRIMARY KEY, "
					+ "numComensales INT NOT NULL, " + "reserva TINYINT" + // short se mapea a TINYINT en MySQL
					")";

			stmt.execute(SQL);
			exito = true;
			System.out.println("Tabla Mesa creada correctamente!");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return exito;
	}

	// Método para crear la tabla Pedido
	public static boolean crearTablaPedido() {
		boolean exito = false;

		Statement stmt = null;

		try {

			stmt = conn.createStatement();

			String SQL = "CREATE TABLE IF NOT EXISTS Pedido (" + "idPedido INT AUTO_INCREMENT PRIMARY KEY, "
					+ "idFactura INT NOT NULL, " + "idProducto INT NOT NULL, " + "cantidad INT NOT NULL, "
					+ "FOREIGN KEY (idFactura) REFERENCES Factura(idFactura), "
					+ "FOREIGN KEY (idProducto) REFERENCES Producto(idProducto)" + ")";

			stmt.execute(SQL);
			exito = true;
			System.out.println("Tabla Pedido creada correctamente!");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return exito;
	}

	// Método para crear la tabla Producto
	public static boolean crearTablaProducto() {
		boolean exito = false;

		Statement stmt = null;

		try {

			stmt = conn.createStatement();

			String SQL = "CREATE TABLE IF NOT EXISTS Producto (" + "idProducto INT AUTO_INCREMENT PRIMARY KEY, "
					+ "denominacion VARCHAR(100) NOT NULL, " + "precio DOUBLE NOT NULL" + ")";

			stmt.execute(SQL);
			exito = true;
			System.out.println("Tabla Producto creada correctamente!");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return exito;
	}

	public static boolean existeTablaMesa() {
		boolean existe = false;

		ResultSet rs = null;

		try {

			DatabaseMetaData meta = conn.getMetaData();

			// Busca la tabla "Mesa" en la base de datos
			rs = meta.getTables(null, null, "Mesa", new String[] { "TABLE" });

			if (rs.next()) {
				existe = true; // La tabla existe
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return existe;
	}
	
	
	 // Método para comprobar si la tabla Factura existe
    public static boolean existeTablaFactura() {
        boolean existe = false;
        
        ResultSet rs = null;

        try {
       
            DatabaseMetaData meta = conn.getMetaData();
            
            // Busca la tabla "Factura" en la base de datos
            rs = meta.getTables(null, null, "Factura", new String[] { "TABLE" });
            
            if (rs.next()) {
                existe = true; // La tabla existe
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return existe;
    }
    
    
 // Método para comprobar si la tabla Producto existe
    public static boolean existeTablaProducto() {
        boolean existe = false;
  
        ResultSet rs = null;

        try {
           
            DatabaseMetaData meta = conn.getMetaData();
            
            // Busca la tabla "Producto" en la base de datos
            rs = meta.getTables(null, null, "Producto", new String[] { "TABLE" });
            
            if (rs.next()) {
                existe = true; // La tabla existe
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return existe;
    }
    
}
