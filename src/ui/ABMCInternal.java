package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidades.Persona;
import logica.Controlador;

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

public class ABMCInternal extends JInternalFrame {
	
	private Controlador ctrl=new Controlador();

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JCheckBox chckHabilitado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMCInternal frame = new ABMCInternal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ABMCInternal() {
		setClosable(true);
		setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 295, 230);
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
		btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modificarClick();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblDni)
									.addGap(38)
									.addComponent(txtDni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnBuscar))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblApellido)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNombre)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addComponent(chckHabilitado)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAgregar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEliminar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnModificar)))
					.addContainerGap(42, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDni)
						.addComponent(btnBuscar)
						.addComponent(txtDni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblApellido)
						.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNombre))
					.addGap(18)
					.addComponent(chckHabilitado)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAgregar)
						.addComponent(btnEliminar)
						.addComponent(btnModificar))
					.addContainerGap(94, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	protected void buscarClick(){
		this.mapearAForm(ctrl.getByDni(this.mapearDeForm()));
	}
	protected void agregarClick() {
		ctrl.add(this.mapearDeForm());
	}

	protected void eliminarClick() {
		ctrl.borrar(this.mapearDeForm());
	}

	protected void modificarClick() {
		ctrl.actualiza(this.mapearDeForm());

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
	}
	private Persona mapearDeForm(){
		Persona p = new Persona();
		p.setDni(this.txtDni.getText());
		p.setApellido(this.txtApellido.getText());
		p.setNombre(this.txtNombre.getText());
		p.setHabilitado(this.chckHabilitado.isSelected());
		return p;
	}

}