package controlador;

import java.sql.*;

public class conexionDB {
	
	public Connection abrirBaseDatos() {
		String urlDB = "jdbc:mysql://localhost:3306/festividades";
		String user = "UserFestividades";
		String pw = "lariosb0418";
		
		try {
			Connection accesoDB = DriverManager.getConnection(urlDB, user, pw);
			System.out.println("Conectado");
			return accesoDB;
			

		} catch (SQLException e) {
			System.out.println("No Conectado");

			return null;
		}
		
	}

}