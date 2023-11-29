package controlador;
import java.sql.Connection;
import java.util.ArrayList;

import controlador.conexionDB;
import modelo.Evento;

public class EventoController {
	
	public boolean guardarEvento(String nombre, String tipoEvento, String fecha, String horaInicio,
			String horaFinal, String lugar, String boleteria, String estado) {
		
		boolean respuestaControlador = true;
		
		conexionDB baseDatos = new conexionDB();
		Connection conexionactiva = baseDatos.abrirBaseDatos();
		
		if(conexionactiva!=null) {
			Evento miEvento = new Evento(nombre,tipoEvento,fecha,horaInicio,
					 horaFinal,  lugar,  boleteria,  estado);
			boolean respuestaModelo = miEvento.agregarEvento(conexionactiva);
			if (respuestaModelo==true) {
				return respuestaControlador;
			}else {
				respuestaControlador = false;
				return respuestaControlador;
			}
		}
		else {
			respuestaControlador = false;
			return respuestaControlador;			
		}
		
	}
	
	public boolean actualizarEvento(int codigoEvento, String nombre, String tipoEvento, String fecha, String horaInicio,
			String horaFinal, String lugar, String boleteria, String estado) {
		
		boolean respuestaControlador = true;
		
		conexionDB baseDatos = new conexionDB();
		Connection conexionactiva = baseDatos.abrirBaseDatos();
		
		if(conexionactiva!=null) {
			Evento miEvento = new Evento(codigoEvento, nombre,tipoEvento,fecha,horaInicio,
					 horaFinal,  lugar,  boleteria,  estado);
			boolean respuestaModelo = miEvento.actualizarEvento(conexionactiva);
			if (respuestaModelo==true) {
				return respuestaControlador;
			}else {
				respuestaControlador = false;
				return respuestaControlador;
			}
		}
		else {
			respuestaControlador = false;
			return respuestaControlador;			
		}
	}
	
	public void EliminarEvento(int CodigoEVista) {
		conexionDB baseDatos = new conexionDB();
		Connection conexionactiva = baseDatos.abrirBaseDatos();
		Evento miEvento = new Evento();//MODELO
		miEvento.EliminarEvento(conexionactiva,CodigoEVista);
	}
	
	public ArrayList mostrarEventos() {
		conexionDB baseDatos = new conexionDB();
		Connection conexionactiva = baseDatos.abrirBaseDatos();
		Evento miEvento = new Evento();
		return miEvento.mostrarEventos(conexionactiva);
		
	}
	public void buscarEvento() {}
	

}