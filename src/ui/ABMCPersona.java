package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.CuentaLogeada;
import entidades.Persona;
import logica.Controlador;
import util.ValorInvalido;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JPasswordField;

public class ABMCPersona extends JInternalFrame {
	
	private Controlador ctrl=new Controlador();

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JCheckBox chckHabilitado;
	private JLabel lblId2;
	private JLabel lblIdOculta;
	private JTextField txtUsuario;
	private JPasswordField txtContraseña;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMCPersona frame = new ABMCPersona();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ABMCPersona() {
		setTitle("ABMCPersona");
		setClosable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 374, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNombre = new JLabel("Nombre");
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		
		JLabel lblDni = new JLabel("Dni");
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		
		chckHabilitado = new JCheckBox("Habilitado");
		chckHabilitado.setSelected(true);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarClick();
			}
		});
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				agregarClick();
			}
		});
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminarClick();
			}
		});
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modificarClick();
			}
		});
		
		JLabel lblId = new JLabel("ID");
		
		lblId2 = new JLabel("");
		
		lblIdOculta = new JLabel("");
		
		JLabel lblUsuario = new JLabel("Usuario");
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		
		txtContraseña = new JPasswordField();
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				limpiarClick();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblId, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
								.addComponent(lblContrasea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
								.addComponent(lblUsuario, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
								.addComponent(lblDni, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
								.addComponent(lblNombre, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
								.addComponent(lblApellido, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(txtDni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnBuscar))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnLimpiar))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(txtContraseña, Alignment.LEADING)
									.addComponent(txtUsuario, Alignment.LEADING))
								.addComponent(lblIdOculta, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(92, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(chckHabilitado, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
							.addGap(170))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAgregar)
							.addGap(6)
							.addComponent(btnEliminar)
							.addGap(6)
							.addComponent(btnModificar))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblIdOculta, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblId, GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDni)
						.addComponent(txtDni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBuscar))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLimpiar))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblApellido)
						.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsuario)
						.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContrasea)
						.addComponent(txtContraseña, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(chckHabilitado)
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAgregar)
						.addComponent(btnEliminar)
						.addComponent(btnModificar))
					.addGap(45))
		);
		contentPane.setLayout(gl_contentPane);
	}
	protected void limpiarClick() {
		Persona p = new Persona();
		p = CuentaLogeada.getPer(p);
		this.txtNombre.setText("");
		this.txtApellido.setText("");
		this.txtDni.setText("");
		this.chckHabilitado.setSelected(false);
		this.lblIdOculta.setText("");
		this.txtUsuario.setText("");
	}

	protected void buscarClick(){
			try {
				this.mapearAForm(ctrl.getByDni(this.mapearDeForm()));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Error al buscar la persona");
			}
	}
	protected void agregarClick() {
		try {
			ctrl.add(this.mapearDeForm());
			JOptionPane.showMessageDialog(this, "La persona se agregó correctamente");
		} catch (ValorInvalido e) {
			JOptionPane.showMessageDialog(this,e.getMessage());
		}  catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al agregar la persona");
		}
	}

	protected void eliminarClick() {
			try {
				ctrl.borrar(this.mapearDeForm());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Error al eliminar la persona");
			}
	}

	protected void modificarClick() {
		try {
			Persona p =this.mapearDeForm();
			p.setId(Integer.parseInt(this.lblIdOculta.getText()));
			ctrl.actualiza(p);
		}   catch (ValorInvalido e) {
		JOptionPane.showMessageDialog(this,e.getMessage());
		}
		    catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al actualizar la persona");
		}

	}
	private void mapearAForm(Persona p){
		if (p==null){
			JOptionPane.showMessageDialog(this, "No se encontro la persona");
			return;
		}
		this.txtNombre.setText(p.getNombre());
		this.txtApellido.setText(p.getApellido());
		this.txtDni.setText(p.getDni());
		this.chckHabilitado.setSelected(p.getHabilitado());
		this.lblIdOculta.setText(Integer.toString(p.getId()));
		this.txtUsuario.setText(p.getUser());
	}
	@SuppressWarnings("deprecation")
	private Persona mapearDeForm(){
		Persona p = new Persona();
		p.setDni(this.txtDni.getText());
		p.setApellido(this.txtApellido.getText());
		p.setNombre(this.txtNombre.getText());
		p.setHabilitado(this.chckHabilitado.isSelected());
		p.setUser(this.txtUsuario.getText());
		p.setPsw(this.txtContraseña.getText());
		return p;
	}
}