package proyectojdvc;

import java.sql.*;
import java.util.*;

public class CRUD {

    public static Scanner sc = new Scanner(System.in);

    // crear tablas
    public static boolean crearTablaMesa(Connection con) {
        if (con == null) throw new SinConexionException();
        String sql = """
            CREATE TABLE IF NOT EXISTS Mesa (
                idMesa INT AUTO_INCREMENT PRIMARY KEY,
                numComensales INT NOT NULL,
                reserva TINYINT NOT NULL
            );
        """;
        return ejecutarUpdate(con, sql);
    }

    public static boolean crearTablaProducto(Connection con) {
        if (con == null) throw new SinConexionException();
        String sql = """
            CREATE TABLE IF NOT EXISTS Producto (
                idProducto INT AUTO_INCREMENT PRIMARY KEY,
                denominacion VARCHAR(100) NOT NULL,
                precio DOUBLE NOT NULL
            );
        """;
        return ejecutarUpdate(con, sql);
    }

    public static boolean crearTablaFactura(Connection con) {
        if (con == null) throw new SinConexionException();
        if (!existeTablaMesa(con)) {
            System.out.println("Debes crear la tabla Mesa antes de Factura.");
            return false;
        }
        String sql = """
            CREATE TABLE IF NOT EXISTS Factura (
                idFactura INT AUTO_INCREMENT PRIMARY KEY,
                idMesa INT NOT NULL,
                tipoPago VARCHAR(50),
                importe DOUBLE,
                FOREIGN KEY (idMesa) REFERENCES Mesa(idMesa)
            );
        """;
        return ejecutarUpdate(con, sql);
    }

    public static boolean crearTablaPedido(Connection con) {
        if (con == null) throw new SinConexionException();
        if (!existeTablaFactura(con) || !existeTablaProducto(con)) {
            System.out.println("Debes crear primero las tablas Factura y Producto antes de Pedido.");
            return false;
        }
        String sql = """
            CREATE TABLE IF NOT EXISTS Pedido (
                idPedido INT AUTO_INCREMENT PRIMARY KEY,
                idFactura INT NOT NULL,
                idProducto INT NOT NULL,
                cantidad INT NOT NULL,
                FOREIGN KEY (idFactura) REFERENCES Factura(idFactura),
                FOREIGN KEY (idProducto) REFERENCES Producto(idProducto)
            );
        """;
        return ejecutarUpdate(con, sql);
    }

    private static boolean ejecutarUpdate(Connection con, String sql) {
        if (con == null) throw new SinConexionException();
        try (Statement st = con.createStatement()) {
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            return false;
        }
    }

    // comprobar si las tablas existen antes de crear las correspondientes
    public static boolean existeTablaMesa(Connection con) { return existeTabla(con,"Mesa"); }
    public static boolean existeTablaProducto(Connection con) { return existeTabla(con,"Producto"); }
    public static boolean existeTablaFactura(Connection con) { return existeTabla(con,"Factura"); }
    public static boolean existeTablaPedido(Connection con) { return existeTabla(con,"Pedido"); }

    private static boolean existeTabla(Connection con, String tabla) {
        if (con == null) throw new SinConexionException();
        try (ResultSet rs = con.getMetaData().getTables(null, null, tabla, null)) {
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error SQL al verificar tabla: " + e.getMessage());
            return false;
        }
    }

