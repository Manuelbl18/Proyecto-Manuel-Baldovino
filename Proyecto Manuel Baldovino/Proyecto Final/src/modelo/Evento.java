package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Evento {
	
	 	private int codigoEvento;  
	 	private String nombre;  
	 	private String tipoEvento;  
	 	private String fecha;  
	 	private String horaInicio;  
	 	private String horaFinal;  
	 	private String lugar;  
	 	private String boleteria;  
	 	private String estado;
	 	
	 	
	 	
		public Evento(int codigoEvento, String nombre, String tipoEvento, String fecha, String horaInicio,
				String horaFinal, String lugar, String boleteria, String estado) {
			
			this.codigoEvento = codigoEvento;
			this.nombre = nombre;
			this.tipoEvento = tipoEvento;
			this.fecha = fecha;
			this.horaInicio = horaInicio;
			this.horaFinal = horaFinal;
			this.lugar = lugar;
			this.boleteria = boleteria;
			this.estado = estado;
		}
		
		public Evento(String nombre, String tipoEvento, String fecha, String horaInicio,
				String horaFinal, String lugar, String boleteria, String estado) {
			
			this.nombre = nombre;
			this.tipoEvento = tipoEvento;
			this.fecha = fecha;
			this.horaInicio = horaInicio;
			this.horaFinal = horaFinal;
			this.lugar = lugar;
			this.boleteria = boleteria;
			this.estado = estado;
		}
		
		public Evento(int codigoEvento) {
			
			this.codigoEvento = codigoEvento;
		
		}
		
		public Evento() {			
		
		}
		
		public int getCodigoEvento() {
			return codigoEvento;
		}
		public void setCodigoEvento(int codigoEvento) {
			this.codigoEvento = codigoEvento;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getTipoEvento() {
			return tipoEvento;
		}
		public void setTipoEvento(String tipoEvento) {
			this.tipoEvento = tipoEvento;
		}
		public String getFecha() {
			return fecha;
		}
		public void setFecha(String fecha) {
			this.fecha = fecha;
		}
		public String getHoraInicio() {
			return horaInicio;
		}
		public void setHoraInicio(String horaInicio) {
			this.horaInicio = horaInicio;
		}
		public String getHoraFinal() {
			return horaFinal;
		}
		public void setHoraFinal(String horaFinal) {
			this.horaFinal = horaFinal;
		}
		public String getLugar() {
			return lugar;
		}
		public void setLugar(String lugar) {
			this.lugar = lugar;
		}
		public String getBoleteria() {
			return boleteria;
		}
		public void setBoleteria(String boleteria) {
			this.boleteria = boleteria;
		}
		public String getEstado() {
			return estado;
		}
		public void setEstado(String estado) {
			this.estado = estado;
		} 
		
		public boolean agregarEvento(Connection conexionDB) {
			
			boolean respuesta = true;
			String sql = "INSERT INTO tbleventos"
					+ "(nombre, "
					+ "tipoEvento, "
					+ "fecha, "
					+ "horaInicio, "
					+ "horaFinal, "
					+ "lugar, "
					+ "boleteria, "
					+ "estado)"
					+ " VALUES ('"
					+ nombre + "','"
					+ tipoEvento +"','"
					+ fecha + "','"
					+ horaInicio + "','"
					+ horaFinal + "','"
					+ lugar + "','"
					+ boleteria + "','"
					+ estado + "');";
			
			try {
				Statement ejecutarComandoSQL = conexionDB.createStatement();
				ejecutarComandoSQL.executeUpdate(sql);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				respuesta = false;				
				
			}
			return respuesta;
			
		}
		
		public boolean actualizarEvento(Connection conexionDB) {
			
			boolean respuesta = (true);

			String consultaActulizar = "UPDATE tbleventos "
										+ "SET "
											+ "codigoEvento = '"
												+ codigoEvento
											+ "',"
											+ "nombre = '"
												+ nombre
											+ "',"
											+ "tipoEvento = '"
												+ tipoEvento
											+ "',"
											+ "	fecha = '"
												+ fecha
											+ "',"
											+ "horaInicio = '"
												+ horaInicio
											+ "',"
											+ "horaFinal = '"
												+ horaFinal
											+ "',"
											+ "lugar = '"
												+ lugar
											+ "',"
											+ "boleteria = '"
												+ boleteria
											+ "',"
											+ "estado = '"
												+ estado
											+ "'"
										+ "WHERE "
											+ "codigoEvento = "
											+ codigoEvento
											+ ";";
			
			try {
				Statement ejecutarComandoSQL = conexionDB.createStatement();
				ejecutarComandoSQL.executeUpdate(consultaActulizar);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				respuesta = false;				
				
			}
			return respuesta;
		}
		public void EliminarEvento(Connection conexionDB,int codigoE) {
			String consulta = "DELETE FROM tbleventos "
								+ "WHERE codigoEvento = "
								+ codigoE
								+ ";";
			
			try {
				Statement ejecutarComandoSQL = conexionDB.createStatement();
				ejecutarComandoSQL.executeUpdate(consulta);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
								
				
			}
		}
		public ArrayList mostrarEventos(Connection conexionDB) {
			
			String consulta = "SELECT * FROM tbleventos;"; 
			ResultSet datosMySQL;
			ArrayList<Evento> infoTransformadaJava = new ArrayList<Evento>();
			
			try {
				Statement ejecutarComandoSQL = conexionDB.createStatement();
				datosMySQL = ejecutarComandoSQL.executeQuery(consulta);
				
				while(datosMySQL.next()) {
					Evento fila = new Evento(
												datosMySQL.getInt("codigoEvento"), 
												datosMySQL.getString("nombre"), 
												datosMySQL.getString("tipoEvento"), 
												datosMySQL.getString("fecha"), 
												datosMySQL.getString("horaInicio"), 
												datosMySQL.getString("horaFinal"), 
												datosMySQL.getString("lugar"), 
												datosMySQL.getString("boleteria"), 
												datosMySQL.getString("estado")
											);
					infoTransformadaJava.add(fila);
					
				}
				return infoTransformadaJava;
				
				
				
			} catch (SQLException e) {
												
				return null;
			}
			
		}
		public void buscarEvento() {}
		
}