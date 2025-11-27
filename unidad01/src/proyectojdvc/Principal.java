package proyectojdvc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class Principal {

    public static Scanner sc = new Scanner(System.in);
    // Conexión a la base de datos
    private static Connection con = null;

    public static void main(String[] args) {
      

        int opcion;
        do {
            mostrarMenu();
            opcion = pedirOpcion();

            try {
                switch (opcion) {
                    case 1 -> {
                        con = Conexion.conectarBD();
                        System.out.println();
                        System.out.println("Conexión establecida correctamente.");
                    }
                    case 2 -> menuCrearTablas();
                    case 3 -> menuInsertar();
                    case 4 -> menuListar();
                    case 5 -> menuModificar();
                    case 6 -> menuBorrar();
                    case 7 -> CRUD.eliminarTablas(con);
                    case 0 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (SinConexionException e) {
                System.err.println("No se puede ejecutar la operación: " + e.getMessage());
            } catch (SQLException e) {
                System.err.println("Error SQL: " + e.getMessage());
            }

        } while (opcion != 0);
    }

    // menú principal
    public static void mostrarMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1. Conectar BD");
        System.out.println("2. Crear Tablas");
        System.out.println("3. Insertar");
        System.out.println("4. Listar");
        System.out.println("5. Modificar");
        System.out.println("6. Borrar datos");
        System.out.println("7. Eliminar tablas");
        System.out.println("0. Salir");
    }

    public static int pedirOpcion() {
        System.out.print("Opción: ");
        return sc.nextInt();
    }

    //crear tablas
    public static void menuCrearTablas() throws SinConexionException {
        System.out.println("\n===== CREAR TABLAS =====");
        System.out.println("1. Mesa");
        System.out.println("2. Producto");
        System.out.println("3. Factura");
        System.out.println("4. Pedido");
        System.out.println("5. Crear todas");
        System.out.print("Opción: ");
        int op = sc.nextInt();
        sc.nextLine();

        boolean resultado = switch (op) {
            case 1 -> CRUD.crearTablaMesa(con);
            case 2 -> CRUD.crearTablaProducto(con);
            case 3 -> CRUD.crearTablaFactura(con);
            case 4 -> CRUD.crearTablaPedido(con);
            case 5 ->
                CRUD.crearTablaMesa(con) && CRUD.crearTablaProducto(con)
                && CRUD.crearTablaFactura(con) && CRUD.crearTablaPedido(con);
            default -> {
                System.out.println("Opción inválida");
                yield false;
            }
        };

        System.out.println(resultado ? "Tabla(s) creada(s) correctamente" : "Error creando tablas");
    }

    // insertar datos
    public static void menuInsertar() throws SinConexionException, SQLException {
        System.out.println("\n===== INSERTAR =====");
        System.out.println("1. Mesa");
        System.out.println("2. Producto");
        System.out.println("3. Factura");
        System.out.println("4. Pedido");
        System.out.print("Opción: ");
        int op = sc.nextInt();
        sc.nextLine();

        switch (op) {
            case 1 -> {
                System.out.print("Número de comensales: ");
                int n = sc.nextInt();
                System.out.print("Reserva (0=Libre, 1=Reservada): ");
                short r = sc.nextShort();
                if (CRUD.insertarMesa(con, n, r)) System.out.println("Mesa insertada correctamente.");
            }
            case 2 -> {
                System.out.print("Denominación del producto: ");
                String d = sc.nextLine();
                System.out.print("Precio: ");
                double p = sc.nextDouble();
                if (CRUD.insertarProducto(con, d, p)) System.out.println("Producto insertado correctamente.");
            }
            case 3 -> {
                System.out.print("ID Mesa: ");
                int idMesa = sc.nextInt();
                sc.nextLine();
                System.out.print("Tipo de pago: ");
                String pago = sc.nextLine();
                System.out.print("Importe: ");
                double importe = sc.nextDouble();
                if (CRUD.insertarFactura(con, idMesa, pago, importe)) System.out.println("Factura insertada correctamente.");
            }
            case 4 -> {
                System.out.print("ID Factura: ");
                int idFac = sc.nextInt();
                sc.nextLine();
                System.out.print("Denominación del producto: ");
                String denom = sc.nextLine();
                List<Integer> ids = CRUD.obtenerIdsProductoPorDenominacion(con, denom);
                if (ids.isEmpty()) {
                    System.out.println("No existe un producto con esa denominación.");
                    return;
                }
                int idProd = (ids.size() == 1) ? ids.get(0) : seleccionarProducto(ids);
                System.out.print("Cantidad: ");
                int cant = sc.nextInt();
                if (CRUD.insertarPedido(con, idFac, idProd, cant)) System.out.println("Pedido insertado correctamente.");
            }
            default -> System.out.println("Opción no válida.");
        }
    }

    private static int seleccionarProducto(List<Integer> ids) {
        System.out.println("Se encontraron varios productos:");
        for (int i = 0; i < ids.size(); i++)
            System.out.println((i + 1) + ". ID: " + ids.get(i));
        System.out.print("Selecciona uno: ");
        int sel = sc.nextInt();
        sc.nextLine();
        return ids.get(sel - 1);
    }

    // listar datos
    public static void menuListar() throws SinConexionException, SQLException {
        System.out.println("\n===== LISTAR =====");
        System.out.println("1. Mesas");
        System.out.println("2. Productos");
        System.out.println("3. Facturas");
        System.out.println("4. Pedidos");
        System.out.print("Opción: ");
        int op = sc.nextInt();
        sc.nextLine();

        switch (op) {
            case 1 -> CRUD.mostrarDatosTabla(con, "Mesa");
            case 2 -> CRUD.mostrarProductos(con);
            case 3 -> CRUD.mostrarDatosTabla(con, "Factura");
            case 4 -> CRUD.mostrarDatosTabla(con, "Pedido");
            default -> System.out.println("Opción no válida.");
        }
    }

    // modificar datos
    public static void menuModificar() throws SinConexionException {

        System.out.println("\n===== MODIFICAR =====");
        System.out.println("1. Mesa");
        System.out.println("2. Producto");
        System.out.println("3. Factura");
        System.out.println("4. Pedido");
        System.out.print("Opción: ");

        int op = sc.nextInt();
        sc.nextLine();

        String tabla = switch (op) {
            case 1 -> "Mesa";
            case 2 -> "Producto";
            case 3 -> "Factura";
            case 4 -> "Pedido";
            default -> null;
        };

        if (tabla == null) {
            System.out.println("Opción no válida.");
            return;
        }

        String campoFiltro = switch (op) {
            case 1 -> "idMesa";
            case 2 -> "idProducto";
            case 3 -> "idFactura";
            case 4 -> "idPedido";
            default -> "";
        };

        // ============================
        // LISTAR LA TABLA ANTES DE MODIFICAR
        // ============================
        System.out.println("\nMostrando registros actuales de " + tabla + "...\n");
        CRUD.mostrarDatosTabla(con, tabla);
        System.out.println();

        // ============================
        // PEDIR ID A MODIFICAR
        // ============================
        System.out.print("Ingrese el ID del registro a modificar: ");
        int id = sc.nextInt();
        sc.nextLine();

        Map<String, Object> nuevosValores = new HashMap<>();

        boolean continuar = true;

        while (continuar) {

            System.out.print("Campo a modificar (o 'fin' para terminar): ");
            String campo = sc.nextLine();

            // condición de salida sin usar break
            continuar = !campo.equalsIgnoreCase("fin");

            if (continuar) {
                System.out.print("Nuevo valor: ");
                String valor = sc.nextLine();

                Object valFinal;
                if (valor.matches("\\d+")) {
                    valFinal = Integer.parseInt(valor);
                } else if (valor.matches("\\d+\\.\\d+")) {
                    valFinal = Double.parseDouble(valor);
                } else {
                    valFinal = valor;
                }

                nuevosValores.put(campo, valFinal);
            }
        }

        // ============================
        // APLICAR CAMBIOS
        // ============================
        if (CRUD.modificarFila(con, tabla, campoFiltro, id, nuevosValores)) {
            System.out.println("Registro modificado correctamente.");
        } else {
            System.out.println("Error al modificar registro.");
        }
    }



    //borrar datos
    public static void menuBorrar() throws SinConexionException {
        System.out.println("\n===== BORRAR DATOS =====");
        System.out.println("1. Mesa");
        System.out.println("2. Producto");
        System.out.println("3. Factura");
        System.out.println("4. Pedido");
        System.out.println("5. Todos");
        System.out.print("Opción: ");
        int op = sc.nextInt();
        sc.nextLine();

        String tabla = switch (op) {
            case 1 -> "Mesa";
            case 2 -> "Producto";
            case 3 -> "Factura";
            case 4 -> "Pedido";
            case 5 -> "TODOS";
            default -> null;
        };

        if (tabla == null) {
            System.out.println("Opción no válida.");
            return;
        }

        CRUD.borrarDatos(con, tabla);
    }
}
