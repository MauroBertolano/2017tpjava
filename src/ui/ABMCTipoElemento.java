package ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import entidades.Persona;
import entidades.TipoElemento;
import logica.ControladorTipoElemento;
import util.PersonaInvalida;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ABMCTipoElemento extends JInternalFrame {
	private ControladorTipoElemento ctrl=new ControladorTipoElemento();
	private JTextField txtCantMax;
	private JTextField txtNombre;
	private JLabel lblIdOculta;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMCTipoElemento frame = new ABMCTipoElemento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ABMCTipoElemento() {
		setTitle("ABMCTipoElemento");
		setBounds(100, 100, 320, 249);
		
		JLabel lblId = new JLabel("ID");
		
		JLabel lblNombre = new JLabel("Nombre");
		
		JLabel lblCantmax = new JLabel("CantMax");
		
		lblIdOculta = new JLabel("");
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		
		txtCantMax = new JTextField();
		txtCantMax.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarClick();
			}
		});
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				agregarClick();
			}
		});
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modificarClick();
			}
		});
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminarClick();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNombre)
									.addGap(18)
									.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(41)
									.addComponent(btnBuscar))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblId)
									.addGap(46)
									.addComponent(lblIdOculta))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblCantmax)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtCantMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(31, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAgregar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEliminar)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnModificar)
							.addGap(65))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(lblIdOculta))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBuscar))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCantmax)
						.addComponent(txtCantMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAgregar)
						.addComponent(btnModificar)
						.addComponent(btnEliminar))
					.addGap(40))
		);
		getContentPane().setLayout(groupLayout);

	}
	protected void buscarClick() {
		try {
			this.mapearAForm(ctrl.getByNombre(this.mapearDeForm()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al buscar el tipo");
		}
	
	}	
	protected void agregarClick() {
		try {
			ctrl.add(this.mapearDeForm());
			JOptionPane.showMessageDialog(this, "El tipo de elemento se guardo correctamente");
		}  catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al agregar el tipo");
		}
	
	}
	protected void modificarClick() {
		try {
			TipoElemento tp = this.mapearDeForm();
			tp.setId(Integer.parseInt(this.lblIdOculta.getText()));
			ctrl.actualiza(tp);
		}   catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al actualizar la persona");
		}

	
	}

	protected void eliminarClick() {
	
	}

	private void mapearAForm(TipoElemento tp){
		if (tp==null){
			JOptionPane.showMessageDialog(this, "No se encontro el tipo de elemento");
			return;
		}
		this.txtNombre.setText(tp.getNombre());
		this.txtCantMax.setText(Integer.toString(tp.getCantMax()));
		this.lblIdOculta.setText(Integer.toString(tp.getId()));
	}
	private TipoElemento mapearDeForm(){
		TipoElemento tp = new TipoElemento();
		tp.setNombre(this.txtNombre.getText());
		if(this.txtCantMax.getText().equals("")){
			tp.setCantMax(0);
		}else{
		tp.setCantMax(Integer.parseInt(this.txtCantMax.getText()));}
		return tp;
	}
}