    // insertar datos
    public static boolean insertarMesa(Connection con, int nComensales, short reserva) {
        if (con == null) throw new SinConexionException();
        if (reserva != 0 && reserva != 1) {
            System.out.println("Error: El campo 'reserva' solo puede ser 0 (Libre) o 1 (Reservada).");
            return false;
        }
        if (nComensales < 0) {
            System.out.println("Error: Número de comensales no puede ser negativo.");
            return false;
        }

        String sql = "INSERT INTO Mesa(numComensales,reserva) VALUES(?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nComensales);
            ps.setShort(2, reserva);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar Mesa: " + e.getMessage());
            return false;
        }
    }

    public static boolean insertarProducto(Connection con, String denominacion, double precio) {
        if (con == null) throw new SinConexionException();
        if (precio < 0) {
            System.out.println("Error: El precio no puede ser negativo.");
            return false;
        }

        String sql = "INSERT INTO Producto(denominacion, precio) VALUES(?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, denominacion);
            ps.setDouble(2, precio);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar Producto: " + e.getMessage());
            return false;
        }
    }

    public static boolean insertarFactura(Connection con, int idMesa, String tipoPago, double importe) {
        if (con == null) throw new SinConexionException();
        if (!existeFila(con, "Mesa", "idMesa", idMesa)) {
            System.out.println("La mesa indicada no existe.");
            return false;
        }
        if (importe < 0) {
            System.out.println("Error: El importe no puede ser negativo.");
            return false;
        }

        String sql = "INSERT INTO Factura(idMesa, tipoPago, importe) VALUES(?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idMesa);
            ps.setString(2, tipoPago);
            ps.setDouble(3, importe);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar Factura: " + e.getMessage());
            return false;
        }
    }

    public static boolean insertarPedido(Connection con, int idFactura, int idProducto, int cantidad) {
        if (con == null) throw new SinConexionException();
        if (!existeFila(con, "Factura", "idFactura", idFactura) || !existeFila(con, "Producto", "idProducto", idProducto)) return false;
        if (cantidad < 0) {
            System.out.println("Error: La cantidad no puede ser negativa.");
            return false;
        }

        String sql = "INSERT INTO Pedido(idFactura, idProducto, cantidad) VALUES(?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idFactura);
            ps.setInt(2, idProducto);
            ps.setInt(3, cantidad);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar Pedido: " + e.getMessage());
            return false;
        }
    }

    //comprobar si existen filas en las tablas
    private static boolean existeFila(Connection con, String tabla, String campo, int valor) {
        if (con == null) throw new SinConexionException();
        String sql = "SELECT 1 FROM " + tabla + " WHERE " + campo + "=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, valor);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public static List<Integer> obtenerIdsProductoPorDenominacion(Connection con, String denom) throws SQLException {
        if (con == null) throw new SinConexionException();
        List<Integer> lista = new ArrayList<>();
        String sql = "SELECT idProducto FROM Producto WHERE denominacion LIKE ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + denom + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(rs.getInt("idProducto"));
        }
        return lista;
    }

    // mostrar datos
    public static void mostrarDatosTabla(Connection con, String tabla) throws SinConexionException {
        if (con == null) throw new SinConexionException();

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM " + tabla)) {

            ResultSetMetaData meta = rs.getMetaData();
            int columnas = meta.getColumnCount();

            // Encabezado
            for (int i = 1; i <= columnas; i++)
                System.out.printf("%-20s", meta.getColumnName(i));
            System.out.println();

            // Línea separadora
            for (int i = 1; i <= columnas; i++)
                System.out.print("--------------------");
            System.out.println();

            // Filas
            while (rs.next()) {
                for (int i = 1; i <= columnas; i++)
                    System.out.printf("%-20s", rs.getString(i));
                System.out.println();
            }

        } catch (SQLException e) {
            System.err.println("No se pudo mostrar la tabla " + tabla);
            e.printStackTrace();
        }
    }

    public static void mostrarProductos(Connection con) throws SQLException {
        if (con == null) throw new SinConexionException();
        String sql = "SELECT * FROM Producto";
        try (Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            System.out.println("ID\tDenominación\tPrecio");
            while (rs.next()) {
                System.out.printf("%d\t%s\t%.2f\n",
                        rs.getInt("idProducto"), rs.getString("denominacion"), rs.getDouble("precio"));
            }
        }
    }

    //modificar filas
    public static boolean modificarFila(Connection con, String tabla, String campoFiltro, Object valorFiltro, Map<String, Object> nuevosValores) {
        if (con == null) throw new SinConexionException();
        if (nuevosValores.isEmpty()) return false;

        // Validaciones numéricas
        for (Map.Entry<String, Object> entry : nuevosValores.entrySet()) {
            String campo = entry.getKey();
            Object valor = entry.getValue();

            if (valor instanceof Number) {
                double num = ((Number) valor).doubleValue();
                if (num < 0) {
                    System.out.println("Error: El valor del campo '" + campo + "' no puede ser negativo.");
                    return false;
                }
            }

            // Validación específica de reserva
            if ("reserva".equals(campo)) {
                if (!(valor instanceof Number) || (((Number) valor).intValue() != 0 && ((Number) valor).intValue() != 1)) {
                    System.out.println("Error: El campo 'reserva' solo puede ser 0 o 1.");
                    return false;
                }
            }
        }

        StringBuilder sql = new StringBuilder("UPDATE " + tabla + " SET ");
        int i = 0;
        for (String campo : nuevosValores.keySet()) {
            if (i++ > 0) sql.append(", ");
            sql.append(campo).append("=?");
        }
        sql.append(" WHERE ").append(campoFiltro).append("=?");

        try (PreparedStatement ps = con.prepareStatement(sql.toString())) {
            i = 1;
            for (Object val : nuevosValores.values()) ps.setObject(i++, val);
            ps.setObject(i, valorFiltro);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error SQL al modificar: " + e.getMessage());
            return false;
        }
    }

    // borrar y eliminar
    public static void borrarDatos(Connection con, String tabla) {
        if (con == null) throw new SinConexionException();
        if (!existeTabla(con, tabla)) {
            System.out.println("La tabla '" + tabla + "' no existe.");
            return;
        }

        try {
            con.setAutoCommit(false);

            mostrarDatosTabla(con, tabla);

            System.out.println("\n¿Desea borrar TODOS los datos o solo algunos según un filtro?");
            System.out.println("1. Borrar TODOS los datos");
            System.out.println("2. Borrar según filtro");
            int tipoBorrado = sc.nextInt();
            sc.nextLine();

            String sqlDelete;
            if (tipoBorrado == 1) {
                sqlDelete = "DELETE FROM " + tabla;
            } else if (tipoBorrado == 2) {
                System.out.println("Escriba condición SQL para borrar (ejemplo: denominacion = 'Agua'):");
                String filtro = sc.nextLine();
                sqlDelete = "DELETE FROM " + tabla + " WHERE " + filtro;
            } else {
                System.out.println("Opción inválida");
                return;
            }

            try (Statement stmt = con.createStatement()) {
                int afectadas = stmt.executeUpdate(sqlDelete);
                System.out.println("\nRegistros afectados: " + afectadas);

                System.out.println("¿Desea confirmar los cambios?");
                System.out.println("1. Confirmar (COMMIT)");
                System.out.println("2. Deshacer (ROLLBACK)");
                int dec = sc.nextInt();

                if (dec == 1) {
                    con.commit();
                    System.out.println("Cambios confirmados.");
                } else {
                    con.rollback();
                    System.out.println("Cambios deshechos.");
                }
            }

        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        } finally {
            try { con.setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public static void eliminarTablas(Connection con) {
        if (con == null) throw new SinConexionException();

        System.out.println("¡Atención! Esto eliminará TODAS las tablas.");
        System.out.print("¿Desea continuar? (S/N): ");
        String resp = sc.nextLine().trim().toUpperCase();

        if (!resp.equals("S")) {
            System.out.println("Operación cancelada.");
            return;
        }

        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS Pedido");
            stmt.executeUpdate("DROP TABLE IF EXISTS Factura");
            stmt.executeUpdate("DROP TABLE IF EXISTS Producto");
            stmt.executeUpdate("DROP TABLE IF EXISTS Mesa");
            System.out.println("Todas las tablas han sido eliminadas.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar tablas.");
            e.printStackTrace();
        }
    }
}
