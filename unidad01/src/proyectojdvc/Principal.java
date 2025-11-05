package proyectojdvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;



public class Principal {
public static	Scanner sc=new Scanner(System.in);
	
	
public static void main(String[] args) {
	
  
    
    
	
	int opcion;
	
	do {
		
	menu();
	
	opcion=pedirOpcion();
	
	switch(opcion) {
	
	case 1->{
		Conexion.conectarBD();
	}
	case 2->{
		
	}
	case 3->{
		
	}
	case 4->{
		
	
	}
	case 5->{
		
	}
	case 6->{
		
	}
	case 7->{
		
	}
	case 0->{
		System.out.println("Saliendo");
	}
	default->{
		System.out.println("La opción introducida no es válida");
	}
	}
		
		
	}while(opcion!=0);
	
	
	
	
	
	
	
	
	
	
	
}


public static void  menu() {
	
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
	opcion=sc.nextInt();
	return opcion;
}
	
}


