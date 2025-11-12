package proyectojdvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Principal {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		int opcion;

		do {

			menu();

			opcion = pedirOpcion();

			switch (opcion) {

			case 1 -> {
				Conexion.conectarBD();
			}
			case 2 -> {
				subMenuTabla();

				opcion = pedirOpcion();

				switch (opcion) {
				// Caso 1: Crea una tabla Mesa
				case 1 -> {

					if (CRUD.crearTablaMesa()) {
						System.out.println("Tabla creada correctamente");
					} else {
						System.err.println("Error durante la creación de tablas");
					}

				}
				// Caso 2: Crea una tabla Producto
				case 2 -> {
					if (CRUD.crearTablaProducto()) {
						System.out.println("Tabla creada correctamente");
					} else {
						System.err.println("Error durante la creación de tablas");
					}

				}
				// Caso 3: Crea una tabla Factura
				case 3 -> {
					if (CRUD.existeTablaMesa()) {
						if (CRUD.crearTablaFactura()) {
							System.out.println("Tabla creada correctamente");
						} else {
							System.err.println("Error durante la creación de tablas");
						}
					}else {
						System.out.println("Debes crear primero la tabla Mesa");
					}
				}
				// Caso 4: Crea una tabla Pedido
				case 4 -> {
					if(CRUD.existeTablaFactura()&&CRUD.existeTablaProducto()) {
					if (CRUD.crearTablaPedido()) {
						System.out.println("Tabla creada correctamente");
					} else {
						System.err.println("Error durante la creación de tablas");
					}
				}else {
					System.out.println("Debes crear primero la tabla Factura y Producto");
				}
					}

				// Caso 2: Crea todas las tablas
				case 5 -> {
					if (CRUD.crearTablaMesa() && CRUD.crearTablaProducto() && CRUD.crearTablaFactura()
							&& CRUD.crearTablaPedido()) {

						System.out.println("Las tablas se crearon correctamente");
					} else {
						System.err.println("Error durante la creación de tablas");
					}

				}

				// default error
				default -> {
					System.out.println("La opción elegida no es válida");
				}
				}

			}
			case 3 -> {

			}
			case 4 -> {

			}
			case 5 -> {

			}
			case 6 -> {

			}
			case 7 -> {

			}
			case 0 -> {
				System.out.println("Saliendo");
			}
			default -> {
				System.out.println("La opción introducida no es válida");
			}
			}

		} while (opcion != 0);

	}

	public static void menu() {

		System.out.println();
		System.out.println("MENU:");
		System.out.println();
		System.out.println("1. Conectar con base de datos");
		System.out.println("2. Crear nueva tabla");
		System.out.println("3. Insertar nuevo campo");
		System.out.println("4. Mostrar datos");
		System.out.println("5. Modificar datos");
		System.out.println("6. Borrar datos");
		System.out.println("7. Elinar tabla");

	}

	public static int pedirOpcion() {
		int opcion;
		System.out.println();
		System.out.println("Introduce una opción");
		opcion = sc.nextInt();
		return opcion;
	}

	public static void subMenuTabla() {
		System.out.println();
		System.out.println("1. Tabla Mesa");
		System.out.println("2. Tabla Producto");
		System.out.println("3. Tabla Factura");
		System.out.println("4. Tabla Pedido");
		System.out.println("5. Crear todas las tablas");
	}

	public static String pedirNombreTabla() {
		String nombreTabla;

		System.out.println("Introduce el nombre de la tabla que desee crear");
		nombreTabla = sc.nextLine();

		return nombreTabla;
	}
}
