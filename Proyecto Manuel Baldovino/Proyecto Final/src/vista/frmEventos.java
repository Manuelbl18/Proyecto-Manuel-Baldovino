package vista;

import controlador.EventoController;
import modelo.Evento;

import java.awt.EventQueue;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class frmEventos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtNombre;
	private JTextField txtFecha;
	private JTextField txtLugar;
	private JRadioButton rdbtnBoleteriaNo;
	private JRadioButton rdbtnBoleteriaSi;
	private DefaultTableModel tabla;	
	private JComboBox cmbEstado;
	private JComboBox cmbTipo;
	private JComboBox cmbHoraInicio;
	private JComboBox cmbAMPMinicio;
	private JComboBox cmbHoraFinal;
	private JComboBox cmbAMPMfinal;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnEliminar;
	private int codigoEventoSeleccionado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmEventos frame = new frmEventos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmEventos() {
		setTitle("Festividades - Eventos");	
		
		tabla = new DefaultTableModel(
				new String [][] {
					{
						"Código",
						"Nombre", 
						"Tipo", 
						"Fecha", 
						"Hora inicial", 
						"Hora final",
						"Lugar",
						"Boletería",
						"Estado"},
				},				
				new String [] {
						"Código",
						"Nombre", 
						"Tipo", 
						"Fecha", 
						"Hora inicial", 
						"Hora final",
						"Lugar",
						"Boletería",
						"Estado",							
							}) 
				{
					@Override
					    public boolean isCellEditable(int row, int column) {
			        return false; // Las celdas no serán editables
									}
			    };		
		limpiarDatosTabla();
		cargarDatosTabalMYSQL();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 739);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogo = new JLabel("New label");
		lblLogo.setBounds(163, 10, 305, 129);
		contentPane.add(lblLogo);
		
		Icon nuevoLogo = new ImageIcon(getClass().getResource("/imagenes/Festividades.png"));
		lblLogo.setIcon(nuevoLogo);
		
		table = new JTable(tabla);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				codigoEventoSeleccionado = (int) tabla.getValueAt(i, 0);
				btnGuardar.setText("Actualizar");
				btnEliminar.setVisible(true);
				btnCancelar.setVisible(true);
				
				txtNombre.setText(tabla.getValueAt(i, 1).toString());
				txtFecha.setText(tabla.getValueAt(i, 3).toString());
				txtLugar.setText(tabla.getValueAt(i, 6).toString());
				txtNombre.requestFocus();
				
				cambiarEstadoObjetos(true);

			}
		});
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBounds(10, 149, 626, 212);
		contentPane.add(table);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(28, 370, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tipo");
		lblNewLabel_1.setBounds(28, 395, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Fecha");
		lblNewLabel_2.setBounds(28, 420, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Hora inicio");
		lblNewLabel_2_1.setBounds(28, 448, 64, 14);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3 = new JLabel("Hora final");
		lblNewLabel_3.setBounds(28, 473, 82, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1_1 = new JLabel("Lugar");
		lblNewLabel_1_1.setBounds(28, 497, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Boleteria");
		lblNewLabel_2_2.setBounds(28, 533, 75, 14);
		contentPane.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Estado");
		lblNewLabel_2_1_1.setBounds(28, 570, 46, 14);
		contentPane.add(lblNewLabel_2_1_1);
		
		cmbTipo = new JComboBox();
		cmbTipo.setEnabled(false);
		cmbTipo.setModel(new DefaultComboBoxModel(new String[] {"C: Concierto", "D: Desfile"}));
		cmbTipo.setBounds(120, 391, 503, 22);
		contentPane.add(cmbTipo);
		
		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		txtNombre.setBounds(120, 367, 503, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtFecha = new JTextField();
		txtFecha.setEnabled(false);
		txtFecha.setColumns(10);
		txtFecha.setBounds(120, 417, 503, 20);
		contentPane.add(txtFecha);
		
		cmbHoraInicio = new JComboBox();
		cmbHoraInicio.setEnabled(false);
		cmbHoraInicio.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		cmbHoraInicio.setSelectedIndex(0);
		cmbHoraInicio.setBounds(120, 444, 418, 22);
		contentPane.add(cmbHoraInicio);
		
		cmbAMPMinicio = new JComboBox();
		cmbAMPMinicio.setEnabled(false);
		cmbAMPMinicio.setModel(new DefaultComboBoxModel(new String[] {"a.m.", "p.m."}));
		cmbAMPMinicio.setBounds(548, 444, 75, 22);
		contentPane.add(cmbAMPMinicio);
		
		cmbHoraFinal = new JComboBox();
		cmbHoraFinal.setEnabled(false);
		cmbHoraFinal.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		cmbHoraFinal.setBounds(120, 473, 418, 22);
		contentPane.add(cmbHoraFinal);
		
		cmbAMPMfinal = new JComboBox();
		cmbAMPMfinal.setEnabled(false);
		cmbAMPMfinal.setModel(new DefaultComboBoxModel(new String[] {"a.m.", "p.m."}));
		cmbAMPMfinal.setBounds(548, 469, 75, 22);
		contentPane.add(cmbAMPMfinal);
		
		txtLugar = new JTextField();
		txtLugar.setEnabled(false);
		txtLugar.setColumns(10);
		txtLugar.setBounds(120, 498, 503, 20);
		contentPane.add(txtLugar);
		
		rdbtnBoleteriaNo = new JRadioButton("No");
		rdbtnBoleteriaNo.setEnabled(false);
		rdbtnBoleteriaNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnBoleteriaNo.setSelected(true);
				rdbtnBoleteriaSi.setSelected(false);
			}
		});
		rdbtnBoleteriaNo.setBackground(new Color(255, 255, 255));
		rdbtnBoleteriaNo.setBounds(195, 529, 109, 23);
		contentPane.add(rdbtnBoleteriaNo);
		rdbtnBoleteriaNo.setSelected(false);
		
		
		rdbtnBoleteriaSi = new JRadioButton("Si");
		rdbtnBoleteriaSi.setEnabled(false);
		rdbtnBoleteriaSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnBoleteriaSi.setSelected(true);
				rdbtnBoleteriaNo.setSelected(false);
			}
		});
		rdbtnBoleteriaSi.setBackground(new Color(255, 255, 255));
		rdbtnBoleteriaSi.setBounds(120, 528, 82, 23);
		contentPane.add(rdbtnBoleteriaSi);
		rdbtnBoleteriaSi.setSelected(true);
		
		
		cmbEstado = new JComboBox();
		cmbEstado.setEnabled(false);
		cmbEstado.setModel(new DefaultComboBoxModel(new String[] {"Programado", "Ejecutado"}));
		cmbEstado.setBounds(120, 566, 503, 22);
		contentPane.add(cmbEstado);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borronyCuentaNueva();
			}
			
		});
		btnCancelar.setVisible(false);
		btnCancelar.setBounds(454, 630, 169, 23);
		contentPane.add(btnCancelar);
		
		btnGuardar = new JButton("Nuevo");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnGuardar.getText() == "Nuevo") {
					cambiarEstadoObjetos(true);
					btnGuardar.setText("Guardar");
					btnCancelar.setVisible(true);
				}else if(btnGuardar.getText() == "Guardar") {
					System.out.println("Empieza el proceso de guardado");
					EventoController miNuevoEvento = new EventoController();
					String tipoEvento = "";
					if (cmbTipo.getSelectedIndex()==0) {
						tipoEvento = "C";
					}else {
						tipoEvento = "D";
					}
					String horaInicio = cmbHoraInicio.getSelectedItem().toString() + cmbAMPMinicio.getSelectedItem().toString();
					String horaFinal = cmbHoraFinal.getSelectedItem().toString() + cmbAMPMfinal.getSelectedItem().toString();
					String boleteria = "";
					if(rdbtnBoleteriaSi.isSelected()) {
						boleteria = "Si"	;
					}else {
						boleteria = "No"	;
					}
					 
					boolean respuestaFinal = miNuevoEvento.guardarEvento(
																			txtNombre.getText(),
																			tipoEvento,
																			txtFecha.getText(),
																			horaInicio,
																			horaFinal,
																			txtLugar.getText(),
																			boleteria,
																			cmbEstado.getSelectedItem().toString()																			
																		);
					if (respuestaFinal) {
						JOptionPane.showMessageDialog(null, "Datos guardados correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error interno");	
					}
					
					borronyCuentaNueva();
					limpiarDatosTabla();
					cargarDatosTabalMYSQL();
				}else if(btnGuardar.getText() == "Actualizar") {
					System.out.println("Empieza el proceso de Actulizar");
					EventoController miNuevoEvento = new EventoController();
					String tipoEvento = "";
					if (cmbTipo.getSelectedIndex()==0) {
						tipoEvento = "C";
					}else {
						tipoEvento = "D";
					}
					String horaInicio = cmbHoraInicio.getSelectedItem().toString() + cmbAMPMinicio.getSelectedItem().toString();
					String horaFinal = cmbHoraFinal.getSelectedItem().toString() + cmbAMPMfinal.getSelectedItem().toString();
					String boleteria = "";
					if(rdbtnBoleteriaSi.isSelected()) {
						boleteria = "Si"	;
					}else {
						boleteria = "No"	;
					}
					 
					boolean respuestaFinal = miNuevoEvento.actualizarEvento(
																			codigoEventoSeleccionado,
																			txtNombre.getText(),
																			tipoEvento,
																			txtFecha.getText(),
																			horaInicio,
																			horaFinal,
																			txtLugar.getText(),
																			boleteria,
																			cmbEstado.getSelectedItem().toString()																			
																		);
					if (respuestaFinal) {
						JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error interno");	
					}
					
					borronyCuentaNueva();
					limpiarDatosTabla();
					cargarDatosTabalMYSQL();
				}
			}
		});
		btnGuardar.setBounds(28, 630, 169, 23);
		contentPane.add(btnGuardar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventoController borrarEvento = new EventoController();
				borrarEvento.EliminarEvento(codigoEventoSeleccionado);
				limpiarDatosTabla();
				cargarDatosTabalMYSQL();
				btnEliminar.setVisible(false);
				borronyCuentaNueva();
			}
		});
		btnEliminar.setVisible(false);
		btnEliminar.setBounds(245, 630, 169, 23);
		contentPane.add(btnEliminar);
		
	}
	
	private void cambiarEstadoObjetos(boolean estado) {
		txtFecha.setEnabled(estado);
		txtLugar.setEnabled(estado);
		txtNombre.setEnabled(estado);
		cmbHoraInicio.setEnabled(estado);
		cmbAMPMinicio.setEnabled(estado);
		cmbHoraFinal.setEnabled(estado);
		cmbAMPMfinal.setEnabled(estado);
		cmbEstado.setEnabled(estado);
		cmbTipo.setEnabled(estado);
		rdbtnBoleteriaNo.setEnabled(estado);
		rdbtnBoleteriaSi.setEnabled(estado);
		
	}
	
	private void borronyCuentaNueva() {
		cambiarEstadoObjetos(false);
		btnGuardar.setText("Nuevo");				
		btnCancelar.setVisible(false);
		btnEliminar.setVisible(false);
		txtFecha.setText("");
		txtLugar.setText("");
		txtNombre.setText("");	
	}
	
	private void limpiarDatosTabla() {
		tabla.setRowCount(1);
	}
	
	private void cargarDatosTabalMYSQL() {
		EventoController ListaEventos = new EventoController();
		ArrayList<Evento> info = ListaEventos.mostrarEventos();
		for (Evento datos : info) {
			Object[] fila = new Object[9];
			fila[0] = datos.getCodigoEvento();
			fila[1] = datos.getNombre();
			fila[2] = datos.getTipoEvento();
			fila[3] = datos.getFecha();
			fila[4] = datos.getHoraInicio();
			fila[5] = datos.getHoraFinal();
			fila[6] = datos.getLugar();
			fila[7] = datos.getBoleteria();
			fila[8] = datos.getEstado();			
			tabla.addRow(fila);
		}
		
	}
	
}